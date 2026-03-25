/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * March 3, 2026
 * SoccerPlayer.java
 * This class represents a soccer player and stores player information
 * such as ID, name, team, position, matches played, goals, assists,
 * and minutes played. It also calculates player performance statistics.
 */

public class SoccerPlayer {
    private int playerId;
    private String playerName;
    private String playerTeam;
    private String playerPosition;
    private int matchesPlayed;
    private int goalScored;
    private int assistAmount;
    private int minutesPlayed;

    public SoccerPlayer(int id, String name, String position, String team, int matches, int goals, int assists, int minutes) {
        this.playerId = id;
        this.playerName = name;
        this.playerPosition = position;
        this.matchesPlayed = matches;
        this.goalScored = goals;
        this.assistAmount = assists;
        this.minutesPlayed = minutes;
        this.playerTeam = team;
    }

    /**
     * method: getPlayerId
     * parameters: none
     * return: int
     * purpose: returns the player Id
     */

    public int getPlayerId() {
        return playerId;
    }

    /**
     * method: getPlayerName
     * parameters: none
     * return: String
     * purpose: returns the player name
     */

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public void setPlayerPosition(String position) {
        this.playerPosition = position;
    }

    public void setPlayerTeam(String team) {
        this.playerTeam = team;
    }

    public void setMatchesPlayed(int matches) {
        this.matchesPlayed = matches;
    }

    public void setGoalScored(int goals) {
        this.goalScored = goals;
    }

    public void setAssistAmount(int assists) {
        this.assistAmount = assists;
    }

    /**
     * method: setMinutesPlayed
     * parameters: int minutes
     * return: void
     * purpose: updates the minutes played
     */

    public void setMinutesPlayed(int minutes) {
        this.minutesPlayed = minutes;
    }

    /**
     * method: goalPerMatch
     * parameters: none
     * return: double
     * purpose: calculates and returns goals per match
     */

    public double goalPerMatch() {
        if (matchesPlayed == 0) return 0;
        return (double) goalScored / matchesPlayed;
    }

    /**
     * method: assistPerMatch
     * parameters: none
     * return: double
     * purpose: calculates and returns assists per match
     */

    public double assistPerMatch() {
        if(matchesPlayed == 0) return 0;
        return (double) assistAmount / matchesPlayed;
    }

    public double minutesPerGoal() {
        if(goalScored == 0) return 0;
        return (double) minutesPlayed / goalScored;
    }

    public double goalInvolvement() {
        if(matchesPlayed == 0) return 0;
        return (double)(goalScored + assistAmount) / matchesPlayed;
    }

    /**
     * method: performanceRating
     * parameters: none
     * return: double
     * purpose: calculates and returns the player's performance rating
     */

    public double performanceRating() {
        if(matchesPlayed == 0) return 0;
        return (goalScored * 3) + (assistAmount * 1) + (minutesPlayed / matchesPlayed);
    }

    /**
     * method: displayPlayerRecord
     * parameters: none
     * return: void
     * purpose: displays all player information in a readable format
     */

    public void displayPlayerRecord() {
        System.out.println( playerName);
        System.out.println("******************************");
        System.out.println("Id:             " + playerId);
        System.out.println("Position:       " + playerPosition);
        System.out.println("Team:           " + playerTeam);
        System.out.println("Matches played: " + matchesPlayed);
        System.out.println("Goals scored:   " + goalScored);
        System.out.println("Assists:        " + assistAmount);
        System.out.println("Minutes played: " + minutesPlayed);
        System.out.println();
        System.out.println();
    }

    /**
     * method: displayStats
     * parameters: none
     * return: void
     * purpose: displays all calculated performance statistics of the player
     */

    public void displayStats() {
        System.out.println("Statistics for the player: " + playerName);
        System.out.println("***********************************");
        System.out.printf("Goal per match:             %.2f%n", goalPerMatch());
        System.out.printf("Assist per match:           %.2f%n", assistPerMatch());
        System.out.printf("Minutes per goal:           %.2f%n", minutesPerGoal());
        System.out.printf("Goal involvement rate:      %.2f%n", goalInvolvement());
        System.out.printf("Performance rating:         %.2f%n", performanceRating());
    }

    /**
     * The following getter methods are for testing purposes only as the program doesn't require them:

     * getPlayerPosition
     * parameters: none
     * return: String
     * purpose: returns the player position

     * getPlayerTeam
     * parameters: none
     * return: String
     * purpose: returns the player team

     * getMatchesPlayed
     * parameters: none
     * return: int
     * purpose: returns the number of matches played

     * getGoalScored
     * parameters: none
     * return: int
     * purpose: returns the number of goals scored

     * getAssistAmount
     * parameters: none
     * return: int
     * purpose: returns the number of assists

     * getMinutesPlayed
     * parameters: none
     * return: int
     * purpose: returns the number of minutes played
     */



    public String getPlayerPosition() {
        return playerPosition;
    }

    public String getPlayerTeam() {
        return playerTeam;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getGoalScored() {
        return goalScored;
    }

    public int getAssistAmount() {
        return assistAmount;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

}
