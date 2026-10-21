// Katherine Hoadley
// The TestPopularLetter class contains unit tests for the PopularLetter class.
// It verifies the correctness of identifying the most popular starting letter for names
// across different year ranges and genders, using data from the NameDatabase.

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestPopularLetter {

    private static NameDatabase nameDatabase;
    private static PopularLetter popularLetter;

    @BeforeAll
    static void setup() {
    	nameDatabase = new NameDatabase();
    	nameDatabase.loadData(new File("RealData"));
        popularLetter = new PopularLetter(nameDatabase);
    }
    
    @Test
    void test2010to2013() {
    	String result = popularLetter.getPopularLetter(2010, 2013, "F");
    	System.out.println(result);
    }
    
    @Test
    void test1885to1890() {
    	String result = popularLetter.getPopularLetter(1885, 1890, "F");
    	System.out.println(result);
    }

    @Test
    void testFemale2025to2027() {
        String result = popularLetter.getPopularLetter(2025, 2027, "F");
        assertNotNull(result);
        // Most popular female starting letter across 2025-2027: "O" for Olivia / Olivia / Olivia
        assertTrue(result.contains("O") || result.contains("C"), "Expected most popular letter to be O or C for F");
        System.out.println("Female 2025–2027: " + result);
    }

    @Test
    void testMale2025to2027() {
        String result = popularLetter.getPopularLetter(2025, 2027, "M");
        assertNotNull(result);
        // Most popular male starting letter across 2025-2027: "L" for Liam / Liam / Liam
        assertTrue(result.contains("L"), "Expected most popular letter to be L for M");
        System.out.println("Male 2025–2027: " + result);
    }

    @Test
    void testFemale2026to2026() {
        String result = popularLetter.getPopularLetter(2026, 2026, "F");
        assertNotNull(result);
        // 2026 top female name: Olivia, starting with O
        assertTrue(result.contains("O"), "Expected most popular letter to be O for F in 2026");
        System.out.println("Female 2026: " + result);
    }

    @Test
    void testMale2027to2027() {
        String result = popularLetter.getPopularLetter(2027, 2027, "M");
        assertNotNull(result);
        // 2027 top male name: Liam, starting with L
        assertTrue(result.contains("L"), "Expected most popular letter to be L for M in 2027");
        System.out.println("Male 2027: " + result);
    }
}