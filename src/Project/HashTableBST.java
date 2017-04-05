package Project;

import java.io.BufferedWriter;

/**
 * HashTableBST
 *
 * This class creates a Hash Table using BSTs
 * as chains. This class implements the following
 * public methods:
 *
 * add()
 * update()
 * display()
 * search()
 * delete()
 * printToFile()
 *
 * @author      Cody Trombley
 */
class HashTableBST {

    //Class members
    private final int HASHSIZE = 7;
    private BST[] hashTable;


    /**
     * Default constructor. Create new array of
     * size HASHSIZE and for each index of the array,
     * initialize a new BST.
     */
    HashTableBST() {
        hashTable = new BST[HASHSIZE];
        for(int i = 0; i < HASHSIZE; i++) {
            hashTable[i] = new BST();
        }
    }


    /**
     * Add node to hash table. Generate hashCode based on
     * node name and add the node to the appropriate
     * BST at the hash index.
     *
     * @param node Node to be added to the hash table
     */
    void add(PersonNode node) {
        int stringHash = Math.abs(node.name.hashCode()) % HASHSIZE;
        hashTable[stringHash].add(node);
    }


    /**
     * Iterate through the hash table and print each
     * BST to the console window.
     */
    void display() {
        for (int i = 0; i < HASHSIZE; i++) {
            System.out.printf("\nTable[%d]", i);
            hashTable[i].display(hashTable[i].root);
        }
    }


    /**
     * Search the hash table for the node matching key.
     * Generate a hashCode based on search key, then
     * search the BST at the appropriate array index
     *
     * @param key   Name to be searched for
     * @return      Node with name matching key, null if not found
     */
    PersonNode search(String key) {
        int searchHash = Math.abs(key.hashCode()) % HASHSIZE;
        return hashTable[searchHash].search(key);
    }


    /**
     * Search the hash table for a node matching updated
     * nodes name. Generate hash code from node's name,
     * then update the BST at the hashed index
     *
     * @param node   Node to be updated
     */
    void update(PersonNode node) {
        int updateHash = Math.abs(node.name.hashCode()) % HASHSIZE;
        hashTable[updateHash].update(node);
    }


    /**
     * Remove node with name matching key from the
     * hash table. Generate an hashCode based on the
     * key, then delete the node from the BST at the
     * hashed index.
     *
     * @param delName   Name of node to be deleted
     */
    void delete(String delName) {
        int delNameHash = Math.abs(delName.hashCode()) % HASHSIZE;
        hashTable[delNameHash].remove(delName);
    }


    /**
     * Iterate through indexes of hash table and write the
     * contents of each BST at each index to the outputfile.
     *
     * @param bw    BufferedWriter attached to output file.
     */
    void printToFile(BufferedWriter bw) {
        System.out.println("Print HashTable to file.");
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i].printToFile(hashTable[i].root, bw);
        }
    }

}
