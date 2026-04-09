/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * April 3, 2026
 * PlayerManager.java
 * Manages soccer player data storage and retrieval via MySQL.
 */


import java.sql.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;


public class PlayerManager {

    private Connection connect = DatabaseManager.getConnection();

    public PlayerManager() {}

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

    public boolean playerNameUpdate(int id, String name) {
        return executeUpdate("UPDATE players SET player_name = ? WHERE player_id = ?", name, id);
    }

    public boolean playerPositionUpdate(int id, String pos) {
        return executeUpdate("UPDATE players SET player_position = ? WHERE player_id = ?", pos, id);
    }

    public boolean playerTeamUpdate(int id, String team) {
        return executeUpdate("UPDATE players SET player_team = ? WHERE player_id = ?", team, id);
    }

    public boolean matchesPlayedUpdate(int id, int val) {
        return executeUpdate("UPDATE players SET matches_played = ? WHERE player_id = ?", val, id);
    }

    public boolean goalsUpdate(int id, int val) {
        return executeUpdate("UPDATE players SET goals_scored = ? WHERE player_id = ?", val, id);
    }

    public boolean assistsUpdate(int id, int val) {
        return executeUpdate("UPDATE players SET assists = ? WHERE player_id = ?", val, id);
    }

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