//Katherine Hoadley
// The TestNameGenderPair class contains unit tests for the NameGenderPair class.
// It verifies the correctness of name mappings from previous years to 2024 based on gender and popularity.

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;

class TestNameGenderPair {

    private NameDatabase nameDatabase;
    private NameGenderPair nameGenderPair;

    @BeforeEach
    void setup() {
        nameDatabase = new NameDatabase();
        nameDatabase.loadData(new File("RealData"));
        nameGenderPair = new NameGenderPair(nameDatabase);
    }
    
    @Test
    void testShannon() {
    	String result = nameGenderPair.getNameGenderPair("Shannon", 1976, "F");
    	System.out.println(result);
    }
    @Test
    void mostPopularMapsToMostPopularFemale() {
        // Most popular female in 2025 was Olivia, in 2027 it's Charlotte
        String result = nameGenderPair.getNameGenderPair("Olivia", 2025, "F");
        assertEquals(
            "If Olivia (F), 2025, was born in 2027, the name would be Charlotte (F).",
            result
        );
    }

    @Test
    void mostPopularMapsToMostPopularMale() {
        // Most popular male in 2025 was Liam, in 2027 it's Liam
        String result = nameGenderPair.getNameGenderPair("Liam", 2025, "M");
        assertEquals(
            "If Liam (M), 2025, was born in 2027, the name would be Liam (M).",
            result
        );
    }

    @Test
    void result2027Stays2027() {
        // Name in 2027 should map to itself
        String result = nameGenderPair.getNameGenderPair("Charlotte", 2027, "F");
        assertEquals(
            "If Charlotte (F), 2027, was born in 2027, the name would be Charlotte (F).",
            result
        );
    }

    @Test
    void testThrowsForInvalidYear() {
        assertThrows(IllegalArgumentException.class, () -> {
            nameGenderPair.getNameGenderPair("Olivia", 2024, "F"); // year not in FakeData
        });
    }

    @Test
    void testThrowsForInvalidGender() {
        assertThrows(IllegalArgumentException.class, () -> {
            nameGenderPair.getNameGenderPair("Liam", 2025, "X");
        });
    }
}