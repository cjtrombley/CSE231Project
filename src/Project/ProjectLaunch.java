/**
 * Created by cjt on 3/10/2017.
 */


package Project;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProjectLaunch {

  private TelephoneBook phoneBook;


	public static void main(String[] args) {

	    for (String s: args) {
	        System.out.println(s);
        }

        ProjectLaunch pl = new ProjectLaunch();
	   // File inputFile = new File(pl.getClass().getResource(args[0]).toString());
        Scanner sc = new Scanner(System.in);

        System.out.println("Thank you for using TelephoneBook v1.0");
        System.out.println();
        System.out.println("1: Hash Table");
        System.out.println("2: Binary Search Tree");
        System.out.print("Please enter a selection: ");


        //Path inputPath = Paths.get(args[0]);
       // Path outputPath = Paths.get(args[1]);

        //URL url = ProjectLaunch.getClass().getResource(args[0]);
        //System.out.println(url.toString());

        //System.out.println(inputPath.toString() + " " + outputPath.toString());

        //Class cls = pl.getClass();
        //URL url = cls.getResource(args[0]);
       // String string = (args[0]);

        String string = pl.getClass().getResource("../res/projectdata.csv").toString();
        BufferedReader br = null;
        BufferedWriter bw = null;
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

            pl.phoneBook = new TelephoneBook(sc.nextInt(), br, bw);

            bw.close();
            br.close();
        } catch (IOException e) {
            System.err.println("IO exception");
        }

        Boolean continueProgram = true;

        while (continueProgram) {
            pl.displayMenu();
            try {
                int selection = sc.nextInt();

                switch(selection) {
                    case 1: pl.phoneBook.add();
                        break;
                    case 2: pl.phoneBook.search();
                        break;
                    case 3: pl.phoneBook.delete();
                        break;
                    case 4: pl.phoneBook.display();
                        break;
                    case 5: pl.phoneBook.save();
                        break;
                    case 6:
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
	    System.out.println("3: Delete telephone number.");
	    System.out.println("4: Display phone book");
	    System.out.println("5: Save phone book to file. ");
	    System.out.println("6: Exit program.");
	    System.out.println("------------------------------------");
	    System.out.print("Please enter a selection: ");
    }

}