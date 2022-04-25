package NumberGame.models;

import java.time.LocalDate;
import java.util.HashSet;

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

    
    /**
     * Compares the userGuess to the answer number to find the number of partial
     *   and exact matches.
     * 
     * @param answer, the answer for this game.
     * @return A string denoting the number of exact and partial values.
     */
    public String compareGuessToResults(int answer){       
        // Turn the answer into a HashSet of digits
        HashSet<Integer> digits = extractDigits(answer);
        
        // Create variables to hold exact, partial count
        int exact = 0, partial = 0; 
        
        // Loop through answer,guess and compare digits
        while(answer > 0){
            // Extract the respective digits
            int answerDig = answer % 10;
            int guessDig = userGuess % 10;
            
            // Compare the two digits for exactness
            if(answerDig == guessDig)
                exact++;    
            // Look for the digit in set
            else if(digits.contains(guessDig))
                partial++;
            
            // Decrement the answer, guess by a factor of 10 to get the next digit
            answer /= 10;
            userGuess /= 10;
        }
        // Create return string
        String result = "e:" + exact + "p:" + partial;
        
        return result;
    }
    
    
    /**
     * Takes an integer, then extract and store each of its digits into a
     *   HashSet. On each iteration of the while loop the ones digit is stripped
     *   from the number value, stores that digit into the results set, and then
     *   reduces the number variable by a factor of 10 for the next loop 
     *   iteration.
     * 
     * @param number the number whose digits will be extracted.
     * @return A hashset containing the individual digits of the input number.
     */
    private HashSet<Integer> extractDigits(int number){
        // Create results hashset
        HashSet<Integer> results = new HashSet<>();
        
        // Loop through and extract digits and adding to set
        while(number > 0){
            // Extract the digit
            int digit = number % 10;
            
            // Add digit to HashSet
            results.add(digit);
            
            // Decrease answer by a factor of 10;
            number /= 10;
        }
        // Return results hashset
        return results;
    }
}
