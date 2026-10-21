// Katherine Hoadley
// The PopularLetter class analyzes baby name data to determine the most popular
// starting letter for names of a given gender over a specified range of years.
// It returns both the most frequent starting letter and an alphabetized list
// of all names that begin with that letter.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PopularLetter {
	
	//field
	private NameDatabase nameDatabase;
	final int FIRST_YEAR = 1880;
	final int LAST_YEAR = 2024;
	
	
	//constructor
	public PopularLetter(NameDatabase nameDatabase) {
		this.nameDatabase = nameDatabase;
	}

	/*
	 * Determines the most popular starting letter for names of a given gender
	 * across a specified range of years, and returns an alphabetized list of
	 * all names that begin with that letter.
	 * 
	 * Parameters:
	 * - startYear: the beginning of the year range (inclusive)
	 * - endYear: the end of the year range (inclusive)
	 * - gender: the gender to filter names by ("M" or "F")
	 * 
	 * Behavior:
	 * - Aggregates frequency counts for each starting letter.
	 * - Tracks all unique names that begin with each letter.
	 * - Identifies the letter with the highest total frequency.
	 * - Sorts and returns the list of names that start with that letter.
	 * 
	 * Returns:
	 * - A formatted string indicating the most popular starting letter and
	 *   the alphabetized list of names that begin with it.
	 * - Throws an IllegalArgumentException if the year range is invalid or
	 *   if no data is found for the given gender in the specified years.
	 */
	public String getPopularLetter(int startYear, int endYear, String gender) {
		if (startYear > endYear || startYear < FIRST_YEAR || endYear > LAST_YEAR) {
			throw new IllegalArgumentException("Start year must be <= end year and year range must be between 1880 and 2024.");
		}
        HashMap<Character, Integer> letterFrequency = new HashMap<>();
        HashMap<Character, ArrayList<String>> letterNames = new HashMap<>();
        
        for(int year = startYear; year <= endYear; year++) {
        	ArrayList<IndividualNameData> nameList = nameDatabase.getNameList(year, gender);
        	
        	if (nameList != null) {
        		for(IndividualNameData entry : nameList) {
        			if(!entry.getName().isEmpty()) {
        				char firstLetter = Character.toUpperCase(entry.getName().charAt(0));
        				
        				//add frequency
        				int currentFreq;
                        if (letterFrequency.containsKey(firstLetter)) {
                            currentFreq = letterFrequency.get(firstLetter);
                        } 
                        else {
                            currentFreq = 0;
                        }
                        letterFrequency.put(firstLetter, currentFreq + entry.getFrequency());
                        
                        //add name
                        if (!letterNames.containsKey(firstLetter)) {
                            letterNames.put(firstLetter, new ArrayList<String>());
                        }
                        
                        ArrayList<String> names = letterNames.get(firstLetter);
                        if (!names.contains(entry.getName())) {
                            names.add(entry.getName());
                        }
                        
        			}
        		}
        	}
        	
        }
        if (letterFrequency.isEmpty()) {
        	throw new IllegalArgumentException("No data found for gender " + gender + " in given years.");
        }
        
     //find most popular letter
        char popularLetter = ' ';
        int maxFreq = -1;
        for (HashMap.Entry<Character, Integer> entry : letterFrequency.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                popularLetter = entry.getKey();
            }
        }
        
     //alphabetize names
        ArrayList<String> namesList = letterNames.get(popularLetter);
        Collections.sort(namesList);
        
    	return "Most popular starting letter for gender " + gender + " between " + startYear + " and " + endYear + " is '" + popularLetter + "' with names: " + namesList;

	}
	
	
}

