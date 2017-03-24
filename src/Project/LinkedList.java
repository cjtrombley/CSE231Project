/**
 * Created by cjt on 3/10/2017.
 */

package Project;

class LinkedList {

    PersonNode head;

    LinkedList() {
        this.head = null;
    }


    /**
     * Adds a node to the beginning of the Linked List.
     *
     * @param newNode The node to be added.
     */
    void add(PersonNode newNode) {

        //If there is no node at the head of the list,
        //insert new node as head
        if (head == null) {
            head = newNode;
        }

        //If there is a node at head, set the
        //current head as a child of the new head,
        //then make the new node the new head
        else {
            newNode.nextPerson = head;
            head = newNode;
        }
    }



    /**
     * Iterate through the LinkedList to find node
     * which has the same name as string parameter
     *
     * @param searchName The name to be searched
     * @return The node with matching name as
     * searched name
     */
    PersonNode get(String searchName) {

        PersonNode nptr = head;  //node pointer
        while (nptr != null) {
            if (nptr.name.equals(searchName)) {
                return nptr;
            } else {
                nptr = nptr.nextPerson;
            }
        }
        return null;
    }



    /**
     * Iterates through linked list to find node
     * with name matching parameter. If found,
     * set the parent of the node to be deleted
     * to point to the node following the deleted node.
     * The deleted node will be removed by the JVM
     * during garbage collection.
     *
     * @param delName Name to be deleted
     */
    void remove(String delName) {

        PersonNode nptr = head;      //node pointer for head
        PersonNode nptrpar = head;   //node point for parent

        while (nptr != null) {

            if (head.name.equals(delName)) { //delete head
                head = nptr.nextPerson;
                System.out.println("Deleting node " + delName);
                return;
            } else if (nptr.nextPerson != null) {
                if (nptr.nextPerson.name.equals(delName)) {

                    //set parent of deleted node to point to child
                    //of deleted node
                    nptrpar.nextPerson = nptr.nextPerson;
                    System.out.println("Deleting node " + delName);
                    return;
                }
            }
            //advance node pointers one node each
            nptrpar = nptr;
            nptr = nptr.nextPerson;
        }
        System.out.println("Name not found.");
    }



    /**
     * Iterates through the LinkedList and adds
     * names of nodes to a StringBuffer, returning
     * the toString() representation of the Buffer.
     *
     * @return string representation of a LinkedList
     */
    public String toString() {
        PersonNode nptr = head;
        StringBuffer sb = new StringBuffer();
        int counter = 1;

        while (nptr != null) {
            sb.append(" -> ").append(nptr.toString());
            counter++;
            if (counter % 3 == 0) { //every 3 nodes, append a newLine to the string.
                sb.append("\n ");
            }
            nptr = nptr.nextPerson;
        }
        return sb.toString();
    }
}



