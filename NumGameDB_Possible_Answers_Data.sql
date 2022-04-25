USE numguess;

-- Delete all previous artist records --
SET SQL_SAFE_UPDATES = 0;
DELETE FROM allpossibleanswers WHERE answerId > -1;
SET SQL_SAFE_UPDATES = 1;

-- Read in data from csv file
LOAD DATA LOCAL INFILE "C:/Users/Grant/Desktop/mThreeSpringBoot_Number_Guess_Game/UniqueFourDigitNumbers.csv"
INTO TABLE numguess.allpossibleanswers 
FIELDS TERMINATED BY ',';
-- 


SELECT * from allpossibleanswers;