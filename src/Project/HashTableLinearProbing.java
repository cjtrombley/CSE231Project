package Project;

/**
 * Created by cjt on 3/24/2017.
 */
public class HashTableLinearProbing {

    PersonNode[] hashTable;

    private int rehashCounter = 0;
    private double rehashFactor;


    HashTableLinearProbing(int tableSize, int rehashFactor)
    {
        hashTable = new PersonNode[tableSize];
        this.rehashFactor = rehashFactor / 100;
    }


    void add (PersonNode node) {
        int nodeHash = Math.abs(node.name.hashCode()) % hashTable.length;
        int counter = 0;
        while (hashTable[nodeHash] != null && hashTable[nodeHash].key != -1) {
            nodeHash++;

            if(nodeHash == hashTable.length) {
                nodeHash = 0;
            }

            counter++;
            if(counter == hashTable.length) {
                doubleArray();
                System.out.println("Doubled array to size " + hashTable.length);
                counter = 0;
            }


        }

        hashTable[nodeHash] = node;
    }


    void display() {
        for(int i = 0; i < hashTable.length; i++) {
            if(hashTable[i] != null) {
                System.out.println(hashTable[i].toString());
            }
        }
    }


    PersonNode search(String searchName){
        int searchHash = Math.abs(searchName.hashCode()) % hashTable.length;
        int counter = 0;

        while ( hashTable[searchHash] != null) {
            if (hashTable[searchHash].name.equals(searchName)){
                return hashTable[searchHash];
            } else {
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

        }

        //key not found by first null index
        return null;
    }

    void delete(String delName) {
        int delHash = Math.abs(delName.hashCode()) % hashTable.length;
        int counter = 0;

        while(hashTable[delHash] != null) {
            if (hashTable[delHash].name.equals(delName)) {
                PersonNode del = hashTable[delHash];
                System.out.println("Deleting node.");
                del.name = null;
                del.address = null;
                del.phoneNumber = null;
                del.key = -1;


                rehashCounter++;
                if(rehashCounter == (int)(hashTable.length) / rehashFactor) {
                    rehash();
                }
            }

            delHash++;
            if (delHash == hashTable.length) {
                delHash = 0; //array index wrap around
            }
        }

    }



    private void doubleArray() {
        PersonNode[] temp = new PersonNode[hashTable.length * 2];

        for (int i = 0; i < hashTable.length; i++) {
            PersonNode hashNode = hashTable[i];
            if(hashNode != null) {
                if(hashNode.key != -1) {
                    int rehashIndex = Math.abs(hashTable[i].name.hashCode()) % temp.length;
                    while(temp[rehashIndex] != null) {
                        rehashIndex++;
                    }
                    temp[rehashIndex] = hashTable[i];
                }
            }
        }

        hashTable = temp;
    }


    private void rehash() {


    }
}
