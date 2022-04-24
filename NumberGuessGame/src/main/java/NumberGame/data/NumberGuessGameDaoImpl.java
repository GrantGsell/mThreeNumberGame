package NumberGame.data;

import NumberGame.models.GameData;
import NumberGame.models.RoundData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    public int createNewGame() throws NumberGuessGameDaoException {
        // Create variables for lastGameId, answerId
        int lastGameId, answerId;
        
        // Obtain the last game id
        lastGameId = getLastGameId();
        
        // Generate a random answer Id
        answerId = generateRandomId();
        
        // Create a new GameData object and set fields
        GameData newGame = new GameData();
        newGame.setGameId(lastGameId + 1);
        newGame.setAnswerId(answerId);
        newGame.setAnswerId(1);
        newGame.setGameWon(false);
        
        // Create statement string
        final String sql = "INSERT INTO Games(gameId, answerId, gameWon) VALUES"
                + "(?, ?, ?);";
        
        // Insert new game into database
        jdbcTemplate.update(sql, newGame.getGameId(), newGame.getAnswerId(), 
                newGame.getGameWon());
        
        return newGame.getGameId();
    }

    
    /**
     * Obtains the id for the last played game. If this is the first game the 
     *   method will return null. Otherwise it will return the integer id of the
     *   last game played.
     * 
     * @return 
     */
    @Override
    public int getLastGameId() throws NumberGuessGameDaoException {
        // Create statement string
        final String sql = "SELECT * FROM Games ORDER BY gameId DESC LIMIT 1";
        
        // Obtain GameData object for the last game played,(single element list)
        List<GameData> lastGameList = jdbcTemplate.query(sql, new GameDataMapper());
        
        // Check to see if lastGameList has a size of 0
        if(lastGameList.size() == 0)
            return 0;
        
        // Get the last GameData object
        GameData lastGame = lastGameList.get(0);
        
        // Return the GameId for the last game object
        return lastGame.getGameId();
    }

    
    /**
     * Obtains a list of all Game objects, which contains Game Ids, and if the 
     *   game is finished (won) or not.
     * 
     * @return A list of Game objects
     */
    @Override
    public List<GameData> getAllGames() throws NumberGuessGameDaoException {
        // Create a statement string
        final String sql = "SELECT * FROM Games";
        
        // Declare a list to hold all of the game data
        List<GameData> allGames;
        
        // Obtain a list of all games
        allGames = jdbcTemplate.query(sql, new GameDataMapper());
        
        // Return the list of all Games
        return allGames;
    }


    /**
     * Adds round data to the RoundData table. First obtains the id of the last
     *   round to be played and increments it by 1 to create a roundId for this
     *   new round. Data is then placed into the RoundData table, after which 
     *   the new roundId and provided gameId data are placed in the rounds
     *   bridge table.
     * 
     * @param gameId the id for the associated game.
     */
    @Override
    public void addNewRoundData(int gameId, RoundData roundData) throws NumberGuessGameDaoException {
        // Create variable for id of the last round played
        int roundId = getLastRoundId() + 1;
        
        // Set roundId for roundData object
        roundData.setRoundDataId(roundId);
        
        // Create sql statement for RoundData table
        final String sql0 = "INSERT INTO RoundData(roundDataId, userGuess, "
                + "results, timeLog) VALUES(?, ?, ?, ?);";
        
        // Execute the roundData table statement
        jdbcTemplate.update(sql0, roundData.getRoundDataId(), 
                roundData.getUserGuess(), roundData.getResults(), roundData.getTimeLog());
        
        // Create sql statement for rounds table
        final String sql1 = "INSERT INTO Ronuds(roundDataId, gameId) "
                + "VALUES(?, ?)";
        
        // Execute the rounds table statement
        jdbcTemplate.update(sql1, gameId, roundData.getRoundDataId());
    }

    
    /**
     * Obtains the id for the last round played. If this is the first round the 
     *   method will return null. Otherwise it will return the integer id of the
     *   last round played.
     * 
     * @return 
     */
    @Override
    public int getLastRoundId() throws NumberGuessGameDaoException {
        // Create statement string
        final String sql = "SELECT * FROM Rounds ORDER BY RoundDataId DESC LIMIT 1";
        
        // Obtain round id for the last round played (single elment list)
        List<Integer> roundId;
        roundId = jdbcTemplate.query(sql, new RoundIdMapper());

        // Return the GameId for the last game object
        return roundId.get(0);
    }

    
    /**
     * Obtains a game object based on its game id.
     * 
     * @param gameId the associated id for the GameData object.
     * @return the GameData object associated with gameId if it exists otherwise
     * null.
     */
    @Override
    public GameData getGameById(int gameId) throws NumberGuessGameDaoException {
        // Create sql statement
        final String sql = "SELECT * FROM Games WHERE gameId = " + gameId + ";";
        
        // Create List to hold all RoundData Objects
        List<GameData> results;
        
        // Execute the query to obtain single elment list
        results = jdbcTemplate.query(sql, new GameDataMapper());
   
        // Return the results list
        return results.get(0);
    }

    
    /**
     * Obtains a list of all round data objects associated with the given 
     *   gameId.
     * 
     * @param gameId the id of the GameData object whose rounds we want to see.
     * @return a list of RoundData objects containing the data associated with
     *   each round of the game specified by gameId.
     */
    @Override
    public List<RoundData> getAllRoundsOneGame(int gameId) throws NumberGuessGameDaoException {
        // Create sql statement
        final String sql = "SELECT * FROM Rounds INNER JOIN RoundData "
                + "USING(roundDataId) WHERE gameId = " + gameId + ";";
        
        // Create List to hold all RoundData Objects
        List<RoundData> results;
        
        // Execute the query 
        results = jdbcTemplate.query(sql, new RoundDataMapper());
   
        // Return the results list
        return results;
    }

    
    /**
     * Obtains the four digit answer associated with the given id.
     * 
     * @param answerId correlates to a four digit number.
     * @return the four digit number associated with the id.
     */
    @Override
    public int getAnswerFromId(int answerId) throws NumberGuessGameDaoException {
        // Create sql statement
        final String sql = "SELECT * FROM AllPossibleAnswers WHERE answerId = "
                + answerId + ";";
        
        // Create a list to hold the single result value
        List<Integer> results;
        
        // Execute the query and store results in list
        results = jdbcTemplate.query(sql, new AnswerMapper());
        
        // Return the element within the results list
        return results.get(0);
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
     * Creates mapping class to map MySQL rounds data table into a single
     *   output value for roundsId
     */
    private static final class RoundIdMapper implements RowMapper<Integer>{

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            // Create new variable to hold round id data
            int roundId;
            
            // Read in the round id from the result set object
            roundId = rs.getInt("roundDataId");
            
            // Return the roundId
            return roundId;
        }
        
    }
    
    
    /**
     * Creates a mapping class to map, then extract the four digit answer that
     *   is associated with an answerId
     */
    private static final class AnswerMapper implements RowMapper<Integer>{

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            // Create new variable to hold round id data
            int answer;
            
            // Read in the round id from the result set object
            answer = rs.getInt("numberValue");
            
            // Return the roundId
            return answer;
        }    
    }
    
    
    /**
     * Creates a mapping class to map MySQL RoundData table into a list of
     *   RoundData objects
     */
    private static final class RoundDataMapper implements RowMapper<RoundData>{

        @Override
        public RoundData mapRow(ResultSet rs, int i) throws SQLException {
            // Create new object to hold RoundData 
            RoundData roundData = new RoundData();
            
            // Populate the fields of roundData
            roundData.setRoundDataId(rs.getInt("roundDataId"));
            roundData.setUserGuess(rs.getInt("userGuess"));
            roundData.setResults(rs.getString("results"));
            roundData.setTimeLog(rs.getDate("timeLog").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            
            // Return the roundData object
            return roundData;
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
