//Katherine Hoadley
// This class provides functionality to retrieve the ranking history of a given name and gender
// across years using a name database. It uses the NameDatabase and Rank classes to access and 
// format rank information for each year from 1880 to 2024.


import java.util.ArrayList;
import java.util.HashMap;
public class AllRanksOfName {
	
	//fields
	private NameDatabase nameDatabase;
	private Rank rank;
	
	//constructor
	public AllRanksOfName(NameDatabase nameDatabase) {
        this.nameDatabase = nameDatabase;
        this.rank = new Rank(nameDatabase);
    }
	


	/*
     * Given a name and a gender, this method returns a list of strings representing
     * the rank of that name/gender pair for each year from 1880 to 2024.
     * 
     * For each year, it calls rank.toString(name, year, gender) to get the rank info.
     * If the result is not null, it adds a formatted string "year: rankInfo" to the list.
     * 
     * Parameters:
     * - name: the name to look up
     * - gender: the gender associated with the name ("M" or "F")
     * 
     * Returns:
     * - An ArrayList of strings, each containing the year and corresponding rank info
     *   for the given name and gender.
     */

	public ArrayList<String> getAllRanksOfName(String name, String gender) {
	    ArrayList<String> allRanks = new ArrayList<>();

	    for (Integer year : nameDatabase.babyNameDataStorage.keySet()) {
	    	String yearRankInfo = rank.toString(name, year, gender);
	    	if(yearRankInfo != null) {
	    		allRanks.add(year + ": " + yearRankInfo);
	    	}
	    }

	    return allRanks;
	}
}
