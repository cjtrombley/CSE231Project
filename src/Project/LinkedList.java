/**
 * Created by cjt on 3/10/2017.
 */

package Project;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements Iterable<PersonNode>{
	
	private PersonNode head;

	public LinkedList() {
		this.head = null;
	}

    /**
     * Adds a node to the beginning of the Linked List.
     *
     * @param newNode The node to be added.
    */
	public void add(PersonNode newNode) {

	    //If there is no node at the head of the list,
        //insert new node as head
	    if (head == null) {
            head = newNode;
        }

        //If there is a node at head, set the
        //new node to point to the current head,
        //then make the new node the new head
        else {
            newNode.setNext(head);
            head = newNode;
        }
    }

    /**
     * Iterate through the LinkedList to find node
     * which has the same name as string parameter
     *
     * @param searchName
     * @return The node with matching name as
     *         searched name
     */
	public PersonNode get(String searchName) {

	    PersonNode nptr = head;

	    while (nptr != null) {
	        if(nptr.getName().equals(searchName)) {
                return nptr;
            }
            else {
	            nptr = nptr.getNext();
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
     * @param delName
     */
	public void remove(String delName) {

	    PersonNode nptr = head;
	    PersonNode nptrpar = head;

	    while (nptr != null) {

	        if (head.getName().equals(delName)){
	            head = nptr.getNext();
	            System.out.println("Deleting node " + delName);
	            return;
            }

            else if (nptr.getNext() != null){
	            if (nptr.getNext().getName().equals(delName)){
	                nptrpar.setNext(nptr.getNext());
                    System.out.println("Deleting node " + delName);
	                return;
                }
            }

            nptrpar = nptr;
	        nptr = nptr.getNext();
        }

        System.out.println("Name not found.");
    }

    /**
     * Iterates through the LinkedList and adds
     * names of nodes to a StringBuffer, returning
     * the toString() representation of the Buffer.
     * @return string representation of a LinkedList
     */
	public String toString() {
	    PersonNode nptr = head;
	    StringBuffer sb = new StringBuffer();
	    int counter = 1;
	    while (nptr != null) {
            sb.append(" -> ").append(nptr.toString());
            counter++;
            if (counter % 3 == 0) {
                sb.append("\n ");
            }

            nptr = nptr.getNext();
        }

        return sb.toString();
    }

    /**
     * Getter for the private field head
     *
     * @return The node being pointed to by head.
     */
    public PersonNode getHead() { return head; }



    public Iterator<PersonNode> iterator() {
        return new LinkListIterator();
    }

    private class LinkListIterator implements Iterator<PersonNode> {

        private PersonNode nptr;

        public LinkListIterator() {
            this.nptr = LinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return (nptr.getNext() != null);
        }

        @Override
        public PersonNode next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            return nptr.getNext();
        }
    }
}