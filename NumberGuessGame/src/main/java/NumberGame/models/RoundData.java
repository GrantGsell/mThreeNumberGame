package NumberGame.models;

import java.time.LocalDate;

/**
 *
 * @author Grant / SPENCER
 */
public class RoundData {
    // Fields
    private int roundDataId;
    private int userGuess;
    private String results;
    private LocalDate timeLog;
    
    
    
    //constructors
    
    public RoundData() {
        
    }
    
    //need to fix this so that it passes in a properly formatted LocalDate object
    public RoundData(int id, int guess, String results, LocalDate time){
        this.roundDataId = id;
        this.userGuess = guess;
        this.results = results;
        this.timeLog = time; 
    }
    
    
    // Field Setters
    public void setRoundDataId(int roundDataId) {
        this.roundDataId = roundDataId;
    }

    public void setUserGuess(int userGuess) {
        this.userGuess = userGuess;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public void setTimeLog(LocalDate timeLog) {
        this.timeLog = timeLog;
    }
    
    // Field Getters
    public int getRoundDataId() {
        return roundDataId;
    }

    public int getUserGuess() {
        return userGuess;
    }

    public String getResults() {
        return results;
    }

    public LocalDate getTimeLog() {
        return timeLog;
    }
    
    //results SPENCER
    public String makeResults(int answer) {
        //convert both integers to strings
        String answerStr = Integer.toString(answer);
        String guessStr = Integer.toString(userGuess);
        //convert those strings to character arrays
        char[] answerArr = answerStr.toCharArray();
        char[] guessArr = guessStr.toCharArray();
        //create variables for exact and partial matches
        int exactCounter = 0;
        int partialCounter = 0;
        //iterate through character arrays
        for(int i = 0; i < 4; i++) {
            //create strings for individual characters for both arrays at specified indices
            String Aidx = Character.toString(answerArr[i]);
            String Gidx = Character.toString(guessArr[i]);
            //check for exact match
            if(Aidx.equals(Gidx)) {
                exactCounter++;
            }
            //check for partial match (and not exact match)
            if(guessStr.contains(Aidx) && !Aidx.equals(Gidx)) {
                partialCounter ++;
            }
        }
        //create string object to store and return results
        String ret = "e:" + Integer.toString(exactCounter) + "p:" + Integer.toString(partialCounter);
        //set this objects results string to what we got
        this.results = ret;
        //return combined results string
        return ret;
    }

    
}
