/**
 * Created by cjt on 3/10/2017.
 */


package Project;

import java.io.*;
import java.util.Scanner;

public class ProjectLaunch {

  TelephoneBook phoneBook;


    /**
     * Main method of application.
     *
     * @param args Command line arguments
     *        args[0] - Name of input file
     *        args[1] - Name of output file
     */
	public static void main(String[] args) {

        ProjectLaunch pl = new ProjectLaunch();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = null;
        BufferedWriter bw = null;

        System.out.println("Thank you for using TelephoneBook v1.0");
        System.out.println();
        System.out.println("1: Binary Search Tree");
        System.out.println("2: Hash Table w/ Chaining - Linked List");
        System.out.println("3: Hash Table w/ Chaining - BST");
        System.out.println("4: Hash Table w/ Linear Probing");

        System.out.print("Please enter a selection: ");

        try {
            //Create a BufferfedReader to read the input file indicated in args[0]
            br = new BufferedReader( new FileReader( new File (args[0])));

            //Create a BufferedWriter to save the directory to the
            //output file indicated in args[1]

            File outputFile = new File(args[1]);
            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }
            bw = new BufferedWriter ( new FileWriter ( outputFile));

            pl.phoneBook = new TelephoneBook(Integer.parseInt(sc.nextLine()), br, bw, sc);

            br.close();
        } catch (IOException e) {
            System.err.println("IO exception");
        }

        Boolean continueProgram = true;
        while (continueProgram) {
            pl.displayMenu();
            try {
                int selection = Integer.parseInt(sc.nextLine());

                switch(selection) {
                    case 1: pl.phoneBook.add();
                        break;
                    case 2: pl.phoneBook.search();
                        break;
                    case 3: pl.phoneBook.update();
                        break;
                    case 4: pl.phoneBook.delete();
                        break;
                    case 5: pl.phoneBook.display();
                        break;
                    case 6: pl.phoneBook.save();
                        break;
                    case 7:
                            System.out.println("Program now exiting.");
                            continueProgram = false;
                            break;
                }
            } catch (Exception e){
                System.err.println("Illegal input, please try again.");
            }

        }
    }



    /**
     *  Prints the menu selection options to the console window.
     *  The menu is the same regardless of phone book data type
     *  selected.
     */
    private void displayMenu() {
	    System.out.println("\nMain menu for " + phoneBook.getType().toString());
	    System.out.println("1: Insert telephone number.");
	    System.out.println("2: Retrieve telephone number.");
	    System.out.println("3: Update telephone number.");
	    System.out.println("4: Delete telephone number.");
	    System.out.println("5: Display phone book");
	    System.out.println("6: Save phone book to file. ");
	    System.out.println("7: Exit program.");
	    System.out.println("------------------------------------");
	    System.out.print("Please enter a selection: ");
    }

}