/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NumberGame.data;

/**
 *
 * @author 17202
 */
public class NumberGuessGameDaoException extends Exception{
    public NumberGuessGameDaoException(String message) {
        super(message); //super: calls construction of super class (Exception)
    }
    
    public NumberGuessGameDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
