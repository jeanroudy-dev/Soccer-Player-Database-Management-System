import java.sql.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * April 3, 2026
 * PlayerManager.java
 * This class manages soccer player data storage, retrieval,
 * updates, and deletion using a MySQL database.
 * It serves as the connection between the GUI and the database layer.
 */


public class PlayerManager {

    private Connection connect = DatabaseManager.getConnection();

    /**
     * constructor: PlayerManager
     * parameters: none
     * purpose: initializes the player manager object
     * and prepares access to the database connection
     */

    public PlayerManager() {}

    /**
     * method: fileUpload
     * purpose: reads a text file and loads valid player records into the database
     * @param filePath the path of the selected text file
     * @return success or error message after processing the file
     */

    public String fileUpload(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return "The file was not found.";
        if (!filePath.endsWith(".txt")) return "Please select a valid .txt file.";

        int count = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length < 8) continue;

                int id = Integer.parseInt(data[0].trim());

                if (!verifyDoubleId(id)) {
                    SoccerPlayer player = new SoccerPlayer(
                            id,
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            Integer.parseInt(data[4].trim()),
                            Integer.parseInt(data[5].trim()),
                            Integer.parseInt(data[6].trim()),
                            Integer.parseInt(data[7].trim())
                    );

                    if (addPlayer(player)) count++;
                }
            }
            return "Successfully loaded " + count + " new players into the database.";
        } catch (Exception e) {
            return "Error to process players file: " + e.getMessage();
        }
    }

    /**
     * method: getPlayerList
     * parameters: none
     * purpose: retrieves all soccer players from the database
     * @return list of all soccer players
     */

    public ArrayList<SoccerPlayer> getPlayerList() {
        ArrayList<SoccerPlayer> players = new ArrayList<>();
        String sql = "SELECT * FROM players";

        try (Statement statement = connect.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                players.add(new SoccerPlayer(
                        result.getInt("player_id"),
                        result.getString("player_name"),
                        result.getString("player_position"),
                        result.getString("player_team"),
                        result.getInt("matches_played"),
                        result.getInt("goals_scored"),
                        result.getInt("assists"),
                        result.getInt("minutes_played")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * method: addPlayer
     * purpose: adds a new soccer player into the database
     * @param player the player object to be added
     * @return true if the player was added successfully, otherwise false
     */

    public boolean addPlayer(SoccerPlayer player) {
        String sql = "INSERT INTO players " +
                "(player_id, player_name, player_position, player_team, " +
                "matches_played, goals_scored, assists, minutes_played) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement prepstatement = connect.prepareStatement(sql)) {
            prepstatement.setInt(1, player.getPlayerId());
            prepstatement.setString(2, player.getPlayerName());
            prepstatement.setString(3, player.getPlayerPosition());
            prepstatement.setString(4, player.getPlayerTeam());
            prepstatement.setInt(5, player.getMatchesPlayed());
            prepstatement.setInt(6, player.getGoalScored());
            prepstatement.setInt(7, player.getAssistAmount());
            prepstatement.setInt(8, player.getMinutesPlayed());

            return prepstatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * method: deletePlayerId
     * purpose: deletes a player record by Id
     * @param id the player Id
     * @return true if deleted successfully, otherwise false
     */

    public boolean deletePlayerId(int id) {
        String sql = "DELETE FROM players WHERE player_id = ?";
        try (PreparedStatement prepstatement = connect.prepareStatement(sql)) {
            prepstatement.setInt(1, id);
            return prepstatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * method: verifyDoubleId
     * purpose: checks whether a player Id already exists
     * @param id the player Id to verify
     * @return true if the Id already exists, otherwise false
     */

    public boolean verifyDoubleId(int id) {
        String sql = "SELECT player_id FROM players WHERE player_id = ?";
        try (PreparedStatement prepstatement = connect.prepareStatement(sql)) {
            prepstatement.setInt(1, id);
            try (ResultSet result = prepstatement.executeQuery()) {
                return result.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * method: findPlayerId
     * purpose: finds and returns a player using the player Id
     * @param id the player Id
     * @return the matching SoccerPlayer object or null if not found
     */

    public SoccerPlayer findPlayerId(int id) {
        String sql = "SELECT * FROM players WHERE player_id = ?";
        try (PreparedStatement prepstatement = connect.prepareStatement(sql)) {
            prepstatement.setInt(1, id);
            try (ResultSet result = prepstatement.executeQuery()) {
                if (result.next()) {
                    return new SoccerPlayer(
                            result.getInt("player_id"),
                            result.getString("player_name"),
                            result.getString("player_position"),
                            result.getString("player_team"),
                            result.getInt("matches_played"),
                            result.getInt("goals_scored"),
                            result.getInt("assists"),
                            result.getInt("minutes_played")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method: playerNameUpdate
     * purpose: updates a player's name
     * @param id the player Id
     * @param name the new player name
     * @return true if updated successfully, otherwise false
     */

    public boolean playerNameUpdate(int id, String name) {
        return executeUpdate("UPDATE players SET player_name = ? WHERE player_id = ?", name, id);
    }

    /**
     * method: playerPositionUpdate
     * purpose: updates a player's position
     * @param id the player Id
     * @param pos the new player position
     * @return true if updated successfully, otherwise false
     */

    public boolean playerPositionUpdate(int id, String pos) {
        return executeUpdate("UPDATE players SET player_position = ? WHERE player_id = ?", pos, id);
    }

    /**
     * method: playerTeamUpdate
     * purpose: updates a player's team
     * @param id the player Id
     * @param team the new player team
     * @return true if updated successfully, otherwise false
     */

    public boolean playerTeamUpdate(int id, String team) {
        return executeUpdate("UPDATE players SET player_team = ? WHERE player_id = ?", team, id);
    }

    /**
     * method: matchesPlayedUpdate
     * purpose: updates matches played
     * @param id the player Id
     * @param val the updated match value
     * @return true if updated successfully, otherwise false
     */

    public boolean matchesPlayedUpdate(int id, int val) {
        return executeUpdate("UPDATE players SET matches_played = ? WHERE player_id = ?", val, id);
    }

    /**
     * method: goalsUpdate
     * purpose: updates goals scored
     * @param id the player Id
     * @param val the updated goals value
     * @return true if updated successfully, otherwise false
     */

    public boolean goalsUpdate(int id, int val) {
        return executeUpdate("UPDATE players SET goals_scored = ? WHERE player_id = ?", val, id);
    }

    /**
     * method: assistsUpdate
     * purpose: updates assists
     * @param id the player Id
     * @param val the updated assist value
     * @return true if updated successfully, otherwise false
     */

    public boolean assistsUpdate(int id, int val) {
        return executeUpdate("UPDATE players SET assists = ? WHERE player_id = ?", val, id);
    }

    /**
     * method: minutesUpdate
     * purpose: updates minutes played
     * @param id the player Id
     * @param val the updated minute value
     * @return true if updated successfully, otherwise false
     */

    public boolean minutesUpdate(int id, int val) {
        return executeUpdate("UPDATE players SET minutes_played = ? WHERE player_id = ?", val, id);
    }

    private boolean executeUpdate(String sql, Object value, int id) {
        try (PreparedStatement prepstatement = connect.prepareStatement(sql)) {
            if (value instanceof String) {
                prepstatement.setString(1, (String) value);
            } else {
                prepstatement.setInt(1, (Integer) value);
            }
            prepstatement.setInt(2, id);
            return prepstatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}