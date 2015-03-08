package com.rubin.matchit;

import java.util.List;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class ModeScreen extends Screen {
	
	//X and Y coordinates for visual elements
	private static final int LABEL_X = 240;
	private static final int STRAIGHT_Y = 635;
	private static final int ZIGZAG_Y = 885;
	private static final int RANDOM_Y = 1135;
	private static final int BACK_X = 20;
	private static final int PLAY_X = 860;
	private static final int NAVIGATION_Y = 1700;
	
	//height and width of visual elements to check for events
	private static final int LABEL_WIDTH = 600;
	private static final int LABEL_HEIGHT = 150;
	private static final int NAVIGATION_WIDTH = 200;
	private static final int NAVIGATION_HEIGHT = 200;
	
	//alpha value for bitmaps
	private static final int ALPHA = 255;
	
	/**
	 * Constructor
	 * @param game
	 */
	public ModeScreen(Game game){
		super (game);
	}

	@Override
	public void update(float deltaTime) {
		//clear keyboard events
		game.getInput().getKeyEvents();
				
		//loop through touch events
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				//straight mode tapped
				if (inBounds(event, LABEL_X, STRAIGHT_Y, LABEL_WIDTH, LABEL_HEIGHT)){
					Settings.mode = Constants.STRAIGHT_MODE;
					playSound(Assets.tap);
				}
				//zigzag mode tapped
				else if (inBounds(event, LABEL_X, ZIGZAG_Y, LABEL_WIDTH, LABEL_HEIGHT)){
					Settings.mode = Constants.ZIGZAG_MODE;
					playSound(Assets.tap);
				}
				//random mode tapped
				else if (inBounds(event, LABEL_X, RANDOM_Y, LABEL_WIDTH, LABEL_HEIGHT)){
					Settings.mode = Constants.RANDOM_MODE;
					playSound(Assets.tap);
				}
				//back button tapped
				else if (inBounds(event, BACK_X, NAVIGATION_Y, NAVIGATION_WIDTH, NAVIGATION_HEIGHT)){
					playSound(Assets.tap);
					game.setScreen(new MenuScreen (game));
				}
				//play button tapped
				else if (inBounds (event, PLAY_X, NAVIGATION_Y, NAVIGATION_WIDTH, NAVIGATION_HEIGHT)){
					playSound(Assets.tap);
					game.setScreen(new PlayScreen(game));
				}
			}
		}
	}


	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		
		//draw on or off based on settings
		if (Settings.mode.equals(Constants.STRAIGHT_MODE))
			g.drawPixmap(Assets.straightOn, LABEL_X, STRAIGHT_Y, ALPHA);
		else
			g.drawPixmap(Assets.straightOff, LABEL_X, STRAIGHT_Y, ALPHA);
		if (Settings.mode.equals(Constants.ZIGZAG_MODE))
			g.drawPixmap(Assets.zigzagOn, LABEL_X, ZIGZAG_Y, ALPHA);
		else
			g.drawPixmap(Assets.zigzagOff, LABEL_X, ZIGZAG_Y, ALPHA);
		if (Settings.mode.equals(Constants.RANDOM_MODE))
			g.drawPixmap(Assets.randomOn, LABEL_X, RANDOM_Y, ALPHA);
		else
			g.drawPixmap(Assets.randomOff, LABEL_X, RANDOM_Y, ALPHA);
		
		//back button 
		g.drawPixmap(Assets.back, BACK_X, NAVIGATION_Y, ALPHA);
		//play button
		g.drawPixmap(Assets.play, PLAY_X, NAVIGATION_Y, ALPHA);

	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());

	}

	@Override
	public void resume() {
		Settings.currentPage = Constants.MODE;
		Settings.load(game.getFileIO());

	}

	@Override
	public void dispose() {
		Settings.save(game.getFileIO());

	}
	
}
