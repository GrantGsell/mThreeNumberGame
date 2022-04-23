/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NumberGame.models;

import java.util.List;
import NumberGame.models.RoundData;

/**
 *
 * @author Grant / SPENCER
 */
public class GameData {
    private int gameId;
    private int answerId;
    private boolean gameWon;
    
    private List<RoundData> rounds;
    private int numRounds;

    //constructor
    //not necessary???
    
    // Field Getters
    public int getGameId() {
        return gameId;
    }

    public int getAnswerId() {
        return answerId;
    }
    
    public boolean getGameWon() {
        return gameWon;
    }

    // Field Setters
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
    
    public void setGameWon(boolean gameWon){
        this.gameWon = gameWon;
    }
    
    //method to get the list of rounds for this game object
    public List<RoundData> getRounds() {
        return rounds;
    }
    
    //method to add a round to the list of rounds for this game object
    public void addRound(RoundData newRound) {
        rounds.add(newRound);
        numRounds++;
    }
    
    
}
