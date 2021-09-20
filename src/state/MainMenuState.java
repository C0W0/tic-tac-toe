/*==========================================================================================
Codebreaker
Terry Zha and Jonathan Xie
November 2, 2020
Java 13.0.2
The Main Menu class of the Code Breaker
This is the state class that displays the "Main Menu" on the GUI,
including all graphics & buttons associated with managing user input. 
This state is automatically called/displayed upon launching the program, and allows
the user to navigate to the play menu, rules/about menu, or terminate the program
============================================================================================
*/
package state;

import java.awt.*;

import gamelogic.Game;
import gfx.Assets;
import ui.UIButton;
import utils.Utils;

public class MainMenuState extends State{

	private int currentTile;
	private int winner;
	private int[][] currentBoard;
	private UIButton restartButton;
	private boolean buffer;
	
	/**MainMenuState method
     * Constructor method of the MainMenuState class
     * 
     * This method creates & places the 3 buttons available to the user in the main menu: 'Play',
     * 'Rules/About', and 'Exit'. Each of these buttons will transfer their player to their corresponding
     * state, or terminate the program
     * 
     * @param game - the passed-in object of the custom-made Game class </type Game>
     */
	public MainMenuState(Game game){
		
		super(game);

		buffer = false;
		winner = 0;
		currentTile = -1;
		currentBoard = new int[3][3];
		restartButton = new UIButton(0, 0, 768, 1024, null, this::restart);
		uiManager.addUIButton(restartButton);
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				int finalI = i;
				int finalJ = j;
				uiManager.addUIButton(new UIButton(260+262*j, 35+262*i, 235, 235, null, ()->
						changeTile(finalI, finalJ)));
			}
		}
//		uiManager.addUIButton(new UIButton(192, 215, 225, 640, Assets.playButton,()->game.setState(State.states[1])));
//		uiManager.addUIButton(new UIButton(192, 455, 165, 450, Assets.rulesAboutButton,()->game.setState(State.states[9])));
//		uiManager.addUIButton(new UIButton(662, 455, 165, 170, Assets.exitButton,()->System.exit(0)));
	}

	
	/**init method
     * This procedural method is inherited from the parent class, the State class
     * It is usually called by the use of a button, but not required for this state
     */
	@Override
	public void init() {
		
	}

	/**render method
     * This procedural method is inherited from the parent class, the State class
     * It constantly updates & renders graphics & buttons on the screen 45 times a second
     * 
     * @param graphics - the passed-in graphics context for drawing buffers on the screen </type Graphics>
     */
	@Override
	public void render(Graphics graphics) {
		
		graphics.drawImage(Assets.mainMenu, 256, 0, 768, 768, null);
		if(currentTile == 1)
			graphics.drawImage(Assets.xTile, 48, 300, 150, 150, null);
		else
			graphics.drawImage(Assets.oTile, 48, 300, 150, 150, null);

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(currentBoard[i][j] == 1)
					graphics.drawImage(Assets.xTile, 295+262*j, 35+262*i, 165, 165, null);
				else if(currentBoard[i][j] == -1)
					graphics.drawImage(Assets.oTile, 295+262*j, 35+262*i, 165, 165, null);
			}
		}
		Utils.drawText(graphics, "Player with the tile ", 124, 256, Color.BLACK, Assets.arial20);
		Utils.drawText(graphics, "Please make your move ", 124, 500, Color.BLACK, Assets.arial20);
		uiManager.render(graphics);

		if(winner != 0){
			graphics.drawImage(Assets.endMessageBar, 512-324, 768/2-128, 648, 256, null);
			if(winner == 2)
				Utils.drawText(graphics, "Tie!", 512, 364, Color.BLACK, Assets.arial20);
			else
				Utils.drawText(graphics, "Congrats to player with the "+(winner==1?"'X'":"'O'")+ " tile for winning the game!", 512, 364, Color.BLACK, Assets.arial20);
			Utils.drawText(graphics, "Click anywhere to restart", 512, 404, Color.BLACK, Assets.arial20);
		}
	}

	private void changeTile(int y, int x){
		if(currentBoard[y][x] != 0 || winner != 0)
			return;
		if(buffer) {
			buffer = false;
			return;
		}
		currentBoard[y][x] = currentTile;
		currentTile = currentTile==-1?1:-1;
		winner = checkVictory();
		if(isTie())
			winner = 2;
	}

	private boolean isTie(){
		int tileCount = 0;
		for(int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if(currentBoard[y][x] != 0)
					tileCount ++;
			}
		}
		return tileCount == 9;
	}

	private int checkVictory(){
		int xCount = 0;
		int oCount = 0;

		//check row
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				if(currentBoard[y][x] == 1)
					xCount ++;
				else if(currentBoard[y][x] == -1)
					oCount ++;
			}
			if(xCount == 3)
				return 1;
			if(oCount == 3)
				return -1;
			xCount = 0;
			oCount = 0;
		}

		//check column
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				if(currentBoard[y][x] == 1)
					xCount ++;
				else if(currentBoard[y][x] == -1)
					oCount ++;
			}
			if(xCount == 3)
				return 1;
			if(oCount == 3)
				return -1;
			xCount = 0;
			oCount = 0;
		}

		//diagonals (top left to bottom right)
		for(int i = 0; i < 3; i++) {
			if(currentBoard[i][i] == 1)
				xCount ++;
			else if(currentBoard[i][i] == -1)
				oCount ++;
		}
		if(xCount == 3)
			return 1;
		if(oCount == 3)
			return -1;
		xCount = 0;
		oCount = 0;

		//diagonals (bottom left to top right)
		for(int i = 0; i < 3; i++) {
			if(currentBoard[2-i][i] == 1)
				xCount ++;
			else if(currentBoard[2-i][i] == -1)
				oCount ++;
		}
		if(xCount == 3)
			return 1;
		if(oCount == 3)
			return -1;

		//if we checked everything and there were no 3-in a row's it's a tie
		return 0;
	}

	private void restart(){
		if(winner == 0)
			return;
		buffer = true;
		winner = 0;
		currentTile = -1;
		currentBoard = new int[3][3];
	}
}
