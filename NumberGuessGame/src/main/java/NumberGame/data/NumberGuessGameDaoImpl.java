package NumberGame.data;

import NumberGame.models.GameData;
import NumberGame.models.RoundData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
    
    
    @Override
    public GameData createNewGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
}
