//Katherine Hoadley
// The IndividualNameData class represents a single record from the baby name dataset.
// Each instance stores the name, gender, year, and frequency of occurrence.
// This class provides getter methods to access each field.
public class IndividualNameData {
	//fields
	String name;
	String gender;
	int year;
	int frequency;
	
	//constructor
	public IndividualNameData(String name, String gender, int year, int frequency){
		this.name = name;
		this.gender = gender;
		this.year = year;
		this.frequency = frequency;
	}
	
	//getters
	public String getName() {
		return name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getFrequency() {
		return frequency;
	}
}
