USE numguess;

/*
-- Delete all previous artist records --
DELETE FROM allpossibleanswers WHERE true;

-- Read in data from csv file
LOAD DATA LOCAL INFILE "C:/Users/Grant/Desktop/mThreeSpringBoot_Number_Guess_Game/UniqueFourDigitNumbers.csv"
INTO TABLE numguess.allpossibleanswers 
FIELDS TERMINATED BY ',';
*/
-- 


SELECT * from allpossibleanswers;