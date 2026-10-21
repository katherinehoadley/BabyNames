//Katherine Hoadley
// The TestYearRangeForRanksOfNames class contains unit tests for the YearRangeForRanksOfNames class.
//It verifies that name frequency and rank data is correctly retrieved across a specified year range,
//and checks for proper exception handling when given invalid year ranges or input parameters.


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestYearRangeForRanksOfNames {

	private static YearRangeForRanksOfNames yearRangeForRanksOfNames;

    @BeforeAll
    public static void setup() {
        NameDatabase nameDatabase = new NameDatabase();
        nameDatabase.loadData(new File("RealData"));
        yearRangeForRanksOfNames = new YearRangeForRanksOfNames(nameDatabase);
    }  
    
    @Test
    void testJohn() {
    	ArrayList<String> result = yearRangeForRanksOfNames.getYearRangeForRanksOfNames("John", "M", 2010, 2015);
    	System.out.println(result);
    }
    
    @Test
    void testValidInputProducesCorrectOutput() {
        ArrayList<String> result = yearRangeForRanksOfNames.getYearRangeForRanksOfNames("Olivia", "F", 2025, 2027);

        assertEquals(3, result.size(), "Expected 3 results for Olivia from 2025–2027");

        // 2025
        assertTrue(result.get(0).startsWith("2025:"), "Should start with year 2025");
        assertTrue(result.get(0).contains("Olivia"), "Should mention Olivia");
        assertTrue(result.get(0).contains("F"), "Should mention gender F");
        assertTrue(result.get(0).contains("12000"), "Should contain frequency 12000");

        // 2026
        assertTrue(result.get(1).startsWith("2026:"), "Should start with year 2026");
        assertTrue(result.get(1).contains("11800"), "Should contain frequency 11800");

        // 2027
        assertTrue(result.get(2).startsWith("2027:"), "Should start with year 2027");
        assertTrue(result.get(2).contains("11750"), "Should contain frequency 11750");
    }

    @Test
    void testYearRangeOutOfBoundsThrowsException() {
        // Start year too early
        assertThrows(IllegalArgumentException.class, () -> {
            yearRangeForRanksOfNames.getYearRangeForRanksOfNames("Olivia", "F", 2024, 2026);
        });

        // End year too late
        assertThrows(IllegalArgumentException.class, () -> {
            yearRangeForRanksOfNames.getYearRangeForRanksOfNames("Olivia", "F", 2025, 2028);
        });
    }

    @Test
    void testStartYearAfterEndYearThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            yearRangeForRanksOfNames.getYearRangeForRanksOfNames("Olivia", "F", 2026, 2025);
        });
    }
}



