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

    /**
     * constructor: SoccerPlayer
     * purpose: creates a soccer player object with all player details
     * @param id the player Id
     * @param name the player name
     * @param position the player's field position
     * @param team the player's team
     * @param matches the total matches played
     * @param goals the total goals scored
     * @param assists the total assists made
     * @param minutes the total minutes played
     */

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
     * purpose: returns the player Id
     * @return the player Id
     */

    public int getPlayerId() {
        return playerId;
    }

    /**
     * method: getPlayerName
     * parameters: none
     * purpose: returns the player name
     * @return the player name
     */

    public String getPlayerName() {
        return playerName;
    }

    /**
     * method: setPlayerName
     * purpose: updates the player name
     * @param name the new player name
     */

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    /**
     * method: setPlayerPosition
     * purpose: updates the player position
     * @param position the new player position
     */

    public void setPlayerPosition(String position) {
        this.playerPosition = position;
    }

    /**
     * method: setPlayerTeam
     * purpose: updates the player team
     * @param team the new player team
     */

    public void setPlayerTeam(String team) {
        this.playerTeam = team;
    }

    /**
     * method: setMatchesPlayed
     * purpose: updates matches played
     * @param matches the total matches played
     */

    public void setMatchesPlayed(int matches) {
        this.matchesPlayed = matches;
    }

    /**
     * method: setGoalScored
     * purpose: updates goals scored
     * @param goals total goals scored
     */

    public void setGoalScored(int goals) {
        this.goalScored = goals;
    }

    /**
     * method: setAssistAmount
     * purpose: updates assists
     * @param assists total assists made
     */

    public void setAssistAmount(int assists) {
        this.assistAmount = assists;
    }

    /**
     * method: setMinutesPlayed
     * purpose: updates the minutes played
     * @param minutes total minutes played
     */

    public void setMinutesPlayed(int minutes) {
        this.minutesPlayed = minutes;
    }

    /**
     * method: goalPerMatch
     * parameters: none
     * purpose: calculates and returns goals per match
     * @return goals scored per match
     */

    public double goalPerMatch() {
        if (matchesPlayed == 0) return 0;
        return (double) goalScored / matchesPlayed;
    }

    /**
     * method: assistPerMatch
     * parameters: none
     * purpose: calculates and returns assists per match
     * @return assists per match
     */

    public double assistPerMatch() {
        if(matchesPlayed == 0) return 0;
        return (double) assistAmount / matchesPlayed;
    }

    /**
     * method: minutesPerGoal
     * parameters: none
     * purpose: calculates average minutes needed per goal
     * @return average minutes per goal
     */

    public double minutesPerGoal() {
        if(goalScored == 0) return 0;
        return (double) minutesPlayed / goalScored;
    }

    /**
     * method: goalInvolvement
     * parameters: none
     * purpose: calculates goal involvement per match
     * @return average goal involvement per match
     */

    public double goalInvolvement() {
        if(matchesPlayed == 0) return 0;
        return (double)(goalScored + assistAmount) / matchesPlayed;
    }

    /**
     * method: performanceRating
     * parameters: none
     * purpose: calculates and returns the player's performance rating
     * @return calculated performance rating
     */

    public double performanceRating() {
        if(matchesPlayed == 0) return 0;
        return (goalScored * 3) + (assistAmount * 1) + (minutesPlayed / matchesPlayed);
    }

    /**
     * method: displayPlayerRecord
     * parameters: none
     * purpose: displays all player information in a readable format
     * @return formatted player record
     */

    public String displayPlayerRecord() {
        StringBuilder playerRecord = new StringBuilder();
        playerRecord.append(playerName).append("\n");
        playerRecord.append("******************************\n");
        playerRecord.append("Id:             ").append(playerId).append("\n");
        playerRecord.append("Position:       ").append(playerPosition).append("\n");
        playerRecord.append("Team:           ").append(playerTeam).append("\n");
        playerRecord.append("Matches played: ").append(matchesPlayed).append("\n");
        playerRecord.append("Goals scored:   ").append(goalScored).append("\n");
        playerRecord.append("Assists:        ").append(assistAmount).append("\n");
        playerRecord.append("Minutes played: ").append(minutesPlayed).append("\n\n");
        return playerRecord.toString();
    }


    /**
     * method: displayStats
     * parameters: none
     * purpose: displays all calculated performance statistics of the player
     * @return formatted player statistics
     */

    public String displayStats() {
        return String.format("Statistics for the player: %s\n" +
                        "***********************************\n" +
                        "Goal per match:             %.2f\n" +
                        "Assist per match:           %.2f\n" +
                        "Minutes per goal:           %.2f\n" +
                        "Goal involvement rate:      %.2f\n" +
                        "Performance rating:         %.2f\n",
                playerName, goalPerMatch(), assistPerMatch(), minutesPerGoal(), goalInvolvement(), performanceRating());
    }

    /**
     * method: getPlayerPosition
     * parameters: none
     * purpose: returns the player position
     * @return the player position
     */

    public String getPlayerPosition() {
        return playerPosition;
    }

    /**
     * method: getPlayerTeam
     * parameters: none
     * purpose: returns the player team
     * @return the player team
     */

    public String getPlayerTeam() {
        return playerTeam;
    }

    /**
     * method: getMatchesPlayed
     * parameters: none
     * purpose: returns the number of matches played
     * @return the number of matches played
     */

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * method: getGoalScored
     * parameters: none
     * purpose: returns the number of goals scored
     * @return the number of goals scored
     */

    public int getGoalScored() {
        return goalScored;
    }

    /**
     * method: getAssistAmount
     * parameters: none
     * purpose: returns the number of assists
     * @return the number of assists
     */

    public int getAssistAmount() {
        return assistAmount;
    }

    /**
     * method: getMinutesPlayed
     * parameters: none
     * purpose: returns the number of minutes played
     * @return the number of minutes played
     */

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

}
