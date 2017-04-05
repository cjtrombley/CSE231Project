package Project;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class implements a Hash Table with Linear Probing.
 * The size of the hash table is input by the user at creation.
 * Whenever the hash table is full, the hash table automatically
 * doubles in size.
 *
 * The Hash Table implements the following public methods:
 * add()
 * update()
 * search()
 * display()
 * delete()
 * printToFile()
 *
 * @author      Cody Trombley
 */
class HashTableLinearProbing {

    //Class members
    private PersonNode[] hashTable;

    private int rehashCounter = 0;
    private final double REHASHFACTOR = .25;


    /**
     * Create a new array of the same size as
     * the input parameter.
     *
     * @param tableSize     Size of hash table
     */
    HashTableLinearProbing(int tableSize) {
        hashTable = new PersonNode[tableSize];
    }


    /**
     * Add node to the hash table. First, generate a hashCode index
     * based on the name of the node. Beginning with the hashed index,
     * iterate through the array until the first null location is
     * found or the first node with a key of -1 (indicated a deleted node)
     * is is located. Once the first valid spot is found, insert the node
     * at that index. If the array is full, double the size of the array
     * to accommodate for the node to be inserted.
     *
     * @param node  Node to be added to hash table
     */
    void add (PersonNode node) {
        int nodeHash = Math.abs(node.name.hashCode()) % hashTable.length;
        int counter = 0;
        while (hashTable[nodeHash] != null && hashTable[nodeHash].key != -1) {

            //If name already exists in the table, update node with
            //new node address/phone number
            if (hashTable[nodeHash].name.equals(node.name)) {

                hashTable[nodeHash].address = node.address;
                hashTable[nodeHash].phoneNumber = node.phoneNumber;
                return;
            }
            else {
                nodeHash++;
                if (nodeHash == hashTable.length) { //array index wrap around
                    nodeHash = 0;
                }

                counter++;
                if (counter == hashTable.length) { //every array location is full, double the size of the array
                    doubleArray();

                    //recalculate hash for node to be inserted based on new table size
                    nodeHash = Math.abs(node.name.hashCode() % hashTable.length);

                    System.out.println("Doubled table to size " + hashTable.length);

                    counter = 0; //reset counter back to 0
                }
            }
        }
        //save node in the array at modified nodeHash
        hashTable[nodeHash] = node;
    }


    /**
     * Search for node with name matching search parameter
     * node and, if found, update the node with the information
     * in the new node.
     *
     * @param node  Node to be updated
     */
    void update(PersonNode node) {
        PersonNode searchPerson = search(node.name);

        if(searchPerson != null) {
            System.out.println("Updating information.");
            searchPerson.address = node.address;
            searchPerson.phoneNumber = node.phoneNumber;
        }
        else {
            System.out.println("Name not found.");
        }
    }


    /**
     * Iterate through the hash table and at each
     * index, print the node at each index to the
     * console window.
     */
    void display() {
        for(int i = 0; i < hashTable.length; i++) {
            if(hashTable[i] != null) {
                System.out.println(hashTable[i].toString());
            }
        }
    }


    /**
     * Search the hash table for node with name matching
     * search string. Generate a hashCode based on the
     * search parameter, then iterate through the array
     * beginning at the hashed index. If a node with
     * a name matching the key is not found by the first
     * null index, key is not in table.
     *
     * @param searchName    Name of node to be searched for
     * @return              Node with name matching search param, null if not found
     */
    PersonNode search(String searchName){
        int searchHash = Math.abs(searchName.hashCode()) % hashTable.length;
        int counter = 0;

        while ( hashTable[searchHash] != null) {
            try{
                if (hashTable[searchHash].name.equals(searchName)) {
                    return hashTable[searchHash];
                }
            } catch (NullPointerException e) {
                //If node was previously deleted, calling hashTable[searchHash].name will
                //cause a null pointer exception.
                //Catch it and do nothing, allow loop to continue
            }

            counter++;
            if(counter == hashTable.length) { //whole array has been traversed, key not found
                return null;
            }

            searchHash++;
            if(searchHash == hashTable.length) {
                //index wrap-around
                searchHash = 0;

            }

        }

        //key not found by first null index
        return null;
    }


