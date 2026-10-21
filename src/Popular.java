// Katherine Hoadley
// The Popular class provides functionality to find the most popular name
// for a given year and gender using data from the NameDatabase.
// It returns the name at rank 1 along with its frequency of occurrence.

import java.util.ArrayList;

public class Popular {
	
	//field 
	private NameDatabase nameDatabase;
	
	//constructor
	public Popular(NameDatabase nameDatabase) {
		this.nameDatabase= nameDatabase;
	}
	

	/*
	 * Retrieves the most popular name for a specified year and gender.
	 * 
	 * Parameters:
	 * - year: the year to search
	 * - gender: the gender to search ("M" or "F")
	 * 
	 * Behavior:
	 * - Checks if data exists for the given year and gender.
	 * - Retrieves the list of names and selects the first entry (rank 1).
	 * 
	 * Returns:
	 * - A formatted string with the name and its frequency, or a message indicating
	 *   that no data is available for the given inputs.
	 */
	public String getPopular(int year, String gender) {		
        
        if (!nameDatabase.babyNameDataStorage.containsKey(year)) {
            return "No data for year " + year;
        }

        if (!nameDatabase.babyNameDataStorage.get(year).containsKey(gender)) {
            return "No data for gender " + gender + " in year " + year;
        }

        ArrayList<IndividualNameData> nameList = nameDatabase.getNameList(year, gender);

        if (nameList.isEmpty()) {
            return "No names found for gender " + gender + " in year " + year;
        }

        IndividualNameData mostPopularName = nameList.get(0);

        return "The most popular name in " + year + " for " + gender + " is " + mostPopularName.getName() + " with "  + mostPopularName.getFrequency() + " occurrences.";
    }
}
