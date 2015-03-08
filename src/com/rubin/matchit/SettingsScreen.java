package com.rubin.matchit;

import java.util.List;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class SettingsScreen extends Screen {
	
	//X and Y coordinates for visual elements
	private static final int SOUND_X = 240;
	private static final int SOUND_Y = 530;
	private static final int TUTORIAL_X = 240;
	private static final int TUTORIAL_Y = 930;
	private static final int SOUND_ICON_X = 440;
	private static final int SOUND_ICON_Y = 680;
	private static final int TUTORIAL_ICON_X = 440;
	private static final int TUTORIAL_ICON_Y = 1080;
	private static final int BACK_X = 20;
	private static final int BACK_Y = 1700;
	private static final int PLAY_X = 860;
	private static final int PLAY_Y = 1700;
	
	//width and height of the icons
	private static final int WIDTH = 200;
	private static final int HEIGHT = 200;
	
	private static final int ALPHA = 255;

	
	
	/**
	 * Constructor
	 * @param game
	 */
	public SettingsScreen(Game game){
		super (game);
	}

	
	@Override
	public void update(float deltaTime) {
		//clear keyboard events
		game.getInput().getKeyEvents();
		
		// list of touch events since the last update
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		// loop through the list of TouchEvents and check to see to check for events
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				//check if the sound was toggled
				if (inBounds(event, SOUND_ICON_X, SOUND_ICON_Y, WIDTH, HEIGHT)){
					Settings.soundEnabled = !Settings.soundEnabled;
					playSound(Assets.tap);
				}
				//check if tutorial was toggled
				else if (inBounds(event, TUTORIAL_ICON_X, TUTORIAL_ICON_Y, WIDTH, HEIGHT)){
					Settings.tutorialOn = !Settings.tutorialOn;
					playSound(Assets.tap);
				}
				//check if play button was pressed
				else if (inBounds(event, BACK_X, BACK_Y, WIDTH, HEIGHT)){
					playSound(Assets.tap);
					game.setScreen(new MenuScreen (game));
				}
				//check if back button was pressed
				else if (inBounds (event, PLAY_X, PLAY_Y, WIDTH, HEIGHT)){
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
		//sound label
		g.drawPixmap(Assets.sound, SOUND_X, SOUND_Y, ALPHA);
		//display on or off based on settings
		if (Settings.soundEnabled)
			g.drawPixmap(Assets.on, SOUND_ICON_X, SOUND_ICON_Y, ALPHA);
		else
			g.drawPixmap(Assets.off, SOUND_ICON_X, SOUND_ICON_Y, ALPHA);
		
		//tutorial label
		g.drawPixmap(Assets.tutorial, TUTORIAL_X, TUTORIAL_Y, ALPHA);
		//on or off based on settings
		if (Settings.tutorialOn)
			g.drawPixmap(Assets.on, TUTORIAL_ICON_X, TUTORIAL_ICON_Y, ALPHA);
		else
			g.drawPixmap(Assets.off, TUTORIAL_ICON_X, TUTORIAL_ICON_Y, ALPHA);
		
		//back button 
		g.drawPixmap(Assets.back, BACK_X, BACK_Y, ALPHA);
		//play button
		g.drawPixmap(Assets.play, PLAY_X, PLAY_Y, ALPHA);
	}

	
	@Override
	public void pause() {
		Settings.save(game.getFileIO());
	}

	
	@Override
	public void resume() {
		Settings.currentPage = Constants.SETTINGS;
		Settings.load(game.getFileIO());
	}

	@Override
	public void dispose() {
		Settings.save(game.getFileIO());
	}
}