    /**
     * Delete node with name matching delete parameter
     * from the hash table. Generate a hashCode based
     * on the delete param and beginning at the
     * hashed index, iterate through the array until
     * the node is found or the first null space is
     * encountered.
     *
     * @param delName   Name of node to be deleted
     */
    void delete(String delName) {
        int delHash = Math.abs(delName.hashCode()) % hashTable.length;
        int counter = 0;

        while(hashTable[delHash] != null) {
            try {
                if (hashTable[delHash].name.equals(delName)) {
                    PersonNode del = hashTable[delHash];
                    System.out.println("Deleting node.");

                    //Set name, address and phone number to null
                    //Key =-1 signifies a node that has been deleted
                    del.name = null;
                    del.address = null;
                    del.phoneNumber = null;
                    del.key = -1;


                    rehashCounter++; //increment on successful delete

                    //Whenever 25% of the entries in the table are deleted, automatically
                    //rehash the table to improve performance
                    if (rehashCounter == (int) (hashTable.length * REHASHFACTOR)) {
                        rehash();
                    }
                    return;
                }
            } catch (NullPointerException e) {
                    //If hashTable[delHash].name was previously deleted
                    //this will throw a NullPointerException
                    //Catch it and do nothing.
                }

            delHash++;
            if (delHash == hashTable.length) {
                delHash = 0; //array index wrap around
            }
        }

        //name not found
        System.out.println("Name not found");
    }


    /**
     * Save contents of hash table to output file.
     * Iterate through array and if not null, print
     * contents of node to file.
     *
     * @param bw    BufferedWriter attached to output file
     */
    void printToFile(BufferedWriter bw) {
        System.out.println("Printting HashTable to file");
        for(int i = 0; i < hashTable.length; i++) {
            if(hashTable[i]!= null) {
                try {
                    bw.write(hashTable[i].toString());
                    bw.newLine();
                    bw.flush();
                } catch (IOException e) {
                    System.err.println("Error printing to file.");
                }

            }
        }

    }


    /**
     * Double size of hash table.
     */
    private void doubleArray() {
        //temporary array twice the size of original hash table
        PersonNode[] temp = new PersonNode[hashTable.length * 2];

        for (int i = 0; i < hashTable.length; i++) { //iterate through each index in hash table array
            PersonNode hashNode = hashTable[i];
            if(hashNode != null) { //if node at index is not empty
                if(hashNode.key != -1) { //if node at index has not been deleted
                    //calculate new hash index based on new table length;
                    int rehashIndex = Math.abs(hashTable[i].name.hashCode()) % temp.length;
                    while(temp[rehashIndex] != null) { //find first available null index location
                        rehashIndex++;
                    }
                    temp[rehashIndex] = hashTable[i]; //add node at index to newly rehashed index
                }
            }
        }

        //set temp array as the new hash table
        hashTable = temp;
    }


    /**
     * Rehash the table.
     *
     */
    private void rehash() {
        System.out.println("Rehashing the table.");

        //temp array of equal size to hash table
        PersonNode[] temp = new PersonNode[hashTable.length];


        for(PersonNode node : hashTable) {
            if(node.key != -1) { //for each node in the table that hasn't been deleted

                int newHash = Math.abs(node.name.hashCode() % hashTable.length);// calculate new hash index
                while (temp[newHash] != null) {
                    newHash++;

                    if (newHash == hashTable.length) { //index wrap around
                        newHash = 0;
                    }
                }

                temp[newHash] = node;
            }
        }
        //set hash table to newly rehashed array.
        hashTable = temp;
    }
}
