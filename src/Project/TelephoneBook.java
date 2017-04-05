package Project;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * TelephoneBook
 *
 * This class provides the base functionality for the Phone Book.
 * Regardless of which data structure is used in the phone book,
 * each structure implements the same following public methods:
 *
 * add()
 * update()
 * search()
 * delete()
 * display()
 * save()
 *
 *@author   Cody Trombley
 */
class TelephoneBook {

    //Class members
    private BST bst;
    private HashTableLinkedList hashTableLinkedList;
    private HashTableBST hashTableBST;
    private HashTableLinearProbing hashTableLinearProbing;
    private ProjectLaunch pl;

    private BookType bookType;

    private BufferedReader inputFile;
    private BufferedWriter outputFile;
    private Scanner sc;


    /**
     *
     * @param bookTypeSelection   Int value representing Phone Book Type
     * @param inputFile           BufferedReader attached to input file
     * @param sc                  Scanner attached to System.in
     */
    TelephoneBook(int bookTypeSelection, BufferedReader inputFile, BufferedWriter bw, Scanner sc) {

        //Determine which type of phone book to make based on the selection input
        //Initialize the appropriate book type

        if (bookTypeSelection == 1) { //BST
            this.bookType = BookType.BINARYSEARCHTREE;
            this.bst = new BST();
        }
        else if (bookTypeSelection == 2) { //Hash Table with Linked List chains
            this.bookType = BookType.HASHTABLELINKEDLIST;
            this.hashTableLinkedList = new HashTableLinkedList();
        }
        else if (bookTypeSelection == 3) { //Hash Table with BST chains
            this.bookType = BookType.HASHTABLEBST;
            this.hashTableBST = new HashTableBST();
        }
        else if (bookTypeSelection == 4) { //Hash Table with Linear Probing
            this.bookType = BookType.HASHTABLELINEARPROBING;

            try{
                System.out.print("Enter table size for Hash Table: ");
                int size = Integer.parseInt(sc.nextLine());
                this.hashTableLinearProbing = new HashTableLinearProbing(size);

            } catch(InputMismatchException e){
                System.err.println("Please input an integer value.");
            }
        }

        this.inputFile = inputFile;
        this.outputFile = bw;
        this.sc = sc;
        init();
    }

    /**
     * Initialize the phone book. Read in lines
     * from the input file and add them to the
     * appropriate phone book structure.
     *
     */
    private void init() {
        try {
            String line = inputFile.readLine(); //read from input file
            while (line != null) {
                PersonNode node = new PersonNode(line); //create a new new node from line

                //add node to data structure
                switch (this.bookType) {
                    case BINARYSEARCHTREE:
                        bst.add(node);
                        break;

                    case HASHTABLELINKEDLIST:
                        hashTableLinkedList.add(node);
                        break;

                    case HASHTABLEBST:
                        hashTableBST.add(node);
                        break;

                    case HASHTABLELINEARPROBING:
                        hashTableLinearProbing.add(node);
                        break;

                    default:
                        break;
                }

                //read in the next line in the file
                line = inputFile.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error locating file.");
        }
    }


    /**
     * Add node to the phone book. Prompt user
     * to input name, address, and phone number
     * information, then create a new node and
     * add it to the appropriate phone book
     * structure.
     *
     */
    void add() {

        String name, address, phoneNum; //input vars

        System.out.print("Enter person's name: ");
        name = sc.nextLine();

        System.out.print("Enter person's address: ");
        address = sc.nextLine();

        System.out.print("Enter person's phone number: ");
        phoneNum = sc.nextLine();

        PersonNode node = new PersonNode(name + "," + address + "," + phoneNum); //create new node

        //add node to proper data structure
        switch (this.bookType) {
            case BINARYSEARCHTREE:
                bst.add(node);
                break;

            case HASHTABLELINKEDLIST:
                hashTableLinkedList.add(node);
                break;

            case HASHTABLEBST:
                hashTableBST.add(node);
                break;

            case HASHTABLELINEARPROBING:
                hashTableLinearProbing.add(node);
                break;

            default:
                break;
        }
    }


    /**
     * Update an existing node in the phone book.
     * Prompt the user to enter a name, followed by
     * an updated address and phone number. Find the
     * node if it exists and update the data
     * accordingly.
     */
    void update() {
        String updateName, newAddress, newNumber; //local input vars

        System.out.print("Enter name to be updated: ");
        updateName = sc.nextLine();

        System.out.print("Enter new address: ");
        newAddress = sc.nextLine();

        System.out.print("Enter new phone number: ");
        newNumber = sc.nextLine();

        //create new node based on input vars
        PersonNode updatedNode = new PersonNode(updateName + "," + newAddress + ", " + newNumber);

        //update the node in the proper data structure
        switch (this.bookType) {
            case BINARYSEARCHTREE:
                bst.update(updatedNode);
                break;

            case HASHTABLELINKEDLIST:
                hashTableLinkedList.update(updatedNode);
                break;

            case HASHTABLEBST:
                hashTableBST.update(updatedNode);
                break;

            case HASHTABLELINEARPROBING:
                hashTableLinearProbing.update(updatedNode);
                break;

            default:
                break;
        }
    }


    /**
     * Search for a name in the phone book. Prompt user
     * to enter name to search for. Search for the node
     * matching search parameter and print it to the
     * console.
     */
    void search() {
        String key; //local input var

        System.out.print("Enter the full name to search: ");

        key = sc.nextLine();
        PersonNode searchPerson = null;

        //search the appropriate data structure for the node
        //matching the key
        switch (this.bookType) {
            case BINARYSEARCHTREE:
                searchPerson = bst.search(key);
                break;

            case HASHTABLELINKEDLIST:
                searchPerson = hashTableLinkedList.search(key);
                break;

            case HASHTABLEBST:
                searchPerson = hashTableBST.search(key);
                break;

            case HASHTABLELINEARPROBING:
                searchPerson = hashTableLinearProbing.search(key);
                break;

            default:
                break;
        }
        if (searchPerson != null) { //print string representation of located node
            System.out.println(searchPerson.toString());
        }
        else {
            System.out.println("Name not found.");
        }
    }


    /**
     * Delete a name from the phone book. Prompt user to
     * enter a name to delete. Search for the node and, if
     * found, remove it from the data structure.
     */
    void delete() {
        String delName; //local input var

        System.out.print("Enter the name to be deleted: ");
        delName = sc.nextLine();

        //delete name from appropriate data structure
        switch (this.bookType) {
            case BINARYSEARCHTREE:
                bst.remove(delName);
                break;

            case HASHTABLELINKEDLIST:
                hashTableLinkedList.delete(delName);
                break;

            case HASHTABLEBST:
                hashTableBST.delete(delName);
                break;

            case HASHTABLELINEARPROBING:
                hashTableLinearProbing.delete(delName);
                break;

            default:
                break;
        }
    }


    /**
     * Print the phone book to the console window.
     */
    void display() {

        //Pring the appropriate structure
        switch (this.bookType) {
            case BINARYSEARCHTREE:
                bst.display(bst.root);
                break;


            case HASHTABLELINKEDLIST:
                hashTableLinkedList.display();
                break;

            case HASHTABLEBST:
                hashTableBST.display();
                break;


            case HASHTABLELINEARPROBING:
                hashTableLinearProbing.display();
                break;

            default:
                break;
        }
    }

    /**
     * Save the phone book to output file.
     */
    void save() {
        //save based on the appropriate data structure
        switch (this.bookType) {
            case BINARYSEARCHTREE:
                bst.printToFile(bst.root, outputFile);
                break;

            case HASHTABLELINKEDLIST:
                hashTableLinkedList.printToFile(outputFile);
                break;

            case HASHTABLEBST:
                hashTableBST.printToFile(outputFile);
                break;

            case HASHTABLELINEARPROBING:
                hashTableLinearProbing.printToFile(outputFile);

            default:
                break;
        }
    }


    BookType getType() { return bookType; }


    /**
     * Emum defining BookType objects.
     */
    public enum BookType {
        BINARYSEARCHTREE ("Binary Search Tree"),
        HASHTABLELINKEDLIST ("Hash Table - Linked List"),
        HASHTABLEBST("Hash Table - BST"),
        HASHTABLELINEARPROBING ("Hash Table - Linear Probing");


        final String bookType;

        BookType(String bookType) {
            this.bookType = bookType;
        }

        public String toString() { return bookType;}
    }

}