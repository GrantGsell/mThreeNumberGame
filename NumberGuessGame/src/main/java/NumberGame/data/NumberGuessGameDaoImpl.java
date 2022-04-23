package NumberGame.data;

import NumberGame.models.GameData;
import NumberGame.models.RoundData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Grant
 */
@Repository
public class NumberGuessGameDaoImpl implements NumberGuessGameDao{
    // JDBC template variable
    private final JdbcTemplate jdbcTemplate;

    // Constructor for Spring Boot REST Service with JDBC Template
    @Autowired
    NumberGuessGameDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    /**
     * Creates a new game with a new four digit number for guessing.
     * 
     * The new game id is created by obtaining the last game id from the Games
     *   table.
     * 
     * Generates a new randomized id for a possible answer. From 1000-9999 there
     *   are 4536 numbers who digits are unique. So for four digit numbers the
     *   random number to be generated is in the range [0, 4535]. The id will
     *   then be used to select a corresponding four digit number from the 
     *   All possible answers table
     * 
     * @return the newly created instance of GameData with is associated id.
     */
    @Override
    public GameData createNewGame() {
        // Create variables for lastGameId, answerId
        int lastGameId, answerId;
        
        // Obtain the last game id
        lastGameId = getLastGameId();
        
        // Generate a random answer Id
        answerId = generateRandomId();
        
        // Create a new GameData object and set fields
        GameData newGame = new GameData();
        newGame.setGameId(lastGameId + 1);
        //newGame.setAnswerId(answerId);
        newGame.setAnswerId(1);
        newGame.setGameWon(false);
        
        // Create statement string
        final String sql = "INSERT INTO Games(gameId, answerId, gameWon) VALUES"
                + "(?, ?, ?);";
        
        // Insert new game into database
        jdbcTemplate.update(sql, newGame.getGameId(), newGame.getAnswerId(), 
                newGame.getGameWon());
        
        return newGame;
    }

    
    /**
     * Obtains the id for the last played game. If this is the first game the 
     *   method will return null. Otherwise it will return the integer id of the
     *   last game played.
     * 
     * @return 
     */
    @Override
    public int getLastGameId() {
        // Create statement string
        final String sql = "SELECT * FROM Games ORDER BY gameId DESC LIMIT 1";
        
        // Obtain GameData object for the last game played,(single element list)
        List<GameData> lastGameList = jdbcTemplate.query(sql, new GameDataMapper());
        
        // Get the last GameData object
        GameData lastGame = lastGameList.get(0);
        
        // Return the GameId for the last game object
        return lastGame.getGameId();
    }

    
    @Override
    public List<GameData> getAllGames() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public void addNewRoundData(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public int getLastRoundId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public GameData getGameById(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public List<RoundData> getAllRoundsOneGame(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    /**
     * Creates mapping class to map MySQL data from table into NumberGuessGame 
     *   object
     */
    private static final class GameDataMapper implements RowMapper<GameData>{

        @Override
        public GameData mapRow(ResultSet rs, int i) throws SQLException {
            GameData game = new GameData();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswerId(rs.getInt("answerId"));
            game.setGameWon(rs.getBoolean("gameWon"));
            return game;
        }
        
    }
    
    
    /**
     * Generates a new randomized integer which correlate to an id for a 
     *   possible answer. From 1000-9999 there are 4536 numbers who digits are 
     *   unique. So for four digit numbers the random number to be generated is 
     *   in the range [0, 4535]. The id will then be used to select a 
     *   corresponding four digit number from the All possible answers table.
     * 
     * @return an int value in the range [0, 4535]
     */
    private static int generateRandomId(){
        // Create variables for min and max value
        int min = 0, max = 4535;
        
        // Declare a random object, seed using system current time
        Random rand = new Random(System.currentTimeMillis());
        
        // Generate a random number in the range
        int randomNumber = rand.nextInt((max - min) + 1) + min;
        
        // Return the randomly generated number
        return randomNumber;
    }
}
