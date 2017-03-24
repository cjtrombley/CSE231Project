/**
 * Created by cjt on 3/10/2017.
 */


package Project;

import java.io.*;
import java.util.Scanner;





public class TelephoneBook {

    private BookType bookType;
    private BST bst;
    private HashTable hashTable;
    private BufferedReader inputFile;
    private BufferedWriter outputFile;

    public TelephoneBook(int bookTypeSelection, BufferedReader inputFile, BufferedWriter bw) {
        if (bookTypeSelection == 1) {
            this.bookType = BookType.HASHTABLE;
        } else {
            this.bookType = BookType.BINARYSEARCHTREE;
        }

        this.inputFile = inputFile;
        this.outputFile = bw;
        init();
    }


    private void init() {
        this.hashTable = new HashTable();
        this.bst = new BST();

        try {

            String line = inputFile.readLine();

            while (line != null) {
                PersonNode node = new PersonNode(line, null);
                switch (this.bookType) {
                    case HASHTABLE:
                        hashTable.add(node);
                        break;

                    case BINARYSEARCHTREE:
                        bst.add(node);
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

    public void add() {

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
            case HASHTABLE: {
                hashTable.add(node);
                break;
            }
            case BINARYSEARCHTREE: {
                bst.add(node);
                break;
            }
            default:
                break;
        }
    }

    public void search() {
        Scanner searchScan = new Scanner(System.in);
        System.out.print("Enter the full name to search: ");

        String key = searchScan.nextLine();
        PersonNode searchPerson = null;

        switch (this.bookType) {
            case HASHTABLE:
                searchPerson = hashTable.search(key);
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

    public void delete() {
        Scanner delScan = new Scanner(System.in);
        System.out.print("Enter the name to be deleted: ");

        String delName = delScan.nextLine();

        switch (this.bookType) {
            case HASHTABLE:
                hashTable.delete(delName);
                break;
            default:
                break;
        }
    }

    public void display() {
        switch (this.bookType) {
            case HASHTABLE:
                hashTable.display();
                break;

            case BINARYSEARCHTREE:
                bst.display(bst.getRoot());
                break;

            default:
                break;
        }
    }

    public void save() {
            switch (this.bookType) {

                case HASHTABLE:
                    hashTable.printToFile(outputFile);
                    break;
                default:
                    break;
            }


    }

    public BookType getType() { return bookType; }

    public enum BookType {
        HASHTABLE ("Hash Table"),
        BINARYSEARCHTREE ("Binary Search Tree");

        private final String bookType;

        BookType(String bookType) {
            this.bookType = bookType;
        }

        public String toString() { return bookType;}
    }

}