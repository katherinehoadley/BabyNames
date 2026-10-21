// Katherine Hoadley
// The AverageRank class calculates the average rank of a given name and gender
// over a specified range of years using data from a NameDatabase.
// It uses the Rank class to retrieve yearly rankings and computes the average
// only for years where valid data is available.

public class AverageRank {
	//fields
	private NameDatabase nameDatabase;
	private Rank rank;
	final int FIRST_YEAR = 1880;
	final int LAST_YEAR = 2024;
	
	//constructor
	public AverageRank(NameDatabase nameDatabase) {
		this.nameDatabase = nameDatabase;
		this.rank = new Rank(nameDatabase);
	}
	

	/*
	 * Calculates the average rank of a given name and gender over a specified range of years.
	 * 
	 * Parameters:
	 * - name: the name to evaluate
	 * - gender: the gender associated with the name ("M" or "F")
	 * - startYear: the beginning of the year range (inclusive)
	 * - endYear: the end of the year range (inclusive)
	 * 
	 * Behavior:
	 * - Throws an IllegalArgumentException if the year range is outside 1880–2024 or improperly ordered
	 * - Throws an IllegalArgumentException if no valid rank data is found in the range
	 * 
	 * Returns:
	 * - The average rank as an integer, based only on years with valid data
	 */

	public int getAverageRank(String name, String gender, int startYear, int endYear) {
		if(startYear < FIRST_YEAR || endYear > LAST_YEAR || startYear > endYear) {
            throw new IllegalArgumentException(
                "Year range must be between " + FIRST_YEAR + " and " + LAST_YEAR + ", and start <= end."
            );
        }
		
		int totalRank = 0;
		int yearsWithData = 0;
		
		for (int year = startYear; year <= endYear; year++) {
		    int yearlyRank = rank.getRank(name, year, gender);
		    if (yearlyRank > 0) {   
		        totalRank += yearlyRank;
		        yearsWithData++;
		    }
		}
		
		if (yearsWithData == 0) {
			throw new IllegalArgumentException("Name " + name + " (" + gender + ") not found in any years between " + startYear + " and " + endYear);
		}
		
		return totalRank / yearsWithData;
	}
}
