/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NumberGame.service;

import NumberGame.data.NumberGuessGameDao;
import NumberGame.models.GameData;
import NumberGame.models.RoundData;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author SPENCER
 */
public class NumberGuessGameService {
    
    private NumberGuessGameDao dao;
    
    public NumberGuessGameService(NumberGuessGameDao dao) {
        this.dao = dao;
    }
    
    //create new game uses the dao create new game method and uses the returned gameId
    public int createNewGame() {
        
        //create new game returns a game id to be used in the controller
        return dao.createNewGame();
    }
    
    //make guess method to return a round object to the controller
    public RoundData makeGuess(int id, int guess) {
        
        RoundData newround;
        
        //set results using result method ****NICOLE*****need to add new method******NICOLE******
        newround.setResults();
        //set user guess using input from controller
        newround.setUserGuess(guess);
        //set local time to now 
        newround.setTimeLog(LocalDate.now());
        
        //return newly created round object
        return newround;
    }
    
    //return list of games 
    public List<GameData> getAllGames() {
        return dao.getAllGames();
    }
    
    
    //return game by id
    public GameData getGameById(int id) {
        return dao.getGameById(id);
    }
    
    
    //return list of rounds by game id
    public List<RoundData> getAllRounds(int id) {
        return dao.getAllRoundsOneGame(id);
    }
    
}
