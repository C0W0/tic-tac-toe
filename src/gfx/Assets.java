/*==========================================================================================
Codebreaker
Terry Zha and Jonathan Xie
November 1, 2020
Java 13.0.2
The assets class of the Code Breaker
This class imports all graphics files (type png) and stores them in BufferedImage variables for GUI usage

List of Global Variables:
arial28 - size 28 arial font used to output computer display messages <type Font>
arial30 - size 20 arial font used to output computer display messages  <type Font>
emptySlot - graphic used to indicate an empty slot for colours to be displayed on the gameboard <type BufferedImage>
pointer - graphic used to indicate/point to the selected number in the options menu  <type BufferedImage>
backButton - button graphic used to navigate to the previous menu state <type BufferedImage>
restartButton - button graphic used to restart/re-initialize the gameboard <type BufferedImage>
forwardButton - button graphic used to scroll forward through the rules pages  <type BufferedImage>
playButton - button graphic used to navigate to the "Play Menu" state  <type BufferedImage>
rulesAboutButton - button graphic used to navigate to the "Rules/About Menu" state <type BufferedImage>
exitButtonbutton - button graphic used to exit/terminate the program <type BufferedImage>
optionsButton - button graphic used to navigate to the "Options" state <type BufferedImage>
computerCodebreakerButton - button graphic used to navigate to the "Computer Codebreaker Menu" state <type BufferedImage>
playerCodebreakderButton - button graphic used to navigate to the player codebreaker game state <type BufferedImage>
pvpCodebreakerButton - button graphic used to navigate to the player vs player codebreaker game state <type BufferedImage>
easyButton - button graphic used to navigate to the easy AI game state <type BufferedImage>
mediumButton - button graphic used to navigate to the medium AI game state <type BufferedImage>
hardButton - button graphic used to navigate to the hard AI game state <type BufferedImage>
rulesButton - button graphic used to navigate to the "Rules" state <type BufferedImage>
aboutButton - button graphic used to navigate to the "About Us" state <type BufferedImage>
blackPegButton - button graphic used to add a black feedback peg <type BufferedImage>
whitePegButton - button graphic used to add a white feedback peg <type BufferedImage>
pegBlack - graphic used to indicate a black peg given to a guessed code on the gameboard <type BufferedImage>
pegWhite - graphic used to indicate a white peg given to a guessed code on the gameboard <type BufferedImage>
confrimButton - button graphic used to confirm the player's current guess or feedback <type BufferedImage>
deleteButton - button graphic used to delete/reset the player's current pending guess or feedback <type BufferedImage>
emptyGameboard - background graphic used for all game states  <type BufferedImage>
aiGameboard - background graphic used for the computer codebreaker game state
playerGameboard - background graphic used for the player codebreaker game state
pvpGameboard - background graphic used for the player vs player codebreaker game state
codebreakerWins - graphic used to indicate that the codebreaker has won the game (code was guessed) <type BufferedImage>
codemakerWins - graphic used to indicate that the codemaker has won the game (code was not guessed) <type BufferedImage>
mainMenu - background graphic used for the "Main Menu" state, the menu that is initially called <type BufferedImage>
playMenu - background graphic used for the "Play Menu" state <type BufferedImage>
computerCodebreakerMenu - background graphic used for the "Computer Codebreaker Menu" state <type BufferedImage>
rulesAboutMenu - background graphic used for the "Rules/About Menu" state <type BufferedImage>
aboutUsPage - background graphic used for the "About Us Menu" state <type BufferedImage>
optionsMenu - background graphic used for the "Options Menu" state <type BufferedImage>
colours - array used to store graphics used to display the 6 possible code colours (blue, green, orange, purple, red, yellow) <type BufferedImage[]>
rulesPages - array used to store background graphics for the 3 pages/sections of the "Rules" state <type BufferedImage[]>
numberButtons - array used to store graphics used to display numbers from 1-10 <type BufferedImage[]>
============================================================================================
*/
package gfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {

    public static Font arial28, arial20;
    public static BufferedImage mainMenu;

    public static BufferedImage xTile, oTile, endMessageBar;
   
    /**Init method
     * This procedural method assigns each BufferedImage variable their appropriate png graphics
     * file (located in the project resource folder) for GUI usage
     */
    public static void init() {

        xTile = loadImage("/texture/x_tile.png");
        oTile = loadImage("/texture/o_tile.png");
        endMessageBar = loadImage("/texture/end_message.PNG");
        mainMenu = loadImage("/texture/grid.png");


        arial28 = loadFont("res/Arial.ttf", 28, Font.BOLD);
        arial20 = loadFont("res/Arial.ttf", 20, Font.PLAIN);
    }
    
    /**loadImage method
     * This functional method decodes the image file from the res folder by reading the
     * path of the image relative to the /res folder
     * 
     * @param path - the file path used to locate the png image file used <type String>
     * @return - the decoded image from the given path. null if the ImageIO is unable
     *      to decode the file </type BufferedImage>
     */
    private static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(Assets.class.getResource(path));
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**loadFont method
     * This functional method decodes the font file from the res folder by reading the
     * path of the ttf file relative to the /res folder
     *
     * @param path - the file path used to locate the ttf font file used <type String>
     * @param size - the size of the font </type int>
     * @param style - the style of the font </type int>
     * @return - the decoded font with given size and style configuration. null if the
     *      FontIO is unable to decode the file </type Font>
     */
    private static Font loadFont(String path, int size, int style){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(style, size);
        }catch (IOException | FontFormatException e){
            e.printStackTrace();
        }
        return null;
    }

}
