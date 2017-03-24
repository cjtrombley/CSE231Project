/**
 * Created by cjt on 3/10/2017.
 */


package Project;


import java.io.*;

class HashTableLinkedList {

    private final int HASHSIZE = 7;
    private LinkedList[] hashTable;


    HashTableLinkedList() {
        this.hashTable = new LinkedList[HASHSIZE];
        for (int i = 0; i < HASHSIZE; i++) {
            hashTable[i] = new LinkedList();
        }
    }


    void add(PersonNode node) {
        int stringHash = Math.abs(node.name.hashCode()) % HASHSIZE;
        hashTable[stringHash].add(node);
    }

    void display() {
        for (int i = 0; i < HASHSIZE; i++) {
            System.out.printf("\nTable[%d]", i);
            System.out.println(hashTable[i].toString());
        }
    }

    PersonNode search(String key) {
        int searchHash = Math.abs(key.hashCode()) % HASHSIZE;
        return hashTable[searchHash].get(key);
    }

    void delete(String delName) {
        int delNameHash = Math.abs(delName.hashCode()) % HASHSIZE;
        hashTable[delNameHash].remove(delName);
    }

    void printToFile(BufferedWriter bw) {
        try{
            for (int i = 0; i < HASHSIZE; i++) {
                PersonNode nptr = hashTable[i].head;

                while (nptr != null) {
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


