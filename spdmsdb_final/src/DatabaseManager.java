import java.sql.*;
import javax.swing.*;
import java.awt.*;

/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * April 3, 2026
 * DatabaseManager.java
 * This class manages the connection to a MySQL database.
 * It prompts the user for login credentials and provides
 * a reusable static connection method for the other classes
 * in the Soccer Player Database Management System.
 */

public class DatabaseManager {

    private static Connection connection;

    /**
     * constructor: DatabaseManager
     * parameters: none
     * purpose: initializes the database manager
     * and starts the database connection process
     * note: private to enforce the use of getConnection()
     */

    private DatabaseManager() {
        connectToDatabase();
    }


    /**
     * method: connectToDatabase
     * parameters: none
     * return: void
     * purpose: prompts the user for MySQL server,
     * database name, username, and password,
     * then attempts to establish a database connection.
     * The program closes if the connection fails.
     */

    private void connectToDatabase() {
        JTextField serverField = new JTextField("localhost:3306");
        JTextField dbField = new JTextField();
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Server:"));
        panel.add(serverField);
        panel.add(new JLabel("Database name:"));
        panel.add(dbField);
        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Please enter your MySQL login details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String server = serverField.getText().trim();
            String database = dbField.getText().trim();
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());

            String url = "jdbc:mysql://" + server + "/" + database +
                    "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

            try {
                connection = DriverManager.getConnection(url, username, password);
                JOptionPane.showMessageDialog(null, "You have connected to the database successfully");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database:\n" + e.getMessage());
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    /**
     * method: getConnection
     * parameters: none
     * purpose: returns the active database connection.
     * If no connection exists or the connection is closed,
     * a new DatabaseManager object is created.
     * @return the active MySQL database connection
     */

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                new DatabaseManager();
            }
        } catch (SQLException e) {
            new DatabaseManager();
        }
        return connection;
    }
}