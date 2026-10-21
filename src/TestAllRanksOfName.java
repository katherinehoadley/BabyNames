// Katherine Hoadley
// The TestAllRanksOfName class contains unit tests for the AllRanksOfName class.
// It verifies the behavior of the getAllRanksOfName method for both known and unknown names,
// ensuring that valid results are returned when expected and appropriate exceptions are thrown
// when data is missing or invalid.


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestAllRanksOfName {
    
    private static AllRanksOfName allRanksOfName;

    @BeforeAll
    public static void setup() {
        NameDatabase nameDatabase = new NameDatabase();
        nameDatabase.loadData(new File("FakeData"));
        allRanksOfName = new AllRanksOfName(nameDatabase);
    }  


    @Test
    public void testUnknownName() {
    	assertThrows(IllegalArgumentException.class, () -> 
    		allRanksOfName.getAllRanksOfName("Xxx", "M"), "Expected IllegalArgumentException for unknown name");
    }

    
    @Test
    public void testKnownFemaleName() {
        ArrayList<String> ranks = allRanksOfName.getAllRanksOfName("Olivia", "F");

        assertNotNull(ranks, "Result should not be null");
        assertEquals(3, ranks.size(), "Olivia should appear in all 3 fake years");

        // 2025
        assertTrue(ranks.get(0).contains("2025"), "Should include year 2025");
        assertTrue(ranks.get(0).contains("Olivia"), "Should include name Olivia");
        assertTrue(ranks.get(0).contains("12000"), "Should include frequency 12000");

        // 2026
        assertTrue(ranks.get(1).contains("2026"), "Should include year 2026");
        assertTrue(ranks.get(1).contains("11800"), "Should include frequency 11800");

        // 2027
        assertTrue(ranks.get(2).contains("2027"), "Should include year 2027");
        assertTrue(ranks.get(2).contains("11750"), "Should include frequency 11750");
    }
    
    @Test
    void testKnownMaleName() {
        ArrayList<String> result = allRanksOfName.getAllRanksOfName("Liam", "M");

        assertEquals(3, result.size(), "Expected 3 results for Liam from 2025–2027");

        // 2025
        assertTrue(result.get(0).startsWith("2025:"), "Should start with year 2025");
        assertTrue(result.get(0).contains("Liam"), "Should mention Liam");
        assertTrue(result.get(0).contains("M"), "Should mention gender M");
        assertTrue(result.get(0).contains("13000"), "Should contain frequency 13000");

        // 2026
        assertTrue(result.get(1).startsWith("2026:"), "Should start with year 2026");
        assertTrue(result.get(1).contains("13500"), "Should contain frequency 13500");

        // 2027
        assertTrue(result.get(2).startsWith("2027:"), "Should start with year 2027");
        assertTrue(result.get(2).contains("14000"), "Should contain frequency 14000");
    }
    
    @Test
    public void allRanksYuva() {
		ArrayList<String> result = allRanksOfName.getAllRanksOfName("Yuva", "M");
		System.out.println(result);
	}

}