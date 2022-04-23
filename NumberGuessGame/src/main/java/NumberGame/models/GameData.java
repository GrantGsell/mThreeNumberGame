/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NumberGame.models;

/**
 *
 * @author Grant
 */
public class GameData {
    private int gameId;
    private int answerId;
    private boolean gameWon;

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
    
    
}
