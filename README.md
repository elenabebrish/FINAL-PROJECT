Game GuessMyWord
OBJECTIVE:
This is final project for Java beginner course in program "She goes tech". It's main agenda is to showcase learned Java basic skills by creating game, including simple database using MySQL Workbench.

GAME DESCRIPTION:
GuessMyWord is played by 1 player. The Player’s goal is to guess all letters of the word from the chosen topic using as less tries as possible. Each topic has its ID and each word has its ID and is unique.

GAME RULES:
1.	The player starts the game.
2.	The Player writes its name and then chooses one of 5 suggested topics.
3.	The game chooses random word from this topic for user to guess it.
4.	The player guesses one letter after another until the whole word is written in display
5.	After the game the Player can see score board with results of the game: username, score, number of tries
IMPLEMENTED GAME LOGIC:
1.	Game starts with suggestion to enter Player’s name: "Please enter your username"
2.	Then the Player is invited to choose a necessary action: “Choose and enter number to take followed action”
  1-	To play game
  2-	To add new topic
  3-	To add new word
  4-	To see score board
  5-	To quit game

3.	If the Player decides to play game (action 1), he will be suggested to choose the topic ID from the list of 5 topics existing in the database. Each topic contains 10 words. The word to quess is chosen at random.
4.	The Player enters a letter. If the word contains such a letter, it will be displayed as, for example: --a--- and if tries limit is not exceeded it will be suggested to the Player to continue guessing letters. When all the letters of the word are guessed, the Players gets a message “You won”. If the word is nt guessed and tries limit is exceeded, the Players gets a message “You suck!”.
5.	When the word is guessed, the Player is invited to play one more time: “Hey, let’s play again? y/n”
•	y - game goes back to starting point and asks first question
•	n - game exits
3.	If the Player chooses the 2nd action (adding new topic) or the 3rd action (adding new word for the existing topic), he is invited to add new topic and words for it. If it is done successfully, there will be a message: “New topic was added”, “New word was added”. If no, the message will be: “Something went wrong”.
4.	If the Player chooses the 4th action (to see score board), then the results of the previous games (ID, username, score, number of tries) will be displayed.
5.	Choosing the 5th action, the game will stop for the Player.

CODE:
Code is written in Java on IntellJ platform and database is created in MySQL Workbench.

DB STRUCTURE:
![DB_EER_Diagram_3_tables](https://user-images.githubusercontent.com/103490561/164762499-fb2f72d3-e12b-467b-9272-c5f7c8f867e1.png)


FLOW DIAGRAM:
![FD (1)](https://user-images.githubusercontent.com/103490561/164621166-250f5a1c-b99c-48b4-92b0-a7023f09f2f6.png)


