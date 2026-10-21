// Katherine Hoadley
// The TestAverageRank class contains unit tests for the AverageRank class.
// It verifies the correctness of average rank calculations across different year ranges,
// including valid cases for known names and edge cases such as invalid year ranges.


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class TestAverageRank {

    private static NameDatabase nameDatabase;
    private static AverageRank averageRank;

    @BeforeAll
    static void setup() {
        nameDatabase = new NameDatabase();
        nameDatabase.loadData(new File("RealData"));
        averageRank = new AverageRank(nameDatabase);
    }
    
    @Test
    void testJohn() {
    	 int avg = averageRank.getAverageRank("John", "M", 2010, 2015);
    	 System.out.println(avg);
    }
    
    @Test
    void testOliviaAverageRank() {
        // Olivia,F: 2025 rank 1, 2026 rank 1, 2027 rank 2
        int avg = averageRank.getAverageRank("Olivia", "F", 2025, 2027);
        assertEquals((1 + 1 + 2)/3, avg, "Average rank should be correct for Olivia");
    }

    @Test
    void testEmmaAverageRank() {
        // Emma,F: 2025 rank 2, 2026 not present, 2027 rank 3
        int avg = averageRank.getAverageRank("Emma", "F", 2025, 2027);
        assertEquals((2 + 3)/2, avg, "Average rank should ignore missing years (2026)");
    }

    @Test
    void testLiamAverageRank() {
        // Liam,M: 2025 rank 1, 2026 rank 1, 2027 rank 1
        int avg = averageRank.getAverageRank("Liam", "M", 2025, 2027);
        assertEquals(1, avg, "Liam should have rank 1 consistently");
    }

    @Test
    void testNoahAverageRank() {
        // Noah,M: 2025 rank 2, 2026 rank 2, 2027 rank 2
        int avg = averageRank.getAverageRank("Noah", "M", 2025, 2027);
        assertEquals(2, avg, "Noah should have rank 2 consistently");
    }

    @Test
    void testNameNotInData() {
        assertThrows(IllegalArgumentException.class, () -> {
            averageRank.getAverageRank("Xxx", "M", 2025, 2027);
        });
    }

    @Test
    void testStartYearAfterEndYear() {
        assertThrows(IllegalArgumentException.class, () -> {
            averageRank.getAverageRank("Olivia", "F", 2027, 2025);
        });
    }
}