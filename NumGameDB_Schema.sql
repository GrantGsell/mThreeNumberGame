DROP DATABASE IF EXISTS numguess;
CREATE DATABASE IF NOT EXISTS numguess;
USE numguess;

CREATE TABLE AllPossibleAnswers(
	answerId smallint PRIMARY KEY,
    numberValue smallint
);

CREATE TABLE RoundData(
	roundDataId int Primary KEY,
    userGuess smallint,
    results char(7),
    timeLog DATETIME
);

CREATE TABLE Games(
	gameId INT PRIMARY KEY AUTO_INCREMENT,
    answerId SMALLINT,
    gameWon boolean,
	CONSTRAINT fk_answer 
		FOREIGN KEY (answerId)
		REFERENCES AllPossibleAnswers(answerId)
);


CREATE TABLE Rounds(
	roundDataId INT PRIMARY KEY NOT NULL,
    gameId INT NOT NULL,
    CONSTRAINT fk_game
		FOREIGN KEY (gameId)
        REFERENCES Games(gameId),
	CONSTRAINT fk_rounddata
		FOREIGN KEY (roundDataId)
        REFERENCES roundData(roundDataId)
);

/*
SELECT * FROM Games;
-- Populate one full db entry for testing purposes only

-- Add two possible answers
INSERT INTO AllPossibleAnswers(answerId, numberValue)
VALUES(0, 0000);
INSERT INTO AllPossibleAnswers(answerId, numberValue)
VALUES(1, 0001);


-- Game 0 Round 1, 2, 3
INSERT INTO RoundData(roundDataId, userGuess, results, timeLog)
VALUES(0, 0006, "e:0:p:0", "2010-12-31 01:15:00");
INSERT INTO RoundData(roundDataId, userGuess, results, timeLog)
VALUES(1, 0005, "e:0:p:0", "2010-12-31 01:15:00");
INSERT INTO RoundData(roundDataId, userGuess, results, timeLog)
VALUES(2, 0004, "e:0:p:0", "2010-12-31 01:15:00");

INSERT INTO Games(gameId, answerId, gameWon)
VALUES(0, 1, false);


INSERT INTO Rounds(roundDataId, gameId) 
VALUES(0, 1);
INSERT INTO Rounds(roundDataId, gameId)
 VALUES(1, 1);
INSERT INTO Rounds(roundDataId, gameId) 
VALUES(2, 1);

SELECT * FROM Games ORDER BY gameId DESC LIMIT 1;

SELECT * 
FROM GAMES
INNER JOIN rounds USING(gameId)
INNER JOIN roundData USING(roundDataId)
INNER JOIN allPossibleAnswers USING(answerId)
ORDER BY gameId, roundDataId;
*/