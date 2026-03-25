/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * March 21, 2026
 * MainTest.java
 * This class contains unit tests for the Main class.
 * It verifies input validation methods, and ensures the program executes correctly
 * without crashing.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {
    /**
     * method: main
     * parameters: String[] args
     * return: void
     * purpose: tests the Main class main method runs without crashing when the user chooses to exit immediately
     */

    @Test
    void main() {
        String testInput = "8\n";
        ByteArrayInputStream in = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(in);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    /**
     * method: integerValidation
     * parameters: Scanner scanner
     * return: int
     * purpose: tests that integerValidation correctly reads and returns a valid integer from input
     */

    @Test
    void integerValidation() {
        String testInput = "5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        int result = Main.integerValidation(scanner, "Enter a number: ");
        assertEquals(5, result);
    }

    /**
     * method: textValidation
     * parameters: Scanner scanner
     * return: String
     * purpose: tests that textValidation correctly reads and returns a valid string from input
     */

    @Test
    void textValidation() {
        String testInput = "Kylian Mbappe\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        String result = Main.textValidation(scanner, "Enter name: ");
        assertEquals("Kylian Mbappe", result);
    }
}