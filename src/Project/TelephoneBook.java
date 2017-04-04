/**
 * Created by cjt on 3/10/2017.
 */


package Project;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


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
     *
     *
     */
    private void init() {
        try {
            String line = inputFile.readLine();
            while (line != null) {
                PersonNode node = new PersonNode(line);
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

                line = inputFile.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error locating file.");
        }
    }

    void add() {

        String name, address, phoneNum;

        System.out.print("Enter person's name: ");
        name = sc.nextLine();

        System.out.print("Enter person's address: ");
        address = sc.nextLine();

        System.out.print("Enter person's phone number: ");
        phoneNum = sc.nextLine();

        PersonNode node = new PersonNode(name + "," + address + "," + phoneNum);

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

    void update() {
        System.out.print("Enter name to be updated: ");
        String newName = sc.nextLine();

        System.out.print("Enter new address: ");
        String newAddress = sc.nextLine();

        System.out.print("Enter new phone number: ");
        String newNumber = sc.nextLine();


        PersonNode updatedNode = new PersonNode(newName + "," + newAddress + ", " + newNumber);


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

    void search() {

        System.out.print("Enter the full name to search: ");

        String key = sc.nextLine();
        PersonNode searchPerson = null;

        switch (this.bookType) {
            case BINARYSEARCHTREE:
                searchPerson = (PersonNode)bst.search(key);
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
        if (searchPerson != null) {
            System.out.println(searchPerson.toString());
        }
        else {
            System.out.println("Name not found.");
        }
    }

    void delete() {

        System.out.print("Enter the name to be deleted: ");

        String delName = sc.nextLine();

        switch (this.bookType) {
            case BINARYSEARCHTREE:
                bst.remove(delName);
                break;

            case HASHTABLELINKEDLIST:
                hashTableLinkedList.delete(delName);
                break;

            case HASHTABLEBST:
                hashTableBST.delete(delName);

            case HASHTABLELINEARPROBING:
                hashTableLinearProbing.delete(delName);

            default:
                break;
        }
    }

    void display() {
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

    void save() {
            switch (this.bookType) {

                case HASHTABLELINKEDLIST:
                    hashTableLinkedList.printToFile(outputFile);
                    break;
                default:
                    bst.printToFile(bst.root, outputFile);
                    break;
            }


    }

    BookType getType() { return bookType; }

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