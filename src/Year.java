// Katherine Hoadley
// The Year class finds the year in which a given name and gender combination was most popular.
// It returns the year, rank, and frequency of the name based on data from the NameDatabase.

import java.util.ArrayList;

public class Year {
	
	//field
	private NameDatabase nameDatabase;
	
	//constructor 
	public Year(NameDatabase nameDatabase) {
		this.nameDatabase = nameDatabase;
	}
	

	// The getYear method finds the year in which the given name and gender had the highest frequency.
	// It returns a string with the most popular year, the rank of the name in that year, and the frequency.
	// If the name is not found in any year, it returns a message indicating that.
	public String getYear(String name, String gender) {	
		int bestYear = -1;		
		int maxFrequency = -1;	
		int bestRank = -1;		
		
		for (Integer year : nameDatabase.babyNameDataStorage.keySet()) {
			
			ArrayList<IndividualNameData> nameList = nameDatabase.getNameList(year, gender);
			
			int rank = 0;
			int namesSeen = 0;
			int prevFrequency = -1;
			
			for(IndividualNameData entry : nameList) {
				namesSeen++;
				
				if (entry.getFrequency() != prevFrequency) {
					rank = namesSeen;
				}
				prevFrequency = entry.getFrequency();
				
				if(entry.getName().equals(name)) {
					if(entry.getFrequency() > maxFrequency) {
						maxFrequency = entry.getFrequency();
						bestYear = year;
						bestRank = rank;
					}
				}
			}
		}
		
		if (bestYear == -1) {
            return "The name " + name + " (gender " + gender + ") was not found in any year.";
		}
		return "The name " + name + ", gender " + gender + ", was most popular in " + bestYear + " with frequency " + maxFrequency + " and rank " + bestRank + ".";
	}
}
