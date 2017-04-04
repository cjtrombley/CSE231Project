package Project;

/**
 * Created by cjt on 3/24/2017.
 */
public class HashTableBST {

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

    void add(PersonNode node) {
        int stringHash = Math.abs(node.name.hashCode()) % HASHSIZE;
        hashTable[stringHash].add(node);
    }

    void display() {
        for (int i = 0; i < HASHSIZE; i++) {
            System.out.printf("\nTable[%d]", i);
            hashTable[i].display(hashTable[i].root);
        }
    }

    PersonNode search(String key) {
        int searchHash = Math.abs(key.hashCode()) % HASHSIZE;
        return hashTable[searchHash].search(key);
    }

    void update(PersonNode node) {
        PersonNode searchNode = search(node.name);
        if(searchNode != null) {
            System.out.println("Updating information.");
            searchNode.address = node.address;
            searchNode.phoneNumber = node.phoneNumber;

        }
        else {
            System.out.println("Name not found.");
        }
    }


    void delete(String delName) {
        int delNameHash = Math.abs(delName.hashCode()) % HASHSIZE;
        hashTable[delNameHash].remove(delName);
    }



}
