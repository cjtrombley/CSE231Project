/**
 * Created by cjt on 3/10/2017.
 */


package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//import static Project.BookType.HASHTABLE;

public class TelephoneBook {

    private BookType bookType;
    private BST bst;
    private HashTable hashTable;
    private String inputFile;
    private String outputFile;

    public TelephoneBook(int bookTypeSelection, String inputFile, String outputFile) {
        if (bookTypeSelection == 1) {
            this.bookType = BookType.HASHTABLE;
        } else {
            this.bookType = BookType.BINARYSEARCHTREE;
        }

        this.inputFile = inputFile;
        this.outputFile = outputFile;
        init();
    }


    private void init() {
        this.hashTable = new HashTable();
        this.bst = new BST();

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)));
            String line = br.readLine();

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

                line = br.readLine();
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
        try {
            File file = new File(outputFile);

            if (!file.exists()) {
                file.createNewFile();
            }
            switch (this.bookType) {
                case HASHTABLE:
                    hashTable.printToFile(file);
                    break;
                default:
                    break;
            }
        } catch(IOException e) {
            System.err.println("IOException");
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