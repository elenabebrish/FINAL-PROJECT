package FinalProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GuessMethods {
    //readTopics (no need here)

    public static void printInstructions() {
        System.out.println("\nChoose and enter number to take fallowed action:"); //  KRISTINE - CHANGE TO INTRODUCTION TO GAME RULES:
        System.out.println("\t 1 - to play game");
        System.out.println("\t 2 - to add new topic");
        System.out.println("\t 3 - to add new word");
        System.out.println("\t 4 - to see score board");
        System.out.println("\t 5 - to quit game");
        System.out.println(); //KRISTINE - ADDITIONAL SPACE FOR VISUAL LOOK
    }

    public static void playTheGameAction(Scanner scanner, Connection conn) throws SQLException {
        //System.out.println("Lets Play! Ready, set, go...");

        ArrayList<Topic> topics = getTopics(conn);
        printTopics(topics);

        // ask person for topic
        System.out.printf("Select topic for your game [1-%d]\n", topics.size());
        int topicIndex = scanner.nextInt();
        // check if topic is in range
        if (topicIndex < 1 || topicIndex > topics.size()) {
            System.out.println("Invalid topic selected");
            return; // exit the program
        }

        // get current topic
        Topic currentTopic = topics.get(topicIndex - 1);
        System.out.printf("You have selected %s topic%n", currentTopic.topic_name);

        // get topic words
        ArrayList<TopicWord> words = getWords(conn, currentTopic);
        // print message
        System.out.println("Here are the words of the game:");
        // print out all topics
        for (int index = 0; index < words.size(); index++) {
            // get topic by index
            TopicWord word = words.get(index);
            // print our topic information
            String output = "\t %d. %s";
            System.out.printf((output) + "\n", index + 1, word.word);
        }

        Random random = new Random();
        TopicWord wordToGuess = words.get(random.nextInt(words.size()));

        List<Character> guesses = new ArrayList<Character>();
        System.out.println("Enter a letter");
        String letter = scanner.nextLine();

        while (scanner.hasNext()) {
            guesses.add(letter.charAt(0));
        }

        for (int i=0; i < wordToGuess.word.length(); i++) {
            if(guesses.contains(wordToGuess.word.charAt(i))) {
                System.out.println(wordToGuess.word.charAt(i));
            }else {
                System.out.println("-");
            }
        }


        //System.out.println("Enter wished amount of moves (1-10)");
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

    public static void insertTopicAction(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Enter new Topic");
        String topic = scanner.nextLine();

        insertTopic(conn, topic);
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

    public static void insertWordAction(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Enter new Word");
        String word = scanner.nextLine();

        getTopics(conn);
        int topic_id = scanner.nextInt();

        insertWords(conn, topic_id , word);
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

    public static ArrayList<Topic> getTopics(Connection conn) throws SQLException {
        String sql = "SELECT * FROM topics";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Topic> topics = new ArrayList<Topic>();

        while (resultSet.next()) {
            Topic topic = new Topic();
            topic.topic_id = resultSet.getInt("topic_id");
            topic.topic_name = resultSet.getString("topic_name");
            topics.add(topic);
        }

        return topics;
    }

    public static ArrayList<TopicWord> getWords(Connection conn, Topic topic) throws SQLException {
        String sql = "SELECT * FROM words WHERE topic_id = ?";
        // prepare statement
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, topic.topic_id);
        // get results
        ResultSet resultSet = statement.executeQuery();
        ArrayList<TopicWord> words = new ArrayList<TopicWord>();

        while (resultSet.next()) {
            TopicWord word = new TopicWord();
            word.word_id = resultSet.getInt("word_id");
            word.topic_id = resultSet.getInt("topic_id");
            word.word = resultSet.getString("word");
            words.add(word);
        }

        return words;
    }

    public static void printTopics(ArrayList<Topic> topics) {
        System.out.println("Here are the topic of the game:");
        // print out all topics
        for (int index = 0; index < topics.size(); index++) {
            // get topic by index
            Topic topic = topics.get(index);
            // print our topic information
            String output = "\t %d. %s";
            System.out.printf((output) + "%n", index + 1, topic.topic_name);
        }
    }

}

class Topic {
    int topic_id;
    String topic_name;
}

class TopicWord {
    int word_id;
    int topic_id;
    String word;
}
