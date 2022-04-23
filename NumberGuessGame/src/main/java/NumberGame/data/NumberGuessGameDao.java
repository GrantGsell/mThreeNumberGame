package NumberGame.data;

import NumberGame.models.GameData;
import NumberGame.models.RoundData;
import java.util.List;

/**
 *
 * @author Grant
 */
public interface NumberGuessGameDao {
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
    int createNewGame();
    
    
    /**
     * Obtains the id for the last played game. If this is the first game the 
     *   method will return null. Otherwise it will return the integer id of the
     *   last game played.
     * 
     * @return 
     */
    int getLastGameId();
    
    
    /**
     * Obtains a list of all Game objects, which contains Game Ids, and if the 
     *   game is finished (won) or not.
     * 
     * @return A list of Game objects
     */
    List<GameData> getAllGames();
    
    
    /**
     * Adds round data to the RoundData table. First obtains the id of the last
     *   round to be played and increments it by 1 to create a roundId for this
     *   new round. Data is then placed into the RoundData table, after which 
     *   the new roundId and provided gameId data are placed in the rounds
     *   bridge table.
     * 
     * @param gameId the id for the associated game.
     */
    void addNewRoundData(int gameId, RoundData roundData);
    
    /**
     * Obtains the id for the last round played. If this is the first round the 
     *   method will return null. Otherwise it will return the integer id of the
     *   last round played.
     * 
     * @return 
     */
    int getLastRoundId();
    
    /**
     * Obtains a game object based on its game id.
     * 
     * @param gameId the associated id for the GameData object.
     * @return the GameData object associated with gameId if it exists
   otherwise null.
     */
    GameData getGameById(int gameId);
    
    /**
     * Obtains a list of all round data objects associated with the given 
     *   gameId.
     * 
     * @param gameId the id of the GameData object whose rounds we want to see.
     * @return a list of RoundData objects containing the data associated with
     *   each round of the game specified by gameId.
     */
    List<RoundData> getAllRoundsOneGame(int gameId);
    
    
    /**
     * Obtains the four digit answer associated with the given id.
     * 
     * @param answerId, the id that is associated with some four digit number
     *   from the AllPossibleAnswers table.
     * @return the four digit number that is associated with the given answerId.
     */
    int getAnswerFromId(int answerId);
    
}
