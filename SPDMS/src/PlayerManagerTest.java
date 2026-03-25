/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * March 21, 2026
 * PlayerManagerTest.java
 * This class contains unit tests for the PlayerManager class.
 * It tests CRUD operations including adding, updating, deleting,
 * and searching for soccer players, as well as loading player data
 * from a file.
 */

import org.junit.jupiter.api.BeforeEach;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerManagerTest {

    private PlayerManager manager;
    private SoccerPlayer firstPlayer;
    private SoccerPlayer secondPlayer;

    /**
     * method: testData
     * parameters: none
     * return: void
     * purpose: initializes PlayerManager and two SoccerPlayer objects before each test
     */

    @BeforeEach
    void testData() {
        manager = new PlayerManager();
        firstPlayer = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        secondPlayer = new SoccerPlayer(2, "Erling Haaland", "Forward", "Manchester City", 12, 8, 4, 1050);
    }

    /**
     * method: addPlayer
     * parameters: none
     * return: void
     * purpose: tests that players can be successfully added to the manager
     */

    @Test
    void addPlayer() {
        assertTrue(manager.addPlayer(firstPlayer));
        assertTrue(manager.addPlayer(secondPlayer));
    }

    /**
     * method: verifyDoubleId
     * parameters: none
     * return: void
     * purpose: tests that the manager can detect duplicate player IDs
     */

    @Test
    void verifyDoubleId() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.verifyDoubleId(1));
        assertFalse(manager.verifyDoubleId(99));
    }

    @Test
    void displayAll() {
        manager.addPlayer(firstPlayer);
        manager.addPlayer(secondPlayer);
        assertDoesNotThrow(manager::displayAll);
    }

    @Test
    void findPlayerId() {
        manager.addPlayer(firstPlayer);
        assertEquals(firstPlayer, manager.findPlayerId(1));
        assertNull(manager.findPlayerId(99));
    }

    @Test
    void findPlayerName() {
        manager.addPlayer(firstPlayer);
        assertEquals(firstPlayer, manager.findPlayerName("Kylian Mbappe"));
        assertNull(manager.findPlayerName("Valverde"));
    }

    @Test
    void deletePlayerId() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.deletePlayerId(1));
        assertFalse(manager.deletePlayerId(1));
    }

    @Test
    void deletePlayerName() {
        manager.addPlayer(secondPlayer);
        assertTrue(manager.deletePlayerName("Erling Haaland"));
        assertFalse(manager.deletePlayerName("Erling Haaland"));
    }

    @Test
    void playerNameUpdate() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.playerNameUpdate(1, "Ronaldo"));
        assertEquals("Ronaldo", firstPlayer.getPlayerName());
    }

    @Test
    void playerPositionUpdate() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.playerPositionUpdate(1, "Winger"));
        assertEquals("Winger", firstPlayer.getPlayerPosition());
    }

    @Test
    void playerTeamUpdate() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.playerTeamUpdate(1, "Madrid"));
        assertEquals("Madrid", firstPlayer.getPlayerTeam());
    }

    @Test
    void matchesPlayedUpdate() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.matchesPlayedUpdate(1, 15));
        assertEquals(15, firstPlayer.getMatchesPlayed());
    }

    @Test
    void goalsUpdate() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.goalsUpdate(1, 10));
        assertEquals(10, firstPlayer.getGoalScored());
    }

    @Test
    void assistsUpdate() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.assistsUpdate(1, 6));
        assertEquals(6, firstPlayer.getAssistAmount());
    }

    @Test
    void minutesUpdate() {
        manager.addPlayer(firstPlayer);
        assertTrue(manager.minutesUpdate(1, 1200));
        assertEquals(1200, firstPlayer.getMinutesPlayed());
    }

    /**
     * method: fileUploadTest
     * parameters: none
     * return: void
     * purpose: tests that players can be loaded correctly from a temporary file
     */

    @Test
    void fileUploadTest() throws IOException {
        Path temporaryFile = Files.createTempFile("players", ".txt");
        String list = "1,Kylian Mbappe,Forward,PSG,10,5,3,900\n" +
                "2,Erling Haaland,Forward,Manchester City,12,8,4,1050";
        Files.write(temporaryFile, list.getBytes());

        assertDoesNotThrow(() -> manager.fileUpload(temporaryFile.toString()));
        assertEquals("Kylian Mbappe", manager.findPlayerId(1).getPlayerName());
        assertEquals("Erling Haaland", manager.findPlayerId(2).getPlayerName());

        Files.deleteIfExists(temporaryFile);
    }

    /**
     * method: fileUploadNotFound
     * parameters: none
     * return: void
     * purpose: tests that uploading a nonexistent file does not crash the program
     */

    @Test
    void fileUploadNotFound() {
        assertDoesNotThrow(() -> manager.fileUpload("nonexistent_file.txt"));
    }

    /**
     * method: mainMenuAddPlayer
     * parameters: none
     * return: void
     * purpose: simulates adding a player through the main menu and checks for success message
     */

    @Test
    void mainMenuAddPlayer() {
        String testInput = String.join("\n",
                "2",
                "1",
                "Kylian Mbappe",
                "Forward",
                "PSG",
                "10",
                "5",
                "3",
                "900",
                "8"
        );

        ByteArrayInputStream input = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertDoesNotThrow(() -> Main.main(new String[]{}));

        String outputMessage = output.toString();
        assertTrue(outputMessage.contains("New player has been added successfully"));
    }
}