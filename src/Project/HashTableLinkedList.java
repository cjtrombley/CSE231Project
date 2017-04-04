/**
 * HashTableLinkedList
 *
 * This class implements a Hash Table with Linked Lists as chains.
 *
 * This class implements the following public methods:
 *
 * add()
 * update()
 * display()
 * search()
 * delete()
 * printToFile()
 */


package Project;


import java.io.*;

class HashTableLinkedList {

    //Class members
    private final int HASHSIZE = 7;
    private LinkedList[] hashTable;


    /**
     * Default constructor. Create a new LinkedList array of size
     * HASHSIZE and initialize each LinkedList in the array.
     */
    HashTableLinkedList() {
        this.hashTable = new LinkedList[HASHSIZE];
        for (int i = 0; i < HASHSIZE; i++) {
            hashTable[i] = new LinkedList();
        }
    }


    /**
     * Add node to the Hash Table. Generate a hashCode
     * based on the node's name, then add the node to
     * the LinkedList at the hashed index.
     *
     * @param node Node to be added to the HashTable
     */
    void add(PersonNode node) {
        int stringHash = Math.abs(node.name.hashCode()) % HASHSIZE;
        hashTable[stringHash].add(node);
    }


    /**
     * Update information of node passed as parameter.
     * Generate a hashCode based on the node's name,
     * then update the node in the LinkedList at the
     * hashed index.
     *
     * @param node Node to be updated
     */
    void update(PersonNode node) {
        int updateHash = Math.abs(node.name.hashCode()) % HASHSIZE;
        hashTable[updateHash].update(node);

    }


    /**
     * Iterate through the HashTable and print the LinkedList
     * stored at each index.
     */
    void display() {
        for (int i = 0; i < HASHSIZE; i++) {
            System.out.printf("\nTable[%d]", i);
            System.out.println(hashTable[i].toString());
        }
    }


    /**
     * Find node with name matching search key.
     * Generate a hashCode based on search key,
     * then search the LinkedList at the hashed
     * index for the search key.
     *
     * @param key Name to be searched for
     * @return    Node with matching name
     */
    PersonNode search(String key) {
        int searchHash = Math.abs(key.hashCode()) % HASHSIZE;
        return hashTable[searchHash].get(key);
    }


    /**
     * Remove node with name matching key from
     * the hash table. Generate a hashCode based
     * on the delete key, then remove the name
     * matching the key from the appropriate index
     * in the hash table.
     *
     * @param delName Name of node to be deleted.
     */
    void delete(String delName) {
        int delNameHash = Math.abs(delName.hashCode()) % HASHSIZE;
        hashTable[delNameHash].remove(delName);
    }


    /**
     * Save hash table to output file.
     *
     * @param bw    BufferedWriter attatched to output File
     */
    void printToFile(BufferedWriter bw) {
        try{
            for (int i = 0; i < HASHSIZE; i++) { //iterate through each index of the hash table
                PersonNode nptr = hashTable[i].head;

                while (nptr != null) { //iterate through each node of the linked list
                    bw.write(nptr.toString());
                    bw.newLine();
                    bw.flush();
                    nptr = nptr.nextPerson;
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing file");
        }
    }
}


