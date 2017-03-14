/**
 * Created by cjt on 3/10/2017.
 */


package Project;


import java.io.*;

public class HashTable {

    private final int HASHSIZE = 7;
    private LinkedList[] hashTable;


    public HashTable() {
        this.hashTable = new LinkedList[HASHSIZE];
        for (int i = 0; i < HASHSIZE; i++) {
            hashTable[i] = new LinkedList();
        }

    }


    public void add(PersonNode node) {

        int stringHash = Math.abs(node.getName().hashCode());
        int tableHash = stringHash % HASHSIZE;
        hashTable[tableHash].add(node);
    }

    public void display() {

        for (int i = 0; i < HASHSIZE; i++) {
            System.out.printf("\nTable[%d]", i);
            System.out.println(hashTable[i].toString());
        }
    }

    public PersonNode search(String key) {

        int searchHash = Math.abs(key.hashCode());
        int searchTableHash = searchHash % 7;

        return hashTable[searchTableHash].get(key);
    }

    public void delete(String delName) {

        int delNameHash = Math.abs(delName.hashCode());
        int delTableHash = delNameHash % 7;

        hashTable[delTableHash].remove(delName);
    }

    public void printToFile(File file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < HASHSIZE; i++) {
                PersonNode nptr = hashTable[i].getHead();

                while (nptr != null) {
                    bw.write(nptr.toString());
                    bw.newLine();
                    bw.flush();
                    nptr = nptr.getNext();
                }

            }

        } catch (IOException e) {
            System.err.println("Error writing file");
        }
    }
}


