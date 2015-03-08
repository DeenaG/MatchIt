package com.rubin.matchit;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint.Align;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class CreditsScreen extends Screen {
	
	//height and width of visual elements to check for events
	
	private static final int LABEL_WIDTH = 600;
	private static final int LABEL_HEIGHT = 150;
	
	private static final int LABEL_X = 240;
	private static final int TEXT_X = 540;
	private static final int GAME_DEV_Y = 330;
	private static final int SOUNDS_Y = 620;
	private static final int FONTS_Y = 1030;
	private static final int RUBIN_Y = 600;
	private static final int MISHICU_Y = 850;
	private static final int SHNUR_Y = 950;
	private static final int FREESOUND_Y = 1020;
	private static final int BONVENOCF_Y = 1250;
	private static final int SQUIRRELFONT_Y = 1310;
	
	private static final String DEVELOPER_TEXT = "deena rubin";
	private static final String MISHICU_TEXT = "mishicu";
	private static final String SHNUR_TEXT = "shnur_";
	private static final String FREESOUND_TEXT = "www.freesound.org";
	private static final String BONVENOCF_TEXT = "BonvenoCF";
	private static final String FONTSQUIRREL_TEXT = "www.fontsquirrel.com";
	
	private static final int DEVELOPER_SIZE = 130;
	private static final int CREDIT_SIZE = 100;
	private static final int WEBSITE_SIZE = 50;
	
	private static final int COLOR = Color.rgb(72, 0, 255);
	
	private static final int BACK_X = 20;
	private static final int PLAY_X = 860;
	private static final int NAVIGATION_Y = 1700;
	
	private static final int ALPHA = 255;
	
	public CreditsScreen (Game game){
		super (game);
	}

	@Override
	public void update(float deltaTime) {
		//clear keyboard events
		game.getInput().getKeyEvents();
		//list of touch events
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		//loop through touch events
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				//back button tapped
				if (inBounds(event, BACK_X, NAVIGATION_Y, LABEL_WIDTH, LABEL_HEIGHT)){
					playSound(Assets.tap);
					game.setScreen(new MenuScreen(game));
				}
				//play button tapped
				else if (inBounds(event, PLAY_X, NAVIGATION_Y, LABEL_WIDTH, LABEL_HEIGHT)){
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
		
		g.drawPixmap(Assets.developerCredit, LABEL_X, GAME_DEV_Y, ALPHA);
		g.drawText(Assets.font, Align.CENTER, COLOR, DEVELOPER_TEXT, DEVELOPER_SIZE, TEXT_X, RUBIN_Y);
		g.drawPixmap(Assets.soundCredit, LABEL_X, SOUNDS_Y, ALPHA);
		g.drawText(Assets.font, Align.CENTER, COLOR, MISHICU_TEXT, CREDIT_SIZE, TEXT_X, MISHICU_Y);
		g.drawText(Assets.font, Align.CENTER, COLOR, SHNUR_TEXT, CREDIT_SIZE, TEXT_X, SHNUR_Y);
		g.drawText(Assets.font, Align.CENTER, COLOR, FREESOUND_TEXT, WEBSITE_SIZE, TEXT_X, FREESOUND_Y);
		g.drawPixmap(Assets.fontCredit, LABEL_X, FONTS_Y, ALPHA);
		g.drawText(Assets.font, Align.CENTER, COLOR, BONVENOCF_TEXT, CREDIT_SIZE, TEXT_X, BONVENOCF_Y);
		g.drawText(Assets.font, Align.CENTER, COLOR, FONTSQUIRREL_TEXT, WEBSITE_SIZE, TEXT_X, SQUIRRELFONT_Y);
		
		//back button 
		g.drawPixmap(Assets.back, BACK_X, NAVIGATION_Y, ALPHA);
		//play button
		g.drawPixmap(Assets.play, PLAY_X, NAVIGATION_Y, ALPHA);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
