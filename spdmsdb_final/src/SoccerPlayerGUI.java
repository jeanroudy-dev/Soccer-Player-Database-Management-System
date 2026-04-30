import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;

/**
 * Jean Roudy Alexis
 * CEN - 3024C - 23585 - Software Development 1
 * April 3, 2026
 * SoccerPlayerGUI.java
 * This class provides a graphical user interface for managing soccer players.
 * It allows users to upload files, display players, add new records,
 * update existing records, delete players, and calculate player statistics.
 * This class works together with PlayerManager and DatabaseManager
 * to connect the user interface with the database system.
 */

public class SoccerPlayerGUI extends JFrame {
    private PlayerManager manager = new PlayerManager();
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);
    private JTable playerTable;
    private DefaultTableModel tableModel;
    private final String BACKGROUND_IMAGE = "/spdms_background.png";

    /**
     * constructor: SoccerPlayerGUI
     * parameters: none
     * purpose: initializes the main application window,
     * creates the table model, and loads the main panels
     */

    public SoccerPlayerGUI() {
        setTitle("Soccer Player Database Management System");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columns = {"Id", "Name", "Position", "Team", "Matches", "Goals", "Assists", "Minutes"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        playerTable = new JTable(tableModel);
        playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        playerTable.setRowHeight(28);
        playerTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        playerTable.setSelectionBackground(new Color(70, 130, 180));
        playerTable.setSelectionForeground(Color.WHITE);

        playerTable.getTableHeader().setBackground(new Color(25, 25, 112));
        playerTable.getTableHeader().setForeground(Color.WHITE);
        playerTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        playerTable.getTableHeader().setReorderingAllowed(false);

        playerTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(new Color(70, 130, 180));
                    c.setForeground(Color.WHITE);
                } else {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(245, 245, 245));
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        mainContainer.add(homeMenu(), "MENU");
        mainContainer.add(displayPage(), "DISPLAY");

        add(mainContainer);
        setVisible(true);
    }

    /**
     * method: styleButton
     * purpose: applies consistent styling to all buttons
     */
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }

    /**
     * method: homeMenu
     * parameters: none
     * return: JPanel
     * purpose: creates the home menu panel with buttons for user actions
     */

    private JPanel homeMenu() {
        BackgroundPanel panel = new BackgroundPanel(BACKGROUND_IMAGE);
        panel.setLayout(new GridBagLayout());
        JPanel menuBox = new JPanel();
        menuBox.setLayout(new BoxLayout(menuBox, BoxLayout.Y_AXIS));
        menuBox.setOpaque(false);

        JLabel title = new JLabel("Soccer Player Database Management System");

        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title.setForeground(new Color(25, 25, 112));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuBox.add(title);
        menuBox.add(Box.createRigidArea(new Dimension(0, 80)));

        JButton loadButton = new JButton("1. Upload file");
        loadButton.addActionListener(e -> handleLoad());

        JButton viewButton = new JButton("2. Display players");
        viewButton.addActionListener(e -> refreshTable());

        JButton addButton = new JButton("3. Add player");
        addButton.addActionListener(e -> handleAdd());

        JButton exitButton = new JButton("4. Exit");
        exitButton.addActionListener(e -> System.exit(0));

        for (JButton button : new JButton[]{loadButton, viewButton, addButton, exitButton}) {

            styleButton(button);

            button.setMaximumSize(new Dimension(350, 45));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuBox.add(button);
            menuBox.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        panel.add(menuBox);
        return panel;
    }

    /**
     * method: displayPage
     * parameters: none
     * return: JPanel
     * purpose: creates the display page with a toolbar and table of players
     */

    private JPanel displayPage() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(new Color(240, 248, 255));

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.setBackground(new Color(220, 230, 250));

        JButton homeButton = new JButton("Main menu");
        JButton statsButton = new JButton("Calculate stats");
        JButton updateButton = new JButton("Update player");
        JButton deleteButton = new JButton("Delete player");

        styleButton(homeButton);
        styleButton(statsButton);
        styleButton(updateButton);
        styleButton(deleteButton);

        homeButton.addActionListener(e -> cardLayout.show(mainContainer, "MENU"));
        statsButton.addActionListener(e -> handleStats());
        updateButton.addActionListener(e -> handleUpdate());
        deleteButton.addActionListener(e -> handleDelete());

        toolbar.add(homeButton);
        toolbar.add(statsButton);
        toolbar.add(updateButton);
        toolbar.add(deleteButton);

        panel.add(toolbar, BorderLayout.NORTH);
        panel.add(new JScrollPane(playerTable), BorderLayout.CENTER);
        return panel;
    }


    private void handleLoad() {
        JFileChooser chooseFile = new JFileChooser();
        if (chooseFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String message = manager.fileUpload(chooseFile.getSelectedFile().getAbsolutePath());
            JOptionPane.showMessageDialog(this, message);
            refreshTable();
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (SoccerPlayer player : manager.getPlayerList()) {
            tableModel.addRow(new Object[]{
                    player.getPlayerId(), player.getPlayerName(), player.getPlayerPosition(), player.getPlayerTeam(),
                    player.getMatchesPlayed(), player.getGoalScored(), player.getAssistAmount(), player.getMinutesPlayed()
            });
        }
        cardLayout.show(mainContainer, "DISPLAY");
    }

    private void handleStats() {
        int row = playerTable.getSelectedRow();
        if (row == -1) return;
        int id = (int) tableModel.getValueAt(row, 0);
        SoccerPlayer player = manager.findPlayerId(id);
        if (player != null) JOptionPane.showMessageDialog(this, player.displayStats());
    }

    private void handleDelete() {
        int row = playerTable.getSelectedRow();
        if (row == -1) return;
        int id = (int) tableModel.getValueAt(row, 0);
        if (manager.deletePlayerId(id)) refreshTable();
    }

    private void handleUpdate() {
        int row = playerTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a player.");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        String[] options = {"Name", "Position", "Team", "Matches Played", "Goals", "Assists", "Minutes Played"};
        String choice = (String) JOptionPane.showInputDialog(this, "Select field:", "Update",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == null) return;
        String newValue = JOptionPane.showInputDialog(this, "Please enter new value:");
        if (newValue == null) return;

        try {
            boolean updated = switch (choice) {
                case "Name" -> manager.playerNameUpdate(id, newValue);
                case "Position" -> manager.playerPositionUpdate(id, newValue);
                case "Team" -> manager.playerTeamUpdate(id, newValue);
                case "Matches Played" -> manager.matchesPlayedUpdate(id, Integer.parseInt(newValue));
                case "Goals" -> manager.goalsUpdate(id, Integer.parseInt(newValue));
                case "Assists" -> manager.assistsUpdate(id, Integer.parseInt(newValue));
                case "Minutes Played" -> manager.minutesUpdate(id, Integer.parseInt(newValue));
                default -> false;
            };

            if (updated) {
                JOptionPane.showMessageDialog(this, "Player updated successfully.");
                refreshTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Your input is invalid.");
        }
    }

    private void handleAdd() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField[] fields = new JTextField[8];
        String[] labels = {"Player Id: ", "Player name: ", "Player position: ",
                "Player team: ", "Matches played: ",
                "Goals scored: ", "Assists: ",
                "Total time in minutes: "};

        for (int i = 0; i < 8; i++) {
            panel.add(new JLabel(labels[i]));
            fields[i] = new JTextField();
            panel.add(fields[i]);
        }

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Player Manually", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(fields[0].getText());

                if (manager.verifyDoubleId(id)) {
                    JOptionPane.showMessageDialog(this, "A player with this Id already exists. Please enter a different Id.");
                } else {
                    SoccerPlayer player = new SoccerPlayer(
                            id,
                            fields[1].getText(),
                            fields[2].getText(),
                            fields[3].getText(),
                            Integer.parseInt(fields[4].getText()),
                            Integer.parseInt(fields[5].getText()),
                            Integer.parseInt(fields[6].getText()),
                            Integer.parseInt(fields[7].getText())
                    );

                    manager.addPlayer(player);
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "New player has been added successfully.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Use numbers for Id and statistics; letters for other fields.");
            }
        }
    }

    public static void main(String[] args) {
        DatabaseManager.getConnection();
        SwingUtilities.invokeLater(SoccerPlayerGUI::new);
    }

    private class BackgroundPanel extends JPanel {
        private Image image;
        public BackgroundPanel(String path) {
            try { image = new ImageIcon(getClass().getResource(path)).getImage(); } catch(Exception e){}
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}