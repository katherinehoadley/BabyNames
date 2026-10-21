// Katherine Hoadley
// The Menu class handles user interaction and input.
// It displays a menu of options, receives user selections, and redirects to the appropriate
// functionality by calling methods from other classes such as Popular, Rank, and Year.
// The NameDatabase is loaded once and shared across all queries.

import java.io.File;
import java.util.Scanner;

	//TODO get rid of if else chain in showMenu() ? not sure how to change this

public class Menu {

	//fields
    private NameDatabase nameDatabase;
    private Scanner scanner;

    //constructor
    public Menu() {
    	this.nameDatabase = new NameDatabase();
    	this.nameDatabase.loadData(new File("RealData"));         
        this.scanner = new Scanner(System.in);
    }


	/*
	 * Displays the main menu and handles user input in a loop until the user chooses to quit.
	 * Based on the user's input, the method calls the corresponding handler method.
	 * If the input is invalid, it prompts the user to try again.
	 */
    public void showMenu() {
        String input = "";	

        while (!input.equals("Q")) {
            System.out.println("P  Show most popular names for a given year and gender.");
            System.out.println("R  Show rank for a given name, gender, and year.");
            System.out.println("Y  Find the year in which the given name, gender combination was most popular.");
            System.out.println("Q  Quit");

            input = scanner.nextLine();
            
            try {
                queryRedirection(input);

            }
            catch(IllegalArgumentException iae){
            	System.out.println(iae.getMessage());
            }
        
            
        }
    }
    
    public void queryRedirection(String input) {
    	if (input.equals("P")) {
            selectedPopular();
        } 
        else if (input.equals("R")) {
            selectedRank();
        } 
        else if (input.equals("Y")) {
            selectedYear();
        } 
        else if (input.equals("Q")) {
        	System.exit(0);
        } 
        else {
            System.out.println("Invalid input. Try again.");
        }
    }
	
	


	/*
	 * Handles the "P" menu option.
	 * Prompts the user for gender and year, then calls the Popular class to retrieve
	 * the most popular name for that year and gender.
	 * Displays the result to the user.
	 */
	private void selectedPopular() {
        System.out.println("Enter gender as a capital letter (M/F):");
        String genderInput = scanner.nextLine().trim().toUpperCase();
        System.out.println("Enter the year:");
        int yearInput = Integer.parseInt(scanner.nextLine().trim());
        Popular popular = new Popular(nameDatabase);
        String result = popular.getPopular(yearInput, genderInput);
        System.out.println(result);
        System.out.println("----------");
    }

	/*
	 * Handles the "R" menu option.
	 * Prompts the user for a name, gender, and year, then calls the Rank class to retrieve
	 * the rank and frequency information for that name/gender/year combination.
	 * Displays the result to the user.
	 */
    private void selectedRank() {
        System.out.println("Enter the name:");
        String nameInput = scanner.nextLine().trim();
        System.out.println("Enter the gender as a capital letter (M/F):");
        String genderInput = scanner.nextLine().trim().toUpperCase();
        System.out.println("Enter the year:");
        int yearInput = Integer.parseInt(scanner.nextLine().trim());
        Rank rank = new Rank(nameDatabase);
        String result = rank.toString(nameInput, yearInput, genderInput);
        System.out.println(result);
        System.out.println("----------");
    }
    

	/*
	 * Handles the "Y" menu option.
	 * Prompts the user for a name and gender, then calls the Year class to determine
	 * the year in which that name/gender combination was most popular.
	 * Displays the result to the user.
	 */
    private void selectedYear() {
        System.out.println("Enter the name:");
        String nameInput = scanner.nextLine().trim();
        System.out.println("Enter the gender as a capital letter (M/F):");
        String genderInput = scanner.nextLine().trim().toUpperCase();
        Year yearQuery = new Year(nameDatabase);
        String result = yearQuery.getYear(nameInput, genderInput);
        System.out.println(result);
        System.out.println("----------");
    }
}
