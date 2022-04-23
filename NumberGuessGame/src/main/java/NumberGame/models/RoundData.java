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
    
    
    
    //constructor
    
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

    
}
