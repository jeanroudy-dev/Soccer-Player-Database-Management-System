/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * March 21, 2026
 * SoccerPlayerTest.java
 * This class contains unit tests for the SoccerPlayer class.
 * It verifies that getters and setters work correctly, and that
 * player performance statistics methods return accurate values.
 * It also ensures display methods execute without throwing exceptions.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoccerPlayerTest {

    /**
     * method: getPlayerId
     * parameters: none
     * return: void
     * purpose: tests that getPlayerId returns the correct ID
     */

    @Test
    void getPlayerId() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertEquals(1, player.getPlayerId());
    }

    @org.junit.jupiter.api.Test
    void getPlayerName() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertEquals("Kylian Mbappe", player.getPlayerName());
    }

    /**
     * method: setPlayerName
     * parameters: String name
     * return: void
     * purpose: tests that setPlayerName correctly updates the player's name
     */

    @org.junit.jupiter.api.Test
    void setPlayerName() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        player.setPlayerName("Erling Haaland");
        assertEquals("Erling Haaland", player.getPlayerName());
    }

    @org.junit.jupiter.api.Test
    void setPlayerPosition() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        player.setPlayerPosition("Winger");
        assertEquals("Winger", player.getPlayerPosition());
    }

    @org.junit.jupiter.api.Test
    void setPlayerTeam() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        player.setPlayerTeam("Real Madrid");
        assertEquals("Real Madrid", player.getPlayerTeam());
    }

    @org.junit.jupiter.api.Test
    void setMatchesPlayed() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        player.setMatchesPlayed(12);
        assertEquals(12, player.getMatchesPlayed());
    }

    @org.junit.jupiter.api.Test
    void setGoalScored() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        player.setGoalScored(8);
        assertEquals(8, player.getGoalScored());
    }

    @org.junit.jupiter.api.Test
    void setAssistAmount() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        player.setAssistAmount(6);
        assertEquals(6, player.getAssistAmount());
    }

    @org.junit.jupiter.api.Test
    void setMinutesPlayed() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        player.setMinutesPlayed(1000);
        assertEquals(1000, player.getMinutesPlayed());
    }

    /**
     * method: goalPerMatch
     * parameters: none
     * return: void
     * purpose: verifies that goalPerMatch calculation returns the correct value
     */

    @org.junit.jupiter.api.Test
    void goalPerMatch() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertEquals(0.5, player.goalPerMatch());
    }

    @org.junit.jupiter.api.Test
    void assistPerMatch() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertEquals(0.3, player.assistPerMatch(), 0.01);
    }

    @org.junit.jupiter.api.Test
    void minutesPerGoal() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertEquals(180, player.minutesPerGoal(), 0.01);
    }

    @org.junit.jupiter.api.Test
    void goalInvolvement() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertEquals(0.8, player.goalInvolvement(), 0.01);
    }

    /**
     * method: performanceRating
     * parameters: none
     * return: void
     * purpose: checks that performanceRating calculation returns the expected value
     */

    @org.junit.jupiter.api.Test
    void performanceRating() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertEquals(5*3 + 3*1 + 900/10.0, player.performanceRating(), 0.01);
    }

    /**
     * method: displayPlayerRecord
     * parameters: none
     * return: void
     * purpose: ensures displayPlayerRecord method runs without throwing exceptions
     */

    @org.junit.jupiter.api.Test
    void displayPlayerRecord() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertDoesNotThrow(player::displayPlayerRecord);
    }

    @org.junit.jupiter.api.Test
    void displayStats() {
        SoccerPlayer player = new SoccerPlayer(1, "Kylian Mbappe", "Forward", "PSG", 10, 5, 3, 900);
        assertDoesNotThrow(player::displayStats);
    }
}