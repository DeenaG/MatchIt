package com.rubin.matchit;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.util.Log;

import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.KeyEvent;

public class PlayScreen extends Screen {
	
	enum GameState{
		Ready,
		Running,
		GameOver
	}
	
	GameState state = GameState.Ready;
	//game world
	private World world;
	
	//coordinates for visual elements
	private static final int BACK_X = 20;
	private static final int NAVIGATION_WIDTH = 200;
	private static final int NAVIGATION_HEIGHT = 200;
	private static final int NAVIGATION_Y = 1500;
	private static final int VISIBLE_Y_TOP = 420;
	private static final int VISIBLE_Y_BOTTOM = 1750;
	
	//dimensions of visual elements
	private final static int WIDTH = 300;
	private final static int HEIGHT = 300;
	
	//ready screen navigation
	private static final int READY_X = 140;
	private static final int READY_Y = 1200;
	private static final int READY_WIDTH = 800;
	private static final int READY_HEIGHT = 150;
	
	//game over navigation
	private static final int DIRECTIONS_X = 140;
	private static final int BACKGROUND_Y = 700;
	private static final int BACKGROUND_WIDTH = 800;
	private static final int BACKGROUND_HEIGHT = 400;
	private static final int DIRECTIONS_Y = 700;
	private static final int NAVIGATION_TEXT_SIZE = 150;
	private static final int NAVIGATION_TEXT_X = 540;
	private static final int NAVIGATION_TEXT_Y = 1275;
	
	private static final int ALPHA = 255;
	
	//coordiantes of touch events
	private int eventX;
	private int eventY;
	
	//is game in running mode
	private boolean updateRunning = false;
	
	//length of scroll array
	private int scrollLength;
	//length of pattern array
	private int patternLength;
	
	//index target shape in pattern array
	private int targetIndex=0;
	//target shape - shape looking for in event
	private Shape targetShape;
	
	//private Shape shape = null;
	
	//error message
	private String message = "";
	
	//true if match has been found
	private boolean match = false;
	
	//total time that the game has been played
	private float totalTime = 0;
	
	//draw error line to wrong shape
	//private boolean drawErrorLine = false;
	
	//color of text
	private int color;
	private static final int COLOR_BLUE = Color.rgb(72, 0, 255);
	private static final int COLOR_RED = Color.rgb(255, 0, 54);
	private static final int COLOR_GREEN = Color.rgb(10, 175, 68);
	private static final int COLOR_PURPLE = Color.rgb(152, 0, 215);
	
	//index of directions array
	private int directionsIndex = 0;
	
	Shape shape = null;
	boolean drawErrorLine = false;
	/**
	 * Constructor
	 * @param game
	 */
	public PlayScreen (Game game){
		super(game);
		world = new World();
		world.pattern.shapes[targetIndex].target = true; //first shape in pattern array is initial target
		scrollLength = world.scroll.shapes.length; //set length for scroll shapes
		patternLength = world.pattern.shapes.length; //set length for pattern shapes
		//set color based on current settings
		if (Settings.theme == Constants.BLUE)
			color = COLOR_BLUE;
		else if (Settings.theme == Constants.RED)
			color = COLOR_RED;
		else if (Settings.theme == Constants.GREEN)
			color = COLOR_GREEN;
		else if (Settings.theme == Constants.PURPLE)
			color = COLOR_PURPLE;
	}

