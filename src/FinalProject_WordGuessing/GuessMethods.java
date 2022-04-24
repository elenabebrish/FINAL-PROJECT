package FinalProject_WordGuessing;

import java.sql.*;
import java.util.*;

public class GuessMethods {
    static Scanner scanner = new Scanner(System.in);

    public static void printInstructions() {
        System.out.println("\nChoose and enter number to take fallowed action:");
        System.out.println("\t 1 - to play game");
        System.out.println("\t 2 - to add new topic");
        System.out.println("\t 3 - to add new word");
        System.out.println("\t 4 - to see score board");
        System.out.println("\t 5 - to quit game");
    }


    public static void playTheGameAction(Connection conn, String userName) throws SQLException {
        System.out.println("RULES that you shall obey: " +
                "\n\t•enter letter one by one" +
                "\n\t•you have maximum 10 guesses per word or ohhh bugger" +
                "\n\t•uppercase or lowercase guess entries are equally rocking" +
                "\nLets play! Ready, set, go...\n");

        ArrayList<Topic> topics = getTopics(conn);

        printTopics(topics);

        // ask person for topic
        System.out.printf("Select topic for your game [1-%d]\n", topics.size());
        int topicIndex = scanner.nextInt();
        // check if topic is in range
        if (topicIndex < 1 || topicIndex > topics.size()) {
            System.out.println("Ohh schnitzel, invalid topic selected");
            return; // Hey, lets play again? y/n
        }

        // get current topic
        Topic currentTopic = topics.get(topicIndex - 1);
        System.out.printf("You have selected %s topic%n", currentTopic.topic_name);

        // get topic words
        ArrayList<TopicWord> words = getWords(conn, currentTopic);

        Random random = new Random();
        TopicWord wordToGuess = words.get(random.nextInt(words.size()));

        List<Character> guesses = new ArrayList<Character>();
        System.out.println("Enter a letter");

        for (int i=0; i < wordToGuess.word.length(); i++) {
            System.out.print("-");
        }

        System.out.println();

        boolean guessed = false;
        int letterCount = 0;
        scanner.nextLine();
        for(int j =0; j < 10; j++){
            letterCount = 0;

            String input = scanner.nextLine().toLowerCase();
            if(input.length() > 1){
                //System.out.println("Guessing whole word");
                if(wordToGuess.word.equals(input)){
                    guessed = true;
                    break;
                }
            }else{
                //System.out.println("Guessing one letter");
                char ch = input.charAt(0);
                guesses.add(ch);
                for (int i=0; i < wordToGuess.word.length(); i++) {
                    if(guesses.contains(wordToGuess.word.charAt(i))) {
                        //first letter printout Upper case
                        if (i==0) {
                            System.out.print(Character.toUpperCase(wordToGuess.word.charAt(i)));
                        } else {
                            System.out.print(wordToGuess.word.charAt(i));
                        }
                        letterCount++;
                    }else {
                        System.out.print("-");
                    }
                }
                System.out.println();
            }
            //char ch = scanner.next().toLowerCase(Locale.ROOT).charAt(0);

            if(letterCount == wordToGuess.word.length()){
                guessed = true;
                break;
            }
        }

        if(guessed){
            System.out.println("And the winner is - YOU!" +
                    "\nCongrats, you deserve vacation");
        }else {
            System.out.println("A hard nut to crack was: " + wordToGuess.word);
            System.out.println("You suck!" +
                    "\nWork hard, play harder");

        }

        insertScoreBoard(conn, userName, currentTopic.topic_id, letterCount);
    }


    public static void insertTopic (Connection conn, String topic_name) throws SQLException {

        String sql = "INSERT INTO topics (topic_name) VALUES (?);";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, topic_name);

        int rowInserted = preparedStatement.executeUpdate();

        if(rowInserted > 0) {
            System.out.println("New Topic was added");
        }else {
            System.out.println("Ouch.. Something went wrong");
        }

    }

    public static void insertTopicAction(Connection conn) throws SQLException {
        System.out.println("Enter new Topic");
        String topic = scanner.nextLine();

        insertTopic(conn, topic);
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


    public static void insertWords (Connection conn, int topic_id, String word) throws SQLException {

        String sql = "INSERT INTO words (topic_id, word) VALUES (?, ?);";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, topic_id);
        preparedStatement.setString(2, word.toLowerCase(Locale.ROOT));

        int rowInserted = preparedStatement.executeUpdate();

        if(rowInserted > 0) {
            System.out.println("New Word was added");
        }else {
            System.out.println("Snap.. Something went wrong");
        }

    }

    public static void insertWordAction(Connection conn) throws SQLException {
        System.out.println("Enter new Word");
        String word = scanner.nextLine();

        ArrayList<Topic> topics = getTopics(conn);
        printTopics(topics);

        // ask person for topic
        System.out.printf("Select topic where to add [1-%d]\n", topics.size());

        int topicIndex = scanner.nextInt();

        if (topicIndex < 1 || topicIndex > topics.size()) {
            System.out.println("Invalid topic selected");
            return; // exit the program
        }

        // get current topic
        Topic currentTopic = topics.get(topicIndex - 1);

        insertWords(conn, currentTopic.topic_id , word);
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


    public static void insertScoreBoard (Connection conn, String username, int topic_id, int scores_moves) throws SQLException {

        String sql = "INSERT INTO scores (username, topic_id, scores_moves) VALUES (?, ?, ?);";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, topic_id);
        preparedStatement.setInt(3, scores_moves);

        int rowInserted = preparedStatement.executeUpdate();

        if(rowInserted > 0) {
            System.out.println("Your score has been saved");
        }else {
            System.out.println("Yikes.. Something went wrong");
        }

    }

    public static void readTOP10(Connection conn) throws SQLException { //KRISTINE: table visualization
        String sql = "SELECT * , ROW_NUMBER () OVER (ORDER BY scores_moves DESC) AS Ranking FROM scores LIMIT 10"; //KRISTINE: added Ranking

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("TOP10 - best of the best: ");

        String heading1 = "Ranking";
        String heading2 = "Username";
        String heading3 = "Scores";
        System.out.println("");
        System.out.printf("%-15s %-15s %15s %n", heading1, heading2, heading3);
        System.out.println("__________________________________________________");

        while (resultSet.next()) {

            int Ranking = resultSet.getInt(5);
            String username = resultSet.getString(2);
            int scores_moves = resultSet.getInt(4);

            String output = "%-15d %-15s %10d";
            System.out.println(String.format(output, Ranking, username, scores_moves));
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