package FinalProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main extends GuessMethods {
    public static void main(String[] args) {
        // database
        final String dbURL = "jdbc:mysql://localhost:3306/guess_my_word_game";
        final String user = "root";
        final String password = "Jm111000";
        char again = 'y';
        Scanner scanner = new Scanner(System.in);

        // get username
        System.out.println("Please, enter your username:");
        String userName = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {
            printInstructions();

            System.out.println("Please, choose your option:");
            while (again == 'y') {
                char action = scanner.nextLine().charAt(0);
                if (action == '1') {
                    playTheGameAction(scanner, conn);
                } else if (action == '2') {
                    insertTopicAction(scanner, conn);
                } else if(action == '3') {
                    insertWordAction(scanner, conn);
                } else if(action == '4') {
                    readScores(conn);
                } else if(action == '5') {
                    break;
                }
            }
            System.out.println("Hey, lets play again? y/n");
            again = scanner.nextLine().charAt(0);
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }

}
