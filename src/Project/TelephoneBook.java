/**
 * Created by cjt on 3/10/2017.
 */


package Project;

import java.io.*;
import java.util.Scanner;


class TelephoneBook {

    private BST bst;
    private HashTableLinkedList hashTableLinkedList;
    private HashTableBST hashTableBST;
    private HashTableLinearProbing hashTableLinearProbing;
    private ProjectLaunch pl;

    private BookType bookType;

    private BufferedReader inputFile;
    private BufferedWriter outputFile;

    TelephoneBook(int bookTypeSelection, BufferedReader inputFile, BufferedWriter bw) {


        if (bookTypeSelection == 1) {
            this.bookType = BookType.BINARYSEARCHTREE;
            this.bst = new BST();
        }
        else if (bookTypeSelection == 2) {
            this.bookType = BookType.HASHTABLELINKEDLIST;
            this.hashTableLinkedList = new HashTableLinkedList();
        }
        else if (bookTypeSelection == 3) {
            this.bookType = BookType.HASHTABLEBST;
            this.hashTableBST = new HashTableBST();
        }
        else if (bookTypeSelection == 4) {
            this.bookType = BookType.HASHTABLELINEARPROBING;


            /*
            try{
                System.out.print("Enter table size for Hash Table: ");
                int size = sc2.nextInt();

                //System.out.println("Enter rehashing factor (int): ");
                //int rehashFactor = sc.nextInt();

                int rehashFactor = 3;
                this.hashTableLinearProbing = new HashTableLinearProbing(size, rehashFactor);

                sc2.close();



            } catch(Exception e){
                System.err.println("Error");
            }*/
        }

        this.inputFile = inputFile;
        this.outputFile = bw;
        init();
    }


    private void init() {
        try {
            String line = inputFile.readLine();
            while (line != null) {
                PersonNode node = new PersonNode(line, null);
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
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter person's name: ");
        name = sc.nextLine();

        System.out.print("Enter person's address: ");
        address = sc.nextLine();

        System.out.print("Enter person's phone number: ");
        phoneNum = sc.nextLine();

        PersonNode node = new PersonNode(name + "," + address + "," + phoneNum , null);

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

            default:
                break;
        }
    }

    void search() {
        Scanner searchScan = new Scanner(System.in);
        System.out.print("Enter the full name to search: ");

        String key = searchScan.nextLine();
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
        Scanner delScan = new Scanner(System.in);
        System.out.print("Enter the name to be deleted: ");

        String delName = delScan.nextLine();

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