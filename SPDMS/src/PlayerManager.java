/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * March 3, 2026
 * PlayerManager.java
 * This class manages the collection of soccer players and performs
 * operations such as adding, searching, deleting, displaying players,
 * and loading player data from a file.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class PlayerManager {
    private ArrayList<SoccerPlayer> playerList;

    public PlayerManager() {
        playerList = new ArrayList<>();
    }

    /**
     * method: addPlayer
     * parameters: SoccerPlayer player
     * return: boolean
     * purpose: adds a player to the player list
     */

    public boolean addPlayer(SoccerPlayer player) {
        playerList.add(player);
        return true;
    }

    /**
     * method: verifyDoubleId
     * parameters: int id
     * return: boolean
     * purpose: checks if the player Id already exists in the list
     */

    public boolean verifyDoubleId(int id) {
        for (SoccerPlayer player : playerList) {
            if (player.getPlayerId() == id) {
                return true;
            };
        }
        return false;
    }

    /**
     * method: displayAll
     * parameters: none
     * return: void
     * purpose: displays all players in the player list
     */

    public void displayAll() {

        if(playerList.isEmpty()) {
            System.out.println("There is no record of players available.");
            return;
        }

        for(SoccerPlayer player : playerList) {
            player.displayPlayerRecord();
        }
    }

    /**
     * method: findPlayerId
     * parameters: int id
     * return: SoccerPlayer
     * purpose: searches and returns a player by their Id
     */

    public SoccerPlayer findPlayerId(int id) {

        for(SoccerPlayer player : playerList) {
            if(player.getPlayerId() == id)
                return player;
        }
        System.out.println("There is no record of this Id of player available.");
        return null;
    }

    /**
     * method: findPlayerName
     * parameters: String name
     * return: SoccerPlayer
     * purpose: searches and returns a player by their name
     */

    public SoccerPlayer findPlayerName(String name) {
        for(SoccerPlayer player : playerList) {
            if(player.getPlayerName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * method: deletePlayerId
     * parameters: int id
     * return: boolean
     * purpose: deletes a player from the list by Id
     */

    public boolean deletePlayerId(int id) {

        SoccerPlayer player = findPlayerId(id);

        if(player != null) {
            playerList.remove(player);
            return true;
        }

        return false;
    }

    /**
     * method: deletePlayerName
     * parameters: String name
     * return: boolean
     * purpose: deletes a player from the list by name
     */

    public boolean deletePlayerName(String name) {

        SoccerPlayer player = findPlayerName(name);

        if(player != null) {
            playerList.remove(player);
            return true;
        }

        return false;
    }

    /**
     * method: playerNameUpdate
     * parameters: int id, String name
     * return: boolean
     * purpose: updates the name of a player with the given Id
     */

    public boolean playerNameUpdate(int id, String name) {
        SoccerPlayer player = findPlayerId(id);
        if (player != null) {
            player.setPlayerName(name);
            return true;
        }
        return false;
    }

    /**
     * method: playerPositionUpdate
     * parameters: int id, String position
     * return: boolean
     * purpose: updates the position of a player with the given Id
     */

    public boolean playerPositionUpdate(int id, String position) {
        SoccerPlayer player = findPlayerId(id);
        if (player != null) {
            player.setPlayerPosition(position);
            return true;
        }
        return false;
    }

    /**
     * method: playerTeamUpdate
     * parameters: int id, String team
     * return: boolean
     * purpose: updates the team of a player with the given Id
     */

    public boolean playerTeamUpdate(int id, String team) {
        SoccerPlayer player = findPlayerId(id);
        if (player != null) {
            player.setPlayerTeam(team);
            return true;
        }
        return false;
    }

    /**
     * method: matchesPlayedUpdate
     * parameters: int id, int matches
     * return: boolean
     * purpose: updates the number of matches played for a player with the given Id
     */

    public boolean matchesPlayedUpdate(int id, int matches) {
        SoccerPlayer player = findPlayerId(id);
        if (player != null) {
            player.setMatchesPlayed(matches);
            return true;
        }
        return false;
    }

    /**
     * method: goalsUpdate
     * parameters: int id, int goals
     * return: boolean
     * purpose: updates the number of goals scored for a player with the given Id
     */

    public boolean goalsUpdate(int id, int goals) {
        SoccerPlayer player = findPlayerId(id);
        if (player != null) {
            player.setGoalScored(goals);
            return true;
        }
        return false;
    }

    /**
     * method: assistsUpdate
     * parameters: int id, int assists
     * return: boolean
     * purpose: updates the number of assists for a player with the given Id
     */

    public boolean assistsUpdate(int id, int assists) {
        SoccerPlayer player = findPlayerId(id);
        if (player != null) {
            player.setAssistAmount(assists);
            return true;
        }
        return false;
    }

    /**
     * method: minutesUpdate
     * parameters: int id, int minutes
     * return: boolean
     * purpose: updates the number of minutes played for a player with the given Id
     */

    public boolean minutesUpdate(int id, int minutes) {
        SoccerPlayer player = findPlayerId(id);
        if (player != null) {
            player.setMinutesPlayed(minutes);
            return true;
        }
        return false;
    }

    /**
     * method: fileUpload
     * parameters: String soccerDataFile
     * return: void
     * purpose: loads players from a .txt file into the list
     */


    public void fileUpload (String soccerDataFile) {

        if(!soccerDataFile.endsWith(".txt")) {
            System.out.println("Your file is not a .txt file and could not be read!!!");
            System.out.println("Please upload only .txt files.");
            return;
        }

        try {

            File newFile = new File(soccerDataFile);
            Scanner scan = new Scanner(newFile);

            while(scan.hasNextLine()) {

                String line = scan.nextLine();

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String position = data[2];
                String team = data[3];
                int matches = Integer.parseInt(data[4]);
                int goals = Integer.parseInt(data[5]);
                int assists = Integer.parseInt(data[6]);
                int minutes = Integer.parseInt(data[7]);

                SoccerPlayer player = new SoccerPlayer(id, name, position, team,
                        matches, goals, assists, minutes);

                if(!verifyDoubleId(id)) {
                    playerList.add(player);
                }
            }

            scan.close();

            System.out.println("Your soccer data file have been loaded successfully.");

        } catch(Exception e) {
            System.out.println("There is an error reading the file. Please try again.");
        }
    }
}
