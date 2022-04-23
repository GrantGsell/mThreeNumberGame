package NumberGame.controller;

import NumberGame.data.NumberGuessGameDao;
import NumberGame.models.GameData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grant
 */
@RestController
@RequestMapping("/api/numbergame")
public class NumberGuessGameController {
    // Create DAO object
    private final NumberGuessGameDao dao;
    
    // Constructor for Dependency Injection
    public NumberGuessGameController(NumberGuessGameDao dao){
        this.dao = dao;
    }
    
    @GetMapping
    public int test(){
        return dao.getLastGameId();
    }
    
    @GetMapping("/newGame")
    public GameData test0(){
        return dao.createNewGame();
    }
}
