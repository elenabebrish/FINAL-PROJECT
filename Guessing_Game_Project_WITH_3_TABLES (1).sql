DROP TABLE IF EXISTS topics;
CREATE TABLE topics(
topic_id INT NOT NULL AUTO_INCREMENT,
topic_name TEXT NOT NULL,
PRIMARY KEY (topic_id)
);

DROP TABLE IF EXISTS words;
CREATE TABLE words(
word_id INT NOT NULL AUTO_INCREMENT,
topic_id INT NOT NULL REFERENCES topics(topic_id),
word TEXT NOT NULL,
PRIMARY KEY (word_id)
);

DROP TABLE IF EXISTS scores;
CREATE TABLE scores(
user_ID INT NOT NULL AUTO_INCREMENT,
username VARCHAR(45) NOT NULL,
topic_id INT REFERENCES topics(topic_id),
scores_results INT,
number_of_tries INT,
PRIMARY KEY (user_ID)
);

INSERT INTO topics (topic_name) VALUES ('Animals');
INSERT INTO topics (topic_name) VALUES ('Colours');
INSERT INTO topics (topic_name) VALUES ('Car brands');
INSERT INTO topics (topic_name) VALUES ('Clothes');
INSERT INTO topics (topic_name) VALUES ('EU countries');

SELECT * FROM topics;

INSERT INTO words (topic_id, word) VALUES (1, 'Horse');
INSERT INTO words (topic_id, word) VALUES (1, 'Giraffe');
INSERT INTO words (topic_id, word) VALUES (1, 'Elephant');
INSERT INTO words (topic_id, word) VALUES (1, 'Pig');
INSERT INTO words (topic_id, word) VALUES (1, 'Cow');
INSERT INTO words (topic_id, word) VALUES (1, 'Lion');
INSERT INTO words (topic_id, word) VALUES (1, 'Tiger');
INSERT INTO words (topic_id, word) VALUES (1, 'Wolf');
INSERT INTO words (topic_id, word) VALUES (1, 'Cat');
INSERT INTO words (topic_id, word) VALUES (1, 'Dog');

INSERT INTO words (topic_id, word) VALUES (2, 'White');
INSERT INTO words (topic_id, word) VALUES (2, 'Black');
INSERT INTO words (topic_id, word) VALUES (2, 'Yellow');
INSERT INTO words (topic_id, word) VALUES (2, 'Green');
INSERT INTO words (topic_id, word) VALUES (2, 'Brown');
INSERT INTO words (topic_id, word) VALUES (2, 'Purple');
INSERT INTO words (topic_id, word) VALUES (2, 'Red');
INSERT INTO words (topic_id, word) VALUES (2, 'Pink');
INSERT INTO words (topic_id, word) VALUES (2, 'Orange');
INSERT INTO words (topic_id, word) VALUES (2, 'Blue');

INSERT INTO words (topic_id, word) VALUES (3, 'Nissan');
INSERT INTO words (topic_id, word) VALUES (3, 'Skoda');
INSERT INTO words (topic_id, word) VALUES (3, 'Audi');
INSERT INTO words (topic_id, word) VALUES (3, 'Peugeot');
INSERT INTO words (topic_id, word) VALUES (3, 'Mercedes');
INSERT INTO words (topic_id, word) VALUES (3, 'Lamborghini');
INSERT INTO words (topic_id, word) VALUES (3, 'Ferrari');
INSERT INTO words (topic_id, word) VALUES (3, 'BMW');
INSERT INTO words (topic_id, word) VALUES (3, 'Opel');
INSERT INTO words (topic_id, word) VALUES (3, 'Toyota');

INSERT INTO words (topic_id, word) VALUES (4, 'Skirt');
INSERT INTO words (topic_id, word) VALUES (4, 'Pants');
INSERT INTO words (topic_id, word) VALUES (4, 'Jeans');
INSERT INTO words (topic_id, word) VALUES (4, 'Trousers');
INSERT INTO words (topic_id, word) VALUES (4, 'Coat');
INSERT INTO words (topic_id, word) VALUES (4, 'Dress');
INSERT INTO words (topic_id, word) VALUES (4, 'Shirt');
INSERT INTO words (topic_id, word) VALUES (4, 'Jumper');
INSERT INTO words (topic_id, word) VALUES (4, 'Shorts');
INSERT INTO words (topic_id, word) VALUES (4, 'Jacket');

INSERT INTO words (topic_id, word) VALUES (5, 'Romania');
INSERT INTO words (topic_id, word) VALUES (5, 'Latvia');
INSERT INTO words (topic_id, word) VALUES (5, 'Germany');
INSERT INTO words (topic_id, word) VALUES (5, 'Estonia');
INSERT INTO words (topic_id, word) VALUES (5, 'Lithuania');
INSERT INTO words (topic_id, word) VALUES (5, 'France');
INSERT INTO words (topic_id, word) VALUES (5, 'Italy');
INSERT INTO words (topic_id, word) VALUES (5, 'Spain');
INSERT INTO words (topic_id, word) VALUES (5, 'Hungary');
INSERT INTO words (topic_id, word) VALUES (5, 'Poland');

SELECT * FROM words;

INSERT INTO scores (username, topic_id, scores_results, number_of_tries) VALUES ('snowflake', 3, 4, 6);
INSERT INTO scores (username, topic_id, scores_results, number_of_tries) VALUES ('nacho1', 2, 3, 8);
INSERT INTO scores (username, topic_id, scores_results, number_of_tries) VALUES ('Tobby', 4, 6, 5);
INSERT INTO scores (username, topic_id, scores_results, number_of_tries) VALUES ('Kitty', 5, 8, 9);
INSERT INTO scores (username, topic_id, scores_results, number_of_tries) VALUES ('YellowBee', 1, 1, 3);

SELECT * FROM scores;

-- Retrieve Animal words by topic ID
SELECT * FROM words WHERE topic_id = 1;

-- Retrieve Colours words by topic ID
SELECT * FROM words WHERE topic_id = 2;

-- Retrieve Car brand words by topic ID
SELECT * FROM words WHERE topic_id = 3;

-- Retrieve Clothes words by topic ID
SELECT * FROM words WHERE topic_id = 4;

-- Retrieve EU countries words by topic ID
SELECT * FROM words WHERE topic_id = 5;

