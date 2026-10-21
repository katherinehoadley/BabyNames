// Katherine Hoadley
// The NameGenderPair class provides functionality to find a name/gender combination
// in the most recent year of available data that has the same rank as a given name/gender
// combination had in a specified earlier year.
// It uses the Rank class to retrieve and compare.


import java.util.ArrayList;

public class NameGenderPair {
	
	//fields
	private NameDatabase nameDatabase;
	private Rank rank;
	final int MOST_RECENT_YEAR = 2024;
	
	//constructor
	public NameGenderPair(NameDatabase nameDatabase) {
        this.nameDatabase = nameDatabase;
        this.rank = new Rank(nameDatabase);
    }
	

	/*
	 * Given a name, gender, and year, this method finds the name/gender pair in the most recent year
	 * that has the same rank as the input name/gender had in the specified year.
	 * 
	 * Parameters:
	 * - name: the name to compare
	 * - year: the year in which the original name's rank is determined
	 * - gender: the gender associated with the name ("M" or "F")
	 * 
	 * Returns:
	 * - A formatted string describing what the name would be in the most recent year
	 *   if it had the same rank as it did in the given year.
	 */
	public String getNameGenderPair(String name, int year, String gender){
		// get original rank of the name in the given year
		int originalRank = rank.getRank(name, year, gender);

		// get the name with the same rank in the most recent year
		String newName = rank.getNameByRank(MOST_RECENT_YEAR, gender, originalRank);


		return "If " + name + " (" + gender + "), " + year + ", was born in " + MOST_RECENT_YEAR + ", the name would be " + newName + " (" + gender + ").";
	}
		
	
}
