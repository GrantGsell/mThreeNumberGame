DROP DATABASE numbergame;

CREATE DATABASE numbergame;

USE numbergame;


CREATE TABLE Num (
	numId INT,
    numValue INT,
    isGoal BOOLEAN,
    CONSTRAINT pk_num
		PRIMARY KEY(numId)
);

CREATE TABLE roundNumber (
	roundNumberId INT,
    numId INT,
    CONSTRAINT pk_roundNum
		PRIMARY KEY(roundNumberId),
	CONSTRAINT fk_roundNum_num
		FOREIGN KEY(numId)
        REFERENCES num(numId)
);

CREATE TABLE Rounds (
	roundId INT,
    roundNumberId INT,
    roundTime DATETIME,
    CONSTRAINT pk_round
		PRIMARY KEY(roundId),
	CONSTRAINT fk_Rounds_roundNumber
		FOREIGN KEY(roundNumberId)
        REFERENCES roundNumber(roundNumberId)
);

CREATE TABLE gameRound (
	gameRoundId INT,
    roundId INT,
    CONSTRAINT pk_gameRound
		PRIMARY KEY(gameRoundId),
	CONSTRAINT fk_gameRound_round
		FOREIGN KEY(roundId)
        REFERENCES Rounds(roundId)
);

CREATE TABLE Game (
	gameId INT,
    gameRoundId INT,
    goalNumberId INT,
    gameOver BOOLEAN,
    CONSTRAINT pk_game
		PRIMARY KEY(gameId),
	CONSTRAINT fk_Game_gameRound
		FOREIGN KEY (gameRoundId)
        REFERENCES gameRound(gameRoundId)
);


CREATE TABLE goalNumber (
	goalNumberId INT,
    numId INT,
    gameId INT,
    CONSTRAINT pk_goalNumber
		PRIMARY KEY(goalNumberId),
	CONSTRAINT fk_goalNumber_game
		FOREIGN KEY(gameId)
        REFERENCES game(gameId)
);





