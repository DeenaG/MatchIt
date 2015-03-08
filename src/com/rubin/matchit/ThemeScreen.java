package com.rubin.matchit;

import java.util.List;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class ThemeScreen extends Screen {
	//x coordinate of all theme shapes
	private static final int SHAPE_X = 390;
	
	//y coordinates for theme shapes
	private static final int RED_Y = 300;
	private static final int BLUE_Y = 600;
	private static final int GREEN_Y = 900;
	private static final int PURPLE_Y = 1200;
	
	//alpha value for selected theme
	private static final int ALPHA_ON = 255;
	
	//alpha value for deselected theme
	private static final int ALPHA_0FF = 100;
	
	//x and y coordinates for navigation buttons
	private static final int BACK_X = 20;
	private static final int PLAY_X = 860;
	private static final int NAVIGATION_Y = 1700;
	
	//width and height for all elements
	private static final int SHAPE_WIDTH = 300;
	private static final int SHAPE_HEIGHT = 300;
	private static final int NAVIGATION_WIDTH = 200;
	private static final int NAVIGATION_HEIGHT = 200;
		
	public ThemeScreen(Game game){
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
				//change theme in settings based on event
				if (inBounds(event, SHAPE_X, BLUE_Y, SHAPE_WIDTH, SHAPE_HEIGHT)){ //blue theme
					playSound(Assets.tap);
					Settings.theme = Constants.BLUE;
				}
				else if (inBounds(event, SHAPE_X, GREEN_Y, SHAPE_WIDTH, SHAPE_HEIGHT)){ //green theme
					playSound(Assets.tap);
					Settings.theme = Constants.GREEN;
				}
				else if (inBounds(event, SHAPE_X, PURPLE_Y, SHAPE_WIDTH, SHAPE_HEIGHT)){ //purple theme
					playSound(Assets.tap);
					Settings.theme = Constants.PURPLE;
				}
				else if (inBounds(event, SHAPE_X, RED_Y, SHAPE_WIDTH, SHAPE_HEIGHT)){ //red theme
					playSound(Assets.tap);
					Settings.theme = Constants.RED;
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
		
		//alpha value depends on theme in constants 
		if (Settings.theme == Constants.BLUE)
			g.drawPixmap(Assets.scrollDiamondBlueDown, SHAPE_X, BLUE_Y, ALPHA_ON);
		else
			g.drawPixmap(Assets.scrollDiamondBlueDown, SHAPE_X, BLUE_Y, ALPHA_0FF);
		if (Settings.theme == Constants.GREEN)
			g.drawPixmap(Assets.scrollDiamondGreenDown, SHAPE_X, GREEN_Y, ALPHA_ON);
		else
			g.drawPixmap(Assets.scrollDiamondGreenDown, SHAPE_X, GREEN_Y, ALPHA_0FF);
		if (Settings.theme == Constants.PURPLE)
			g.drawPixmap(Assets.scrollDiamondPurpleDown, SHAPE_X, PURPLE_Y, ALPHA_ON);
		else
			g.drawPixmap(Assets.scrollDiamondPurpleDown, SHAPE_X, PURPLE_Y, ALPHA_0FF);
		if (Settings.theme == Constants.RED)
			g.drawPixmap(Assets.scrollDiamondRedDown, SHAPE_X, RED_Y, ALPHA_ON);
		else
			g.drawPixmap(Assets.scrollDiamondRedDown, SHAPE_X, RED_Y, ALPHA_0FF);
		
		//back button 
		g.drawPixmap(Assets.back, BACK_X, NAVIGATION_Y, ALPHA_ON);
		//play button
		g.drawPixmap(Assets.play, PLAY_X, NAVIGATION_Y, ALPHA_ON);
	}


	@Override
	public void pause() {
		Settings.save(game.getFileIO());

	}

	@Override
	public void resume() {
		Settings.currentPage = Constants.THEME;
		Settings.load(game.getFileIO());

	}

	@Override
	public void dispose() {
		Settings.save(game.getFileIO());

	}

}