	@Override
	public void update(float deltaTime) {
		//update the game time if the game is runnings
		if (updateRunning)
			totalTime += deltaTime;
		//touch events stored in list
		List <TouchEvent> touchEvents = game.getInput().getTouchEvents();
		//clear buffer of key events
		game.getInput().getKeyEvents();
		
		//update screen based on state of the game
		if(state == GameState.Ready)
			updateReady(touchEvents);
		else if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		else if (state == GameState.GameOver){
			updateGameOver(touchEvents);
			updateRunning = false;
		}
		
	}

	
	/**
	 * updates screen if the game is over
	 * @param touchEvents list of touch events
	 */
	private void updateGameOver(List<TouchEvent> touchEvents) {
		for (int i=0; i<touchEvents.size(); i++){
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				//play again if play button pressed
				if (super.inBounds(event, 140, 1150, 200, 200)){
					game.setScreen(new MenuScreen(game));
					super.playSound(Assets.tap);
				}
				//go back to main menu of back button pressed
				else if (super.inBounds(event, 740, 1150, 200, 200)){
					game.setScreen(new PlayScreen(game));
					super.playSound(Assets.tap);
				}
			}
		}
		
	}

	/**
	 * @param touchEvents list of touch events
	 * @param deltaTime time since the screen was last updated
	 */
	
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int previousY = 3000;
		int maxY = -3000;
		targetShape = world.pattern.shapes[targetIndex]; //shape to look for at events
		for (int i=0; i<touchEvents.size(); i++){
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				//scroll through array to find the target shape. 
				//if the target shape exists, previousY is updated to the target shape's y
				//this will be used to determine if the shape is out of order
				for (int j=0; j<scrollLength; j++){
					Shape newShape = world.scroll.shapes[j];
					if (newShape.target)
						previousY = newShape.y;
				}
				//if there are two shapes that are the same - determine the max y coordinate. 
				//only the shape at the max y coordinate is valid as an event
				for (int j=0; j<scrollLength; j++){
					Shape newShape = world.scroll.shapes[j];
					if (newShape.equals(targetShape) && newShape.y> VISIBLE_Y_TOP && newShape.y <VISIBLE_Y_BOTTOM 
							&& !newShape.target && newShape.y < previousY){
						if (newShape.y > maxY)
							maxY = newShape.y;
					}
				}
				for (int j=0; j<scrollLength; j++){
					shape = world.scroll.shapes[j];
					boolean equals = shape.equals(targetShape);
					boolean shapeInBounds = super.inBounds(event, shape.x, shape.y, WIDTH, HEIGHT);
					boolean visible = shape.y>VISIBLE_Y_TOP && shape.y < VISIBLE_Y_BOTTOM;
					
					//check for wrong shape being touched:
					//event is inbounds of a visible shape
					//shape is not equal to the target shape
					if (!equals && shapeInBounds && visible){
						shape.target = true;
						message = "wrong shape!";
						playSound(Assets.error);
						world.gameOver = true;
						eventX = event.x;
						eventY = event.y;
						drawErrorLine = true;
						
					}
					//check if the event is the right shape but out of order
					// event is on visible shape
					//the shape is in bounds
					//the event shape is equal to the target shape
					//the event's y coordinate is greater than the previously tapped target shape's y coordinate
					else if (equals && shapeInBounds && visible && event.y > previousY){
						shape.target = true;
						message = "wrong order!";
						playSound(Assets.error);
						world.gameOver = true;
						eventX = event.x;
						eventY = event.y;
						drawErrorLine = true;
					}
					
					//check if missed a double
					//if the shapes are equal and visible, but the event's y coordinate is less (higher on the screen) than another equal shape's max y coordinate
					else if (equals && shapeInBounds && visible && event.y < maxY){
						shape.target = true;
						message = "missed double!";
						playSound(Assets.error);
						world.gameOver = true;
						eventX = event.x;
						eventY = event.y;
						drawErrorLine = true;
					}
					//check if the event is the correct shape
					else if (equals && shapeInBounds && visible && !shape.target && event.y < previousY){
						previousY = 3000; //reset previousY 
						maxY = 3000; //reset max shape
						match = true;
						shape.target = true;
						playSound(Assets.tap);
						world.score += World.SCORE_INCREMENT;
						
						//update target index of pattern array

						world.pattern.shapes[targetIndex].target = false;
						if (targetIndex >=3)
							targetIndex = 0;
						else
							targetIndex++;
						world.pattern.shapes[targetIndex].target = true;
						return;
					}
				}
				return;
			}
		}
		
		//check to see if a shape was missed
		if (!world.gameOver){
			if((shape != null && shape.y>VISIBLE_Y_BOTTOM && match) || !match){
				match = false;
				for (int i=0; i < scrollLength ; i++){
					//if a shape is below visible line and hasn't been tapped, the shape was missed
					if (world.scroll.shapes[i].equals(targetShape) && world.scroll.shapes[i].y > VISIBLE_Y_BOTTOM && !world.scroll.shapes[i].target){
						message = "missed shape!";
						playSound(Assets.error);
						world.gameOver = true;
						return;
					}
				}
			}
		}
		//if game is running, update the world
		if (state == GameState.Running)
			world.update(deltaTime, totalTime);
		
		//if game is over, switch mode
		if (world.gameOver){
			state = GameState.GameOver;
		}
		
	}

	/**
	 *update the screen in ready mode
	 * @param touchEvents list of touch events 
	 */
	private void updateReady(List<TouchEvent> touchEvents) {
		//check for touch events
		for (int i=0; i<touchEvents.size(); i++){
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				//if the tutorial is on, check for continue button to advance through directions
				if (Settings.tutorialOn){
					if (super.inBounds(event, READY_X, READY_Y, READY_WIDTH, READY_HEIGHT)){
						if (directionsIndex < 4)
							directionsIndex++;
						else{
							updateRunning = true;
							state = GameState.Running;
						}
						super.playSound(Assets.tap);
					}
				}
				//if tutorial is not on, check for start button
				else{
					if (super.inBounds(event, READY_X, READY_Y, READY_WIDTH, READY_HEIGHT)){
						updateRunning = true;
						state = GameState.Running;
						super.playSound(Assets.tap);
					}
				}
				//back button tapped
				if (inBounds(event, BACK_X, NAVIGATION_Y, NAVIGATION_WIDTH, NAVIGATION_HEIGHT)){
					playSound(Assets.tap);
					game.setScreen(new MenuScreen (game));
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		drawRunningUI();
		if (state == GameState.Ready)
			drawReadyUI();
		else if(state == GameState.GameOver)
			drawGameOverUI();
			

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 700, 1080, 700, Color.BLACK, 150);
		g.drawText(Assets.font, Align.CENTER, Color.WHITE, "GAME OVER", 160, 540, 900);
		g.drawText(Assets.font, Align.CENTER, Color.WHITE, message, 120, 540, 1070);
		g.drawText(Typeface.SANS_SERIF, Align.CENTER, Color.WHITE, (int)totalTime + "s", 120, 540, 1200);
		g.drawPixmap(Assets.back, 140, 1150, 255);
		g.drawPixmap(Assets.play, 740, 1150, 255);
		g.drawRect(0, eventY, eventX, 10, Color.RED, 255);
	}

	private void drawRunningUI() {
		int scrollType, scrollColor, scrollX, scrollY, alpha;
		boolean target;
		Pixmap pix;
		Graphics g = game.getGraphics();
		for (int i=0; i<scrollLength; i++){
			scrollType = world.scroll.shapes[i].type;
			scrollColor = world.scroll.shapes[i].color;
			scrollX = world.scroll.shapes[i].x;
			scrollY = world.scroll.shapes[i].y;
			if (world.scroll.shapes[i].target)
				pix = PixmapSelection.chooseScrollPixmapDown(scrollType, scrollColor);
			else
				pix = PixmapSelection.chooseScrollPixmapUp(scrollType, scrollColor);
			if (pix != null)
				g.drawPixmap(pix, scrollX, scrollY, 255);
			
		}
		
		
		//pattern shapes
		g.drawRect(0, 0, 1080, 420, Color.WHITE, 255);
		g.drawRect(0, 420, 1080, 4, Color.BLACK, 255);

		int patternType, patternColor, patternX, patternY;
		
		for (int i=0; i<patternLength; i++){
			patternType = world.pattern.shapes[i].type;
			patternColor = world.pattern.shapes[i].color;
			patternX = world.pattern.shapes[i].x;
			patternY = world.pattern.shapes[i].y;
			target = world.pattern.shapes[i].target;
			pix = PixmapSelection.choosePatternPixmap(patternType, patternColor);
			if (target)
				alpha = 255;
			else
				alpha = 100;
			
			if (pix !=null)
				g.drawPixmap(pix, patternX, patternY, alpha);
			
		}
		
		
		g.drawText(Assets.font, Align.CENTER, color, Integer.toString(world.score), 120, 560, 100);
		
		g.drawRect(0, 1750, 1080, 170, Color.WHITE, 180);
		g.drawRect(0, 1750, 1080, 5, Color.BLACK, 255);
		
		
		
	}

	
	private void drawReadyUI() {
		Pixmap[] pixmaps = {Assets.directions1, Assets.directions2, Assets.directions3, Assets.directions4, Assets.directions5};
		Graphics g = game.getGraphics();
		//if tutorial is on, show the directions
		if (Settings.tutorialOn){
			g.drawRect(DIRECTIONS_X, BACKGROUND_Y, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, color, ALPHA);
			g.drawPixmap(pixmaps[directionsIndex],DIRECTIONS_X, DIRECTIONS_Y, ALPHA);
			if (directionsIndex < 4)
				g.drawText(Assets.font, Align.CENTER, color, "continue", NAVIGATION_TEXT_SIZE, NAVIGATION_TEXT_X, NAVIGATION_TEXT_Y);
			else
				g.drawText(Assets.font, Align.CENTER, color, "start", NAVIGATION_TEXT_SIZE, NAVIGATION_TEXT_X, NAVIGATION_TEXT_Y);
		}
		else
			g.drawText(Assets.font, Align.CENTER, color, "start", NAVIGATION_TEXT_SIZE, NAVIGATION_TEXT_X, NAVIGATION_TEXT_Y);
		g.drawPixmap(Assets.back, BACK_X, NAVIGATION_Y, ALPHA);
	}

	@Override
	//update settings when game is paused
	public void pause() {
		Settings.totalGames++;
		if (world.score > Settings.totalScore)
			Settings.totalScore = world.score;
		if (Settings.mode.equals(Constants.STRAIGHT_MODE)){
			Settings.straightTotalGames++;
			if (world.score > Settings.straightHighScore)
				Settings.straightHighScore = world.score;
		}
		else if (Settings.mode.equals(Constants.ZIGZAG_MODE)){
			Log.d("zigzag", "zigzag");
			Settings.zigzagTotalGames++;
			if (world.score > Settings.zigzagHighScore)
				Settings.zigzagHighScore = world.score;
		}
		else if (Settings.mode.equals(Constants.RANDOM_MODE)){
			Log.d("random", "random");
			Settings.randomTotalGames++;
			if (world.score > Settings.randomHighScore)
				Settings.randomHighScore = world.score;
		}
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
		Settings.currentPage = Constants.PLAY;
		Settings.load(game.getFileIO());
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
