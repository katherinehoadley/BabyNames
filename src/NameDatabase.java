//Katherine Hoadley
// The NameDatabase class is responsible for loading and storing baby name data
// from text files spanning the years 1880 to 2024. Each file contains name, gender,
// and frequency information for a given year. The data is organized in a nested
// HashMap structure: year → gender → list of IndividualNameData objects.
// This class provides a method to load all data files and a method to retrieve
// the list of name entries for a specific year and gender to deal with
// levels of abstraction.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NameDatabase {
	HashMap<Integer, HashMap<String, ArrayList<IndividualNameData>>> babyNameDataStorage = new HashMap<>();
	
	//fields
	final int NAME_INDEX = 0;
	final int GENDER_INDEX = 1;
	final int FREQUENCY_INDEX = 2;
	final int REAL_FIRST_YEAR = 1880;
	final int REAL_LAST_YEAR = 2024;
	final int FAKE_FIRST_YEAR = 2025;
	final int FAKE_LAST_YEAR = 2027;

	/*
	 * Loads baby name data from text files for each year between FIRST_YEAR and LAST_YEAR.
	 * 
	 * For each year:
	 * - Constructs the filename using the year (e.g., "yob1880.txt").
	 * - Reads each line of the file, which contains name, gender, and frequency.
	 * - Creates an IndividualNameData object for each line.
	 * - Organizes the data into a nested HashMap structure:
	 *   year → gender → list of IndividualNameData objects.
	 * 
	 * If a file is missing, a warning is printed but the program continues loading other years.
	 */
	void loadData(File dataFile) {	//pick between directories
		babyNameDataStorage.clear();
		int firstYear = 0;
		int lastYear = 0;
		
		if (dataFile.getName().equalsIgnoreCase("RealData")) {
		    firstYear = REAL_FIRST_YEAR;
		    lastYear = REAL_LAST_YEAR;
		} else if (dataFile.getName().equalsIgnoreCase("FakeData")) {
		    firstYear = FAKE_FIRST_YEAR;
		    lastYear = FAKE_LAST_YEAR;
		} else {
		    throw new IllegalArgumentException("Unknown data folder: " + dataFile.getName());
		}
		
		for(int year = firstYear; year <= lastYear; year++) {	
			String fileName = dataFile + "/yob" + year + ".txt";	
			
			try (Scanner scanner = new Scanner(new File(fileName))) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] nameGenderFreq = line.split(",");	
					String nameInFile = nameGenderFreq[NAME_INDEX];		
					String genderInFile = nameGenderFreq[GENDER_INDEX];	
					int frequencyInFile = Integer.parseInt(nameGenderFreq[FREQUENCY_INDEX]);	
					IndividualNameData nameEntry = new IndividualNameData(nameInFile, genderInFile, year, frequencyInFile);
					
					if (!babyNameDataStorage.containsKey(year)) {
						babyNameDataStorage.put(year, new HashMap<>());
					}
					if (!babyNameDataStorage.get(year).containsKey(genderInFile)) {
					    babyNameDataStorage.get(year).put(genderInFile, new ArrayList<>());
					}
					babyNameDataStorage.get(year).get(genderInFile).add(nameEntry);
					
				}
					
			} 
			
			catch (FileNotFoundException e) {
			    System.out.println("Warning: File not found for year " + year + " (" + fileName + ")");
			}
			
		}
		
	}
	

	/*
	 * Retrieves the list of IndividualNameData entries for a specific year and gender.
	 * 
	 * Parameters:
	 * - year: the year to look up
	 * - gender: the gender to look up ("M" or "F")
	 * 
	 * Returns:
	 * - The list of name entries for the given year and gender, or null if no data is available.
	 */
	public ArrayList<IndividualNameData> getNameList(int year, String gender) {	
        if (!babyNameDataStorage.containsKey(year)) {
            return null;
        }
        if (!babyNameDataStorage.get(year).containsKey(gender)) {
            return null;
        }
        return babyNameDataStorage.get(year).get(gender);
    }
	
}
