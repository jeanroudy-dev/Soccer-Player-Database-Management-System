/**
 * Jean Roudy Alexis
 * CEN - 3024C- 23585 - Software Development 1
 * March 25, 2026
 * SoccerPlayerGUI.java
 * This class runs the Soccer Player Database Management System with a graphical user interface (GUI)
 * and manages user interaction through buttons, dialog boxes, and display panels.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SoccerPlayerGUI extends JFrame {
    private PlayerManager manager = new PlayerManager();
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);
    private JTextArea displayArea;


    private final String BACKGROUND_IMAGE = "/spdms_background.png";

    public SoccerPlayerGUI() {
        setTitle("Soccer Player Database Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainContainer.add(homeMenu(), "MENU");
        mainContainer.add(displayPage(), "DISPLAY");

        add(mainContainer);
        setVisible(true);
    }

    /**
     * method: homeMenu
     * parameters: none
     * return: JPanel
     * purpose: creates and returns the main menu panel with all menu buttons and background
     */


    private JPanel homeMenu() {
        BackgroundPanel panel = new BackgroundPanel(BACKGROUND_IMAGE);
        panel.setLayout(new GridBagLayout());

        JPanel menuBox = new JPanel();
        menuBox.setLayout(new BoxLayout(menuBox, BoxLayout.Y_AXIS));
        menuBox.setOpaque(false);

        JLabel title = new JLabel("Welcome to the Soccer Player Database Management System");
        title.setFont(new Font("ARIAL", Font.BOLD, 28));
        title.setForeground(Color.BLUE);
        title.setForeground(new Color(25, 25, 112));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuBox.add(title);
        menuBox.add(Box.createRigidArea(new Dimension(0, 100)));


        addMenuButton(menuBox, "1. Load players from file", e -> handleLoad());
        addMenuButton(menuBox, "2. Add player manually", e -> handleAdd());
        addMenuButton(menuBox, "3. Display all players", e -> {
            displayArea.setText(manager.displayAll());
            cardLayout.show(mainContainer, "DISPLAY");
        });
        addMenuButton(menuBox, "4. Search player", e -> handleSearch());
        addMenuButton(menuBox, "5. Delete player", e -> handleDelete());
        addMenuButton(menuBox, "6. Calculate player statistics", e -> handleStats());
        addMenuButton(menuBox, "7. Update Player", e -> handleUpdate());
        addMenuButton(menuBox, "8. Exit", e -> System.exit(0));

        panel.add(menuBox);
        return panel;
    }

    /**
     * method: addMenuButton
     * parameters: JPanel panel, String text, ActionListener action
     * return: void
     * purpose: adds a single button with the given text and action listener to the provided panel
     */

    private void addMenuButton(JPanel panel, String text, ActionListener action) {
        JButton btn = new JButton(text);

        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(300, 40));
        btn.setMaximumSize(new Dimension(300, 40));
        btn.setMinimumSize(new Dimension(300, 40));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addActionListener(action);

        panel.add(btn);
        panel.add(Box.createRigidArea(new Dimension(0, 12)));
    }

    /**
     * method: displayPage
     * parameters: none
     * return: JPanel
     * purpose: creates and returns the display panel with a HOME button and a text area for showing player info
     */

    private JPanel displayPage() {
        BackgroundPanel panel = new BackgroundPanel(BACKGROUND_IMAGE);
        panel.setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setOpaque(false);
        JButton homeButton = new JButton("Main Menu");
        homeButton.addActionListener(e -> cardLayout.show(mainContainer, "MENU"));
        top.add(homeButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        displayArea.setForeground(Color.BLACK);
        displayArea.setOpaque(false);
        displayArea.setMargin(new Insets(15, 15, 15, 15));

        JScrollPane scroll = new JScrollPane(displayArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        panel.add(top, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    /**
     * method: handleLoad
     * parameters: none
     * return: void
     * purpose: handles the loading of player data from a file and displays the result in the display panel
     */



    private void handleLoad() {
        String file = JOptionPane.showInputDialog(this, "Please enter the data file path:");

        if (file != null && !file.trim().isEmpty()) {
            String resultMessage = manager.fileUpload(file.trim());

            displayArea.setText(resultMessage);

            if (resultMessage.startsWith("Error")) {
                JOptionPane.showMessageDialog(this, resultMessage, "Invalid file", JOptionPane.ERROR_MESSAGE);
            } else {
                cardLayout.show(mainContainer, "DISPLAY");
            }
        }
    }

    /**
     * method: handleSearch
     * parameters: none
     * return: void
     * purpose: handles searching for a player by Id and displays the player record in the display panel
     */

    private void handleSearch() {
        String idStr = JOptionPane.showInputDialog(this, "Please enter the player Id you want to search:");
        if (idStr != null) {
            try {
                SoccerPlayer p = manager.findPlayerId(Integer.parseInt(idStr.trim()));
                displayArea.setText(p != null ? p.displayPlayerRecord() : "The player is not found.");
            } catch (Exception e) { displayArea.setText("Invalid input."); }
            cardLayout.show(mainContainer, "DISPLAY");
        }
    }

    /**
     * method: handleStats
     * parameters: none
     * return: void
     * purpose: handles displaying a player's performance statistics in the display panel
     */

    private void handleStats() {
        String idStr = JOptionPane.showInputDialog(this, "Please enter the player Id:");
        if (idStr != null) {
            try {
                SoccerPlayer p = manager.findPlayerId(Integer.parseInt(idStr.trim()));
                if (p != null) {
                    displayArea.setText(p.displayStats());
                } else {
                    displayArea.setText("The player is not found.");
                }
            } catch (Exception e) { displayArea.setText("Invalid input."); }
            cardLayout.show(mainContainer, "DISPLAY");
        }
    }

    /**
     * method: handleDelete
     * parameters: none
     * return: void
     * purpose: handles deleting a player by Id and displays the result in the display panel
     */

    private void handleDelete() {
        String id = JOptionPane.showInputDialog(this, "Please enter the player Id you want to delete:");
        if (id != null) {
            try {
                boolean ok = manager.deletePlayerId(Integer.parseInt(id.trim()));
                displayArea.setText(ok ? "The player has been deleted successfully." : "The player is not found.");
            } catch (Exception e) { displayArea.setText("Invalid input."); }
            cardLayout.show(mainContainer, "DISPLAY");
        }
    }

    /**
     * method: handleUpdate
     * parameters: none
     * return: void
     * purpose: handles updating a selected player's field and displays the update confirmation
     */

    private void handleUpdate() {
        String id = JOptionPane.showInputDialog(this, "Please enter the player Id you want to update:");
        if (id == null) return;

        try {
            int idUpdate = Integer.parseInt(id.trim());
            SoccerPlayer player = manager.findPlayerId(idUpdate);

            if (player != null) {
                String[] options = {"1. Name", "2. Position", "3. Team", "4. Matches played", "5. Goals", "6. Assists", "7. Minutes played"};
                String choice = (String) JOptionPane.showInputDialog(this, "Please select the field you want to update:",
                        "Update Player", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (choice != null) {
                    String newValue = JOptionPane.showInputDialog(this, "Please enter the new value:");
                    if (newValue != null) {
                        int updateChoice = Character.getNumericValue(choice.charAt(0));

                        switch(updateChoice) {
                            case 1 -> manager.playerNameUpdate(idUpdate, newValue);
                            case 2 -> manager.playerPositionUpdate(idUpdate, newValue);
                            case 3 -> manager.playerTeamUpdate(idUpdate, newValue);
                            case 4 -> manager.matchesPlayedUpdate(idUpdate, Integer.parseInt(newValue));
                            case 5 -> manager.goalsUpdate(idUpdate, Integer.parseInt(newValue));
                            case 6 -> manager.assistsUpdate(idUpdate, Integer.parseInt(newValue));
                            case 7 -> manager.minutesUpdate(idUpdate, Integer.parseInt(newValue));
                        }
                        displayArea.setText("The player has been updated successfully.");
                        cardLayout.show(mainContainer, "DISPLAY");
                    }
                }
            } else {
                displayArea.setText("The player you entered cannot be found.");
                cardLayout.show(mainContainer, "DISPLAY");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
        }
    }


    /**
     * method: handleAdd
     * parameters: none
     * return: void
     * purpose: handles manually adding a new player using a form and displays a confirmation message
     */

    private void handleAdd() {
        JPanel p = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField[] fields = new JTextField[8];
        String[] labels = {"Enter the player Id: ", "Enter the player name: ", "Enter the player position: ",
                "Enter the player team: ", "Enter the number of matches played: ",
                "Enter the number of goals scored: ", "Enter the amount of assists: ",
                "Enter the amount of time played in minutes: "};

        for(int i=0; i<8; i++) {
            p.add(new JLabel(labels[i]));
            fields[i] = new JTextField();
            p.add(fields[i]);
        }

        int result = JOptionPane.showConfirmDialog(this, p, "Add Player Manually", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(fields[0].getText());
                if (manager.verifyDoubleId(id)) {
                    displayArea.setText("A player with this Id already exists. Please enter a different Id.");
                } else {
                    SoccerPlayer sp = new SoccerPlayer(id, fields[1].getText(), fields[2].getText(), fields[3].getText(),
                            Integer.parseInt(fields[4].getText()), Integer.parseInt(fields[5].getText()),
                            Integer.parseInt(fields[6].getText()), Integer.parseInt(fields[7].getText()));
                    manager.addPlayer(sp);
                    displayArea.setText("New player has been added successfully.");
                }
                cardLayout.show(mainContainer, "DISPLAY");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please only use numbers for Id and statistics; and only letters for the other fields");
            }
        }
    }

    /**
     * method: main
     * parameters: String[] args
     * return: void
     * purpose: launches the SoccerPlayerGUI application
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SoccerPlayerGUI::new);
    }

    /**
     * class: BackgroundPanel
     * purpose: a custom JPanel that paints a background image, or a color display if the image is missing
     */

    private class BackgroundPanel extends JPanel {
        private Image image;
        public BackgroundPanel(String path) {
            try {
                java.net.URL imageUrl = getClass().getResource(path);
                if (imageUrl != null) {
                    image = new ImageIcon(imageUrl).getImage();
                } else {
                    System.out.println("Background image not found: " + path);
                }
            } catch (Exception e) {
                System.out.println("Background image not found at: " + path);
            }
            setLayout(new BorderLayout());
        }
        @Override
        protected void paintComponent(Graphics background) {
            super.paintComponent(background);
            if (image != null) {
                background.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            } else {
                background.setColor(new Color(34, 139, 34));
                background.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }
}