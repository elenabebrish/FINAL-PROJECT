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
scores_moves INT,
PRIMARY KEY (user_ID)
);

INSERT INTO topics (topic_name) VALUES ('Animals');
INSERT INTO topics (topic_name) VALUES ('Colours');
INSERT INTO topics (topic_name) VALUES ('Car brands');
INSERT INTO topics (topic_name) VALUES ('Clothes');
INSERT INTO topics (topic_name) VALUES ('EU countries');

SELECT * FROM topics;

INSERT INTO words (topic_id, word) VALUES (1, 'horse');
INSERT INTO words (topic_id, word) VALUES (1, 'giraffe');
INSERT INTO words (topic_id, word) VALUES (1, 'elephant');
INSERT INTO words (topic_id, word) VALUES (1, 'pig');
INSERT INTO words (topic_id, word) VALUES (1, 'cow');
INSERT INTO words (topic_id, word) VALUES (1, 'lion');
INSERT INTO words (topic_id, word) VALUES (1, 'tiger');
INSERT INTO words (topic_id, word) VALUES (1, 'wolf');
INSERT INTO words (topic_id, word) VALUES (1, 'cat');
INSERT INTO words (topic_id, word) VALUES (1, 'dog');

INSERT INTO words (topic_id, word) VALUES (2, 'white');
INSERT INTO words (topic_id, word) VALUES (2, 'black');
INSERT INTO words (topic_id, word) VALUES (2, 'yellow');
INSERT INTO words (topic_id, word) VALUES (2, 'green');
INSERT INTO words (topic_id, word) VALUES (2, 'brown');
INSERT INTO words (topic_id, word) VALUES (2, 'purple');
INSERT INTO words (topic_id, word) VALUES (2, 'red');
INSERT INTO words (topic_id, word) VALUES (2, 'pink');
INSERT INTO words (topic_id, word) VALUES (2, 'orange');
INSERT INTO words (topic_id, word) VALUES (2, 'blue');

INSERT INTO words (topic_id, word) VALUES (3, 'nissan');
INSERT INTO words (topic_id, word) VALUES (3, 'skoda');
INSERT INTO words (topic_id, word) VALUES (3, 'audi');
INSERT INTO words (topic_id, word) VALUES (3, 'peugeot');
INSERT INTO words (topic_id, word) VALUES (3, 'mercedes');
INSERT INTO words (topic_id, word) VALUES (3, 'lamborghini');
INSERT INTO words (topic_id, word) VALUES (3, 'ferrari');
INSERT INTO words (topic_id, word) VALUES (3, 'bmw');
INSERT INTO words (topic_id, word) VALUES (3, 'opel');
INSERT INTO words (topic_id, word) VALUES (3, 'toyota');

INSERT INTO words (topic_id, word) VALUES (4, 'skirt');
INSERT INTO words (topic_id, word) VALUES (4, 'pants');
INSERT INTO words (topic_id, word) VALUES (4, 'jeans');
INSERT INTO words (topic_id, word) VALUES (4, 'trousers');
INSERT INTO words (topic_id, word) VALUES (4, 'coat');
INSERT INTO words (topic_id, word) VALUES (4, 'dress');
INSERT INTO words (topic_id, word) VALUES (4, 'shirt');
INSERT INTO words (topic_id, word) VALUES (4, 'jumper');
INSERT INTO words (topic_id, word) VALUES (4, 'shorts');
INSERT INTO words (topic_id, word) VALUES (4, 'jacket');

INSERT INTO words (topic_id, word) VALUES (5, 'romania');
INSERT INTO words (topic_id, word) VALUES (5, 'latvia');
INSERT INTO words (topic_id, word) VALUES (5, 'germany');
INSERT INTO words (topic_id, word) VALUES (5, 'estonia');
INSERT INTO words (topic_id, word) VALUES (5, 'lithuania');
INSERT INTO words (topic_id, word) VALUES (5, 'france');
INSERT INTO words (topic_id, word) VALUES (5, 'italy');
INSERT INTO words (topic_id, word) VALUES (5, 'spain');
INSERT INTO words (topic_id, word) VALUES (5, 'hungary');
INSERT INTO words (topic_id, word) VALUES (5, 'poland');

SELECT * FROM words;

INSERT INTO scores (username, topic_id, scores_moves) VALUES ('snowflake', 3, 4);
INSERT INTO scores (username, topic_id, scores_moves) VALUES ('nacho1', 2, 3);
INSERT INTO scores (username, topic_id, scores_moves) VALUES ('Tobby', 4, 6);
INSERT INTO scores (username, topic_id, scores_moves) VALUES ('Kitty', 5, 8);
INSERT INTO scores (username, topic_id, scores_moves) VALUES ('YellowBee', 1, 1);

SELECT * FROM scores ORDER BY scores_moves ASC LIMIT 10;

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
