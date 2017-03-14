/**
 * Created by cjt on 3/10/2017.
 */


package Project;

import java.util.Scanner;

public class ProjectLaunch {

  private TelephoneBook phoneBook;

	public static void main(String[] args) {

	    for (String s: args) {
	        System.out.println(s);
        }

        ProjectLaunch pl = new ProjectLaunch();
        Scanner sc = new Scanner(System.in);

        System.out.println("Thank you for using TelephoneBook v1.0");
        System.out.println();
        System.out.println("1: Hash Table");
        System.out.println("2: Binary Search Tree");
        System.out.print("Please enter a selection: ");

        pl.phoneBook = new TelephoneBook(sc.nextInt(), args[0], args[1]);

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