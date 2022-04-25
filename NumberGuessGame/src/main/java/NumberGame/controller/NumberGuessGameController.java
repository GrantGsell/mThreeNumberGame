package NumberGame.controller;

import NumberGame.data.NumberGuessGameDaoException;
import NumberGame.models.GameData;
import NumberGame.models.Guess;
import NumberGame.models.RoundData;
import NumberGame.service.NumberGuessGameService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final NumberGuessGameService service;
    
    // Constructor for Dependency Injection
    public NumberGuessGameController(NumberGuessGameService service){
        this.service = service;
    }
    
    //testing
    //-------------
    
//    @GetMapping
//    public int test(){
//        return service.getLastGameId();
//    }

//    @GetMapping("/newGame")
//    public GameData test0(){
//        return service.createNewGame();
//    }
    
    //---------------
    
    //spencer
    //BEGIN (POST METHOD)
    //Starts a game, generates an answer, and sets the correct status. Should return a 201
    //CREATED message as well as the created gameId
    @PostMapping("/begin")
    public String BeginGame() throws NumberGuessGameDaoException{
        
        //create integer game id value
        int retId = service.createNewGame();
        
        
        //string output for successful creation of a new game, combined with the most recently created games ID
        String ret = "Application Status 201: new game successfully created with id: " + retId;
        
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
    public RoundData guess(@RequestBody Guess guess) throws NumberGuessGameDaoException { //without passing gameid since we know which game we're currently updating
        //check for invalid guess
        if(!service.checkGuess(guess.getGuess())){
            //print error message
            System.out.println("Error due to invalid user guess");
            return null;
        } else {
            //return guess if input is valid
            return service.makeGuess(guess.getId(),guess.getGuess());
        }
        
    }
    
    
    //spencer
    //GAME (GET METHOD)
    //Returns a list of all games. Be sure in-progress games do not display their answer
    @GetMapping("/game")
    public List<GameData> viewAllGames() throws NumberGuessGameDaoException{
        return service.getAllGames();
    }
    
    
    //spencer
    //GAME/GAMEID (GET METHOD)
    //Returns a specific game based on ID. Be sure in-progress games do not display their answer
    @GetMapping("/game/{gameid}")
    public GameData viewGameById(@RequestBody int id) throws NumberGuessGameDaoException{
        return service.getGameById(id);
    }
    
    
    //spencer
    //ROUNDS/GAMEID (GET METHOD)
    //Returns a list of rounds for the specified game sorted by time
    @GetMapping("/rounds/{gameid}")
    public List<RoundData> viewGameRounds(@RequestBody int id) throws NumberGuessGameDaoException{
        return service.getAllRounds(id);
    }
    
}
