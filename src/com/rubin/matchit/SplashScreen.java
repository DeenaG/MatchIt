package com.rubin.matchit;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class SplashScreen extends Screen {
	Context c = game.getContext();
			
	//how much time has passed since the screen was loaded
	private float time = 0;
	//The alpha value of the splash screen without text
	private int currentAlphaValue = 255;
	//increment the alpha value of the splash screen without text
	private int alphaIncrement = 5;
			
	//constants to keep track of the coordinates of the splash screen
	private static final int TEXT_X = 90;
	private static final int TEXT_Y = 885;
	
	
	/**
	 * Constructor
	 * @param game
	 */
	public SplashScreen(Game game){
		super(game);

	}

	/* (non-Javadoc)
	 * @see com.badlogic.androidgames.framework.Screen#update(float)
	 */
	@Override
	public void update(float deltaTime) {
		//clear keyboard events
		game.getInput().getKeyEvents();
		
		//get touch events since the last time the update method was called and store in List
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		//increment time with the time that has passed since the last time the update() method was called
		time+=deltaTime;
		
		//after three seconds, load the menu screen
		if (time >= 3.5){
			//game.setScreen(new MenuScreen(game));
			game.setScreen(new MenuScreen(game));
		}
		
		//if the user taps the screen, load the menu screen
		for (int i=0; i<touchEvents.size(); i++){
			TouchEvent event = touchEvents.get(i);
			if((event.type)==TouchEvent.TOUCH_UP)
				//game.setScreen(new MenuScreen(game));
				game.setScreen(new MenuScreen(game));
		}
	}


	/* (non-Javadoc)
	 * @see com.badlogic.androidgames.framework.Screen#present(float)
	 */
	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		
		if (time < 1.5)
			g.drawPixmap(Assets.splashScreenText, TEXT_X, TEXT_Y, currentAlphaValue);
		else if (time >= 1.5 && time < 2.0)
			g.drawPixmap(Assets.splashScreenIcon, TEXT_X, TEXT_Y, currentAlphaValue);
		else if (time >= 2.0 && time < 3.5){
			g.drawPixmap(Assets.splashScreenIcon, TEXT_X, TEXT_Y, currentAlphaValue);
			if(currentAlphaValue >= alphaIncrement)
				currentAlphaValue -= alphaIncrement;
			else if(currentAlphaValue >= 0)
				currentAlphaValue = 0;
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		Settings.currentPage = Constants.SPLASH_SCREEN;

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
