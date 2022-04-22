package GuessMyWord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

//TODO - Ranking in Score Board have to change ID to index+1
//     - Printout word (not guessed one) have to have first letter Upper
//     - Option to guess/enter all word
//     -
//     -

public class Main extends GuessMethods {
    public static void main(String[] args) {
        // database
        final String dbURL = "jdbc:mysql://localhost:3306/guess_my_word_game";
        final String user = "root";
        final String password = "0108";
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
                        playTheGameAction(scanner, conn, userName);

                        System.out.println("Hey, lets play again? y/n");
                        againGame = scanner.next().charAt(0);
                    }
                } else if (action == '2') {
                    insertTopicAction(scanner, conn);
                } else if(action == '3') {
                    insertWordAction(scanner, conn);
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
