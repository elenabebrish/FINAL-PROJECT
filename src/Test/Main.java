package Test;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String dbURL = "jdbc:mysql://localhost:3306/guess_my_word_game";
        final String user = "root";
        final String password = "jemi0404";
        char again = 'y';
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {
            //readTopics(conn);
//            readWords(conn);
//            readScores(conn);

            printInstructions();
            System.out.println(); //KRISTINE - ADDITIONAL SPACE FOR VISUAL LOOK
            System.out.println("Please choose your option:"); //KRISTINE - USER FRIENDLY OPTION

            while (again == 'y') {

                char action = scanner.nextLine().charAt(0);

                if(action == '1') {
                    System.out.println("Lets Play! Ready, set, go...");

                    readTopics(conn);
                    System.out.println("Enter Topic number:");
                    int chooseTopic = scanner.nextInt();
                    readWords(conn);

                    //if(chooseTopic <=5 && chooseTopic >=1) {
                    //  readWords(conn);
                    //}



//                    //Topic ID from 1 to 5, should be adjusted if new topic added
//                    int chooseTopic = scanner.nextInt();
//                    if(chooseTopic <=5 && chooseTopic >=1) {
//                        System.out.println("You have successfully choose topic");
//
//
//                    }else {
//                        System.out.println("There is no such topic ID, please, enter valid one");
//                    }





                    //System.out.println("Enter wished amount of moves (1-10)");






                }else if (action == '2') {
                    System.out.println("Enter new Topic");
                    String topic = scanner.nextLine();

                    insertTopic(conn, topic);

                }else if(action == '3') {
                    System.out.println("Enter new Word");
                    String word = scanner.nextLine();

                    readTopics(conn);
                    int topic_id = scanner.nextInt();

                    insertWords(conn, topic_id , word);

                }else if(action == '4') {
                    readScores(conn);

                }else if(action == '5') {
                    break;

                }



                //printInstructions(); // KRISTINE - NOT NECESSARY


            }
            System.out.println("Hey, lets play again? y/n"); //KRISTINE - MOVED TO ANOTHER LINE
            again = scanner.nextLine().charAt(0); //KRISTINE - MOVED TO ANOTHER LINE

        }catch (SQLException e) {
            System.out.println("Something went wrong");
        }

    }

    public static void readTopics(Connection conn) throws SQLException {
        String sql = "SELECT * FROM topics;";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Topic list:");

        while (resultSet.next()) {

            int topic_id = resultSet.getInt(1);
            String topic_name = resultSet.getString(2);

            String output = "\t %d. %s";
            System.out.println(String.format(output,topic_id, topic_name));
        }

    }

    public static void readWords(Connection conn) throws SQLException {
        String sql = "SELECT word FROM words WHERE topic_id = 1;";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            String word = resultSet.getString("word");

            System.out.println(String.format(word));

        }



    }




    public static void readScores(Connection conn) throws SQLException {
        String sql = "SELECT * FROM scores;";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            int user_ID = resultSet.getInt(1);
            String username = resultSet.getString(2);
            int topic_id = resultSet.getInt(3);
            int scores_results = resultSet.getInt(4);
            int number_of_tries = resultSet.getInt(5);

            String output = "Score board: \n\t ID: %d \n\t Username: %s \n\t Score: %d \n\t Tries: %d";
            System.out.println(String.format(output, user_ID, username, topic_id, scores_results, number_of_tries));
        }

    }

    public static void insertTopic (Connection conn, String topic_name) throws SQLException {

        String sql = "INSERT INTO topics (topic_name) VALUES ('?');";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, topic_name);

        int rowInserted = preparedStatement.executeUpdate();

        if(rowInserted > 0) {
            System.out.println("New Topic was added");
        }else {
            System.out.println("Something went wrong");
        }

    }

    public static void insertWords (Connection conn, int topic_id, String word) throws SQLException {

        String sql = "INSERT INTO words (topic_id, word) VALUES (?, '?');";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, topic_id);
        preparedStatement.setString(2, word);

        int rowInserted = preparedStatement.executeUpdate();

        if(rowInserted > 0) {
            System.out.println("New Word was added");
        }else {
            System.out.println("Something went wrong");
        }

    }

    public static void printInstructions(){
        System.out.println("\nChoose and enter number to take fallowed action:"); //  KRISTINE - CHANGE TO INTRODUCTION TO GAME RULES:
        System.out.println("\t 1 - to play game");
        System.out.println("\t 2 - to add new topic");
        System.out.println("\t 3 - to add new word");
        System.out.println("\t 4 - to see score board");
        System.out.println("\t 5 - to quit game");


    }


}
