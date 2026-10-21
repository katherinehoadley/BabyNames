// Katherine Hoadley
// The Rank class provides functionality to determine the rank of a name for a given year and gender,
// retrieve a name by its rank, and format rank-related information as a readable string.
// It uses data from the NameDatabase and supports rank calculations based on name frequency.

import java.util.ArrayList;

public class Rank {

    // field
    private NameDatabase nameDatabase;

    // constructor gets loaded database
    public Rank(NameDatabase nameDatabase) {
        this.nameDatabase = nameDatabase;
    }

	/*
	 * Calculates the rank of a given name for a specific year and gender.
	 * 
	 * Parameters:
	 * - name: the name to look up
	 * - year: the year to search
	 * - gender: the gender to filter by ("M" or "F")
	 * 
	 * Behavior:
	 * - Validates that data exists for the given year and gender.
	 * - Iterates through the name list, updating rank when frequency changes.
	 * - Returns the rank of the name if found, or -1 if not found.
	 * 
	 * Throws:
	 * - IllegalArgumentException if the year or gender is not found in the database.
	 */
    public int getRank(String name, int year, String gender) {
        
            if (!nameDatabase.babyNameDataStorage.containsKey(year)) {
                throw new IllegalArgumentException("No data available for year " + year);
            }

            if (!nameDatabase.babyNameDataStorage.get(year).containsKey(gender)) {
                throw new IllegalArgumentException("No data available for gender " + gender + " in year " + year);
            }

            ArrayList<IndividualNameData> nameList = nameDatabase.getNameList(year, gender);
            if (nameList.isEmpty()) {
                throw new IllegalArgumentException("No names found for gender " + gender + " in year " + year);
            }

            int rank = 0;
            int namesSeen = 0;
            int prevFrequency = -1;

            for (IndividualNameData entry : nameList) {
                namesSeen++;
                if (entry.getFrequency() != prevFrequency) {
                    rank = namesSeen;
                }
                if (entry.getName().equals(name)) {
                    return rank;
                }
                prevFrequency = entry.getFrequency();
            }
            
			return -1;
    }


	/*
	 * Retrieves the name that corresponds to a specific rank for a given year and gender.
	 * 
	 * Parameters:
	 * - year: the year to search
	 * - gender: the gender to filter by ("M" or "F")
	 * - targetRank: the rank to look up
	 * 
	 * Behavior:
	 * - Validates that name data exists for the given year and gender.
	 * - Iterates through the name list, updating rank when frequency changes.
	 * - Returns the name at the specified rank, or "Unavailable" if not found.
	 * 
	 * Catches:
	 * - Any exceptions related to missing data and prints an error message.
	 */
    public String getNameByRank(int year, String gender, int targetRank) {
        try {
            ArrayList<IndividualNameData> nameList = nameDatabase.getNameList(year, gender);

            if (nameList == null || nameList.isEmpty()) {
                throw new Exception("No names found for gender " + gender + " in year " + year);
            }

            int rank = 0;
            int namesSeen = 0;
            int prevFrequency = -1;

            for (IndividualNameData entry : nameList) {
                namesSeen++;
                if (entry.getFrequency() != prevFrequency) {
                    rank = namesSeen;
                }
                if (rank == targetRank) {
                    return entry.getName();
                }
                prevFrequency = entry.getFrequency();
            }

            throw new Exception("No name found with rank " + targetRank + " in year " + year + " for gender " + gender);

        } catch (Exception e) {
            System.out.println("Error in getNameByRank: " + e.getMessage());
            return "Unavailable";
        }
    }

	/*
	 * Returns a formatted string describing the rank and frequency of a given name
	 * for a specific year and gender.
	 * 
	 * Parameters:
	 * - name: the name to describe
	 * - year: the year to search
	 * - gender: the gender to filter by ("M" or "F")
	 * 
	 * Behavior:
	 * - Calls getRank to determine the name's rank.
	 * - Retrieves the frequency of the name from the name list.
	 * - Returns a descriptive string with name, gender, year, frequency, and rank.
	 * 
	 * Throws:
	 * - IllegalArgumentException if the name or frequency is not found.
	 */
    public String toString(String name, int year, String gender) {
        
            int rank = getRank(name, year, gender);
            if (rank == -1) {
                throw new IllegalArgumentException("Rank not found for name " + name);
            }

            ArrayList<IndividualNameData> nameList = nameDatabase.getNameList(year, gender);
            int frequency = -1;
            for (IndividualNameData entry : nameList) {
                if (entry.getName().equals(name)) {
                    frequency = entry.getFrequency();
                    break;
                }
            }

            if (frequency == -1) {
                throw new IllegalArgumentException("Frequency not found for name " + name);
            }

            return "The name " + name + ", gender " + gender + ", in the year " + year +
                   " occurred with frequency " + frequency + " and rank " + rank + ".";

        
    }
}
