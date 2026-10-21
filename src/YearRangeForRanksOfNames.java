// Katherine Hoadley
// The YearRangeForRanksOfNames class returns the rank information for a given name and gender
// across a specified range of years using data from the NameDatabase.


import java.util.ArrayList;
public class YearRangeForRanksOfNames {
	
	//fields
	private NameDatabase nameDatabase;
	private Rank rank;
	final int FIRST_YEAR = 1880;
	final int LAST_YEAR = 2025;
		
	//constructor
	public YearRangeForRanksOfNames(NameDatabase nameDatabase) {
		this.nameDatabase = nameDatabase;
	    this.rank = new Rank(nameDatabase);
	}
	

	// The getYearRangeForRanksOfNames method returns a list of strings, each representing the rank
	// and frequency of the given name and gender for each year in the specified range.
	// Throws an IllegalArgumentException if the year range is invalid.
	public ArrayList<String> getYearRangeForRanksOfNames(String name, String gender, int startYear, int endYear){
		ArrayList<String> allRanks = new ArrayList<>();
		
        if (startYear < FIRST_YEAR || endYear > LAST_YEAR || startYear > endYear) {
            throw new IllegalArgumentException("Year range must be between 1880 and 2025, and start <= end.");
        }
        
        for (int year = startYear; year <= endYear; year++) {
            String yearRankInfo = rank.toString(name, year, gender);
            if (yearRankInfo != null) {
                allRanks.add(year + ": " + yearRankInfo);
            }
        }
        
        return allRanks;
	}
}
