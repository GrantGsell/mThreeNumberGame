package NumberGame.models;

import java.time.LocalDate;

/**
 *
 * @author Grant
 */
public class RoundData {
    // Fields
    private int roundDataId;
    private int userGuess;
    private String results;
    private LocalDate timeLog;
    
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
