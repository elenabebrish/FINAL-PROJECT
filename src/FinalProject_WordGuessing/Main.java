package FinalProject_WordGuessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

//TODO - Printout word (not guessed one) have to have first letter Upper
//     - Fix how pts are counted (now by amount of guessed letters per word not per moves)
//     - If after playing game you press 'n' and then 'y' to see option list, then again choose 'option 1',
//          it doesn't allow game to be played
//     - Doesn't save word in Score Board if enter full (saves only letter by letter guessed)


public class Main extends GuessMethods {
    public static void main(String[] args) {
        // database
        final String dbURL = "jdbc:mysql://localhost:3306/guess_my_word_game";
        final String user = "root";
        final String password = "Jm111000";
        char againAction = 'y';
        char againGame = 'y';
        Scanner scanner = new Scanner(System.in);

        // get username
        System.out.println("Please, enter your username:");
        String userName = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {

            while (againAction == 'y') {
                printInstructions();

                System.out.println("Please, choose your option:");
                char action = scanner.next().charAt(0);
                scanner.nextLine();

                if (action == '1') {
                    while (againGame == 'y') {
                        playTheGameAction(conn, userName);

                        System.out.println("Hey, lets play again? y/n");
                        againGame = scanner.next().charAt(0);
                    }
                } else if (action == '2') {
                    insertTopicAction(conn);
                } else if(action == '3') {
                    insertWordAction(conn);
                } else if(action == '4') {
                    readTOP10(conn);
                } else if(action == '5') {
                    break;
                }

                System.out.println("Maybe want to see option list? y/n");
                againAction = scanner.next().charAt(0);

            }
        } catch (SQLException e) {
            System.out.println("Ooops.. Something went wrong");
        }
    }

}
