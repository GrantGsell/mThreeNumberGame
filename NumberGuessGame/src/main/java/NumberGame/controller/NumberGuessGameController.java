package NumberGame.controller;

import NumberGame.data.NumberGuessGameDao;
import NumberGame.models.GameData;
import NumberGame.models.RoundData;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grant SPENCER
 */
@RestController
@RequestMapping("/api/numbergame")
public class NumberGuessGameController {
    // Create DAO object
    private final NumberGuessGameDao dao;
    private List<GameData> AllGames;
    private int currGameIndex;
    
    // Constructor for Dependency Injection
    public NumberGuessGameController(NumberGuessGameDao dao){
        this.dao = dao;
        currGameIndex = 0;
    }
    
    //testing
    //-------------
    
    @GetMapping
    public int test(){
        return dao.getLastGameId();
    }

    @GetMapping("/newGame")
    public GameData test0(){
        return dao.createNewGame();
    }
    
    //---------------
    
    //spencer
    //BEGIN (POST METHOD)
    //Starts a game, generates an answer, and sets the correct status. Should return a 201
    //CREATED message as well as the created gameId
    @PostMapping("/begin")
    public String BeginGame() {
        
        //add a new game to the games list
        AllGames.add(dao.createNewGame());
        
        
        //string output for successful creation of a new game, combined with the most recently created games ID
        String ret = "Application Status 201: new game successfully created with id: " + Integer.toString(AllGames.get(currGameIndex).getGameId());
        
        //increment current game index value
        currGameIndex++;
        
        return ret;
    }
    
    
    //spencer
    //GUESS (POST METHOD)
    //Makes a guess by passing the guess and gameId in JSON 
    //The program must calculate the results of the game guess and mark the game finished if the guess
    //is correct. It returns the Round object with the results filled in
    
    //INFO
    //we're approaching this method a bit differently than the directions
    //since we know the current game id, we don't need to pass in a game id
    //we only need to pass in the updated value
    //
    //we return the newly created round object
    
    @PostMapping("/guess")
    public RoundData guess(int value) { //without passing gameid since we know which game we're currently updating
        
        //create new round object using user guess
        //need to fix Dao to take in a user guess and make a new round object with a
        //properly formatted time object as well
        RoundData newRound = dao.addNewRoundData(value);
        
        //add new round to current game object in list
        AllGames.get(currGameIndex).addRound(newRound);
        
        return newRound;
    }
    
    
    //spencer
    //GAME (GET METHOD)
    //Returns a list of all games. Be sure in-progress games do not display their answer
    @GetMapping("/game")
    public List<GameData> viewAllGames(){
        
        //create new list so we can alter the in-progress games answer
        List<GameData> ret = AllGames;
        
        //set to a custom answer id to avoid showing the answer of the in-progress game
        ret.get(currGameIndex).setAnswerId(99999);
        
        return ret;
    }
    
    
    //spencer
    //GAME/GAMEID (GET METHOD)
    //Returns a specific game based on ID. Be sure in-progress games do not display their answer
    @GetMapping("/game/gameid")
    public GameData viewGameById(int id) {
        
        //find game by id
        GameData ret = dao.getGameById(id);
        
        //if that game hasn't been won we change the answer id to a custom hidden id
        if(!ret.getGameWon()) {
            ret.setAnswerId(99999);
        }
        
        //return specific game object
        return ret;
    }
    
    
    //spencer
    //ROUNDS/GAMEID (GET METHOD)
    //Returns a list of rounds for the specified game sorted by time
    @GetMapping("/rounds/gameid")
    public List<RoundData> viewGameRounds(int id) {
        
        //create temp game object by specified id
        GameData temp = dao.getGameById(id);
        
        //return list of rounds for that game object
        return temp.getRounds();
    }
    
}
