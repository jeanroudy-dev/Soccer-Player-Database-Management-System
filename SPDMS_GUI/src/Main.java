/**
 * Jean Roudy Alexis
 * CEN - 3024C- 23585 - Software Development 1
 * March 3, 2026
 * Main.java
 * This class runs the Soccer Player Database Management System and manages user interaction through the console menu.
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /**
         * In this section I display the menu
         * purpose: prints the console menu options for the user
         */

        Scanner userInput = new Scanner(System.in);
        PlayerManager manager = new PlayerManager();

        int userChoice = 0;

        while(userChoice != 8) {

            System.out.println("\n Welcome to the Soccer Player Database Management System");
            System.out.println();
            System.out.println("Please enter the number of your choice from 1 to 8:");
            System.out.println("1. Load players from file");
            System.out.println("2. Add player manually");
            System.out.println("3. Display all players");
            System.out.println("4. Search player");
            System.out.println("5. Delete player");
            System.out.println("6. Calculate player performance statistics");
            System.out.println("7. Update Player");
            System.out.println("8. Exit");


            try {
                userChoice = Integer.parseInt(userInput.nextLine());
                if(userChoice < 1 || userChoice > 8) {
                    System.out.println("\n Please enter a valid single digit number from 1 to 8 only:");
                    continue;
                }
            } catch(Exception e) {
                System.out.println("Your input is invalid. Please enter a number from 1 to 8:");
                continue;
            }

            if(userChoice == 1) {

                System.out.print("Enter the data file path: ");
                String file = userInput.nextLine();

                manager.fileUpload(file);
            }

            else if(userChoice == 2) {

                int id;
                while (true) {
                    id = integerValidation(userInput, "Enter the player Id: ");

                    if (manager.verifyDoubleId(id)) {
                        System.out.println("A player with this Id already exists. Please enter a different Id.");
                    } else {
                        break;
                    }
                }

                String name = textValidation(userInput, "Enter the player name: ");
                String position = textValidation(userInput, "Enter the player position: ");
                String team = textValidation(userInput, "Enter the player team: ");
                int matches = integerValidation(userInput, "Enter the number of matches played: ");
                int goals = integerValidation(userInput, "Enter the number of goals scored: ");
                int assists = integerValidation(userInput, "Enter the amount of assists: ");
                int minutes = integerValidation(userInput, "Enter the amount of time played in minutes: ");

                SoccerPlayer player = new SoccerPlayer(id, name, position, team, matches, goals, assists, minutes);
                manager.addPlayer(player);
                System.out.println("New player has been added successfully.");

            }

            else if(userChoice == 3) {
                manager.displayAll();
            }

            else if(userChoice == 4) {

                System.out.println("\nOptions for player search");
                System.out.println();
                System.out.println("1. Search player by Id");
                System.out.println("2. Search player by name");
                System.out.println();

                int searchOption = integerValidation(userInput, "Select one of these options by entering numbers 1 or 2: ");

                if (searchOption == 1) {
                        // Search by ID
                    int id = integerValidation(userInput, "Please enter the player Id you want to search: ");
                    SoccerPlayer player = manager.findPlayerId(id);

                    if (player != null) {
                        player.displayPlayerRecord();
                    } else {
                        System.out.println("The player with Id " + id + " is not found in the system.");
                    }
                }
                else if (searchOption == 2) {
                        // Search by Name
                    String name = textValidation(userInput, "Please enter the player name you want to search: ");
                    SoccerPlayer player = manager.findPlayerName(name);

                    if (player != null) {
                        player.displayPlayerRecord();
                    } else {
                        System.out.println("The player named " + name + " is not found.");
                    }
                }
                else {
                    System.out.println("Invalid search option.");
                }
            }

            else if(userChoice == 5) {

                System.out.println("\nOptions to delete player");
                System.out.println();
                System.out.println("1. Delete player by Id");
                System.out.println("2. Delete player by name");
                System.out.println();

                int deleteOption = integerValidation(userInput, "Select one of these options by entering numbers 1 or 2: ");

                if (deleteOption == 1) {

                    int id = integerValidation(userInput, "Please enter the player Id you want to delete: ");
                    if(manager.deletePlayerId(id)) {
                        System.out.println("The player has been deleted successfully.");
                    }
                    else {
                        System.out.println("The player is not found.");
                    }
                }
                else if (deleteOption == 2) {
                    String name = textValidation(userInput, "Please enter the player name you want to delete: ");
                    if(manager.deletePlayerName(name)) {
                        System.out.println("The player has been deleted successfully.");
                    }
                    else {
                        System.out.println("The player is not found.");
                    }
                }
                else {
                    System.out.println("Search option is not valid.");
                }
            }


            else if(userChoice == 6) {

                int id = integerValidation(userInput, "Please enter the player Id: ");

                SoccerPlayer player = manager.findPlayerId(id);

                if(player != null) {
                    player.displayStats();
                }
                else {
                    System.out.println("The player is not found.");
                }
            }

            else if(userChoice == 7) {

                int id = integerValidation(userInput, "Please enter the player Id you want to update: ");

                SoccerPlayer player = manager.findPlayerId(id);

                if(player != null) {

                    int updateChoice;

                    while(true) {
                        System.out.println("\nPlease select the field you want to update:");
                        System.out.println("1. Name");
                        System.out.println("2. Position");
                        System.out.println("3. Team");
                        System.out.println("4. Matches played");
                        System.out.println("5. Goals");
                        System.out.println("6. Assists");
                        System.out.println("7. Minutes played");

                        updateChoice = integerValidation(userInput, "Please enter the number of your choice: ");

                        if (updateChoice >= 1 && updateChoice <= 7) {
                            break;
                        } else {
                            System.out.println("Your input is invalid. Please try again.");
                        }
                    }

                    switch(updateChoice) {
                        case 1:
                            String name = textValidation(userInput, "Enter the new player name: ");
                            manager.playerNameUpdate(id, name);
                            break;

                        case 2:
                            String position = textValidation(userInput, "Enter the new player position: ");
                            manager.playerPositionUpdate(id, position);
                            break;

                        case 3:
                            String team = textValidation(userInput, "Enter the new team: ");
                            manager.playerTeamUpdate(id, team);
                            break;

                        case 4:
                            int matches = integerValidation(userInput, "Enter matches played: ");
                            manager.matchesPlayedUpdate(id, matches);
                            break;

                        case 5:
                            int goals = integerValidation(userInput, "Enter goals scored: ");
                            manager.goalsUpdate(id, goals);
                            break;

                        case 6:
                            int assists = integerValidation(userInput, "Enter assists amount: ");
                            manager.assistsUpdate(id, assists);
                            break;

                        case 7:
                            int minutes = integerValidation(userInput, "Enter minutes played: ");
                            manager.minutesUpdate(id, minutes);
                            break;

                    }

                    System.out.println("The player has been updated successfully.");
                }
                else {
                    System.out.println("The player you entered cannot be found.");
                }
            }

        }

        System.out.println("Thanks for using the Soccer Player Database Management System.");
        System.out.println("Bye!");
    }

    /**
     * method: integerValidation
     * parameters: Scanner userInput, String displayMessage
     * return: int
     * purpose: validates that the user enters a non-negative integer
     * non-empty input
     * valid integer
     */

    public static int integerValidation(Scanner userInput, String displayMessage) {
        while(true) {
            System.out.print(displayMessage);
            String line = userInput.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("Your input cannot be empty. Please enter a number:");
            }
            else if(line.matches("\\d+")) {
                int value = Integer.parseInt(line);
                if(value < 0) {
                    System.out.println("Your input cannot be negative. Please Try again.");
                } else {
                    return value;
                }
            } else {
                System.out.println("Your input is invalid and not supported. Please enter a number:");
            }
        }
    }

    /**
     * method: textValidation
     * parameters: Scanner userInput, String displayMessage
     * return: String
     * purpose: validates that the user enters proper text (letters, spaces, hyphens, apostrophes, periods)
     */

    public static String textValidation(Scanner userInput, String displayMessage) {
        while(true) {
            System.out.print(displayMessage);
            String text = userInput.nextLine().trim();

            if(text.isEmpty()) {
                System.out.println("Your input cannot be empty. Please try again.");

            }

            if (!text.matches(".*[a-zA-Z].*")) {
                System.out.println("Your input must contain at least one letter.");
                continue;
            }

            // allows letters, spaces, hyphens, apostrophes, and periods
            else if(!text.matches("[a-zA-Z\\s\\-\\'\\.]+")) {
                System.out.println("Your input is invalid. Please enter text with letters and spaces. Hyphens, apostrophes, and periods are allowed");
            } else {
                return text;
            }
        }
    }
}