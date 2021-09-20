/*=============================================================================
Code Breaker
Terry Zha and Jonathan Xie
September 23, 2020
Java 13.0.2
The main class of the Code Breaker
===============================================================================
*/
import gamelogic.Game;


public class Launcher {

    /**main method:
     * This procedural method is called automatically and is used to organize the calling of other methods
     *
     * List of Local Variables:
     * game - the main game object used for game logic, gui, and mouseIO </type Game>
     * @param args ...
     */
    public static void main(String[] args){
        Game game = new Game("Tic Tac Toe", 1024, 768);
        game.start();
    }

}
