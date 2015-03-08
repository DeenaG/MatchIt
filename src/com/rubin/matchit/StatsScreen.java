package com.rubin.matchit;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.Log;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class StatsScreen extends Screen {
	//alpha value for bitmaps
	private static final int ALPHA = 255;
	
	//x coordinate for all labels
	private static final int LABELS_X = 40;
	//y coordinates for labels
	private static final int HIGH_SCORES_Y = 100;
	private static final int GAMES_PLAYED_Y = 800;
	private static final int NAVIGATION_Y = 1700;
	
	//x coordinates for navigation elements
	private static final int BACK_X = 20;
	private static final int PLAY_X = 860;
	
	//x coordinate for text elements
	private static final int TEXT_X = 540;
	//y coordinates for text elements
	private static final int STRAIGHT_SCORE_Y = 400;
	private static final int ZIGZAG_SCORE_Y = 550;
	private static final int RANDOM_SCORE_Y = 700;
	private static final int TOTAL_GAMES_Y = 1100;
	private static final int STRAIGHT_GAMES_Y = 1250;
	private static final int ZIGZAG_GAMES_Y = 1400;
	private static final int RANDOM_GAMES_Y = 1550;
	
	
	//height and width of visual elements to check for events
	private static final int WIDTH = 200;
	private static final int HEIGHT = 250;

	//color values for text
	private static final int RED = 72;
	private static final int GREEN = 0;
	private static final int BLUE = 255;
	
	//size of text
	private static final int FONT_SIZE = 90;
	
	//stats to display
	private static  String straightScore; 
	private static  String zigzagScore; 
	private static  String randomScore; 
	private static String straightGames;
	private static  String zigzagGames;
	private static String randomGames; 
	private static  String totalGames;
	
	/**
	 * Constructor
	 * @param game
	 */
	public StatsScreen (Game game){
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
				if (inBounds(event, BACK_X, NAVIGATION_Y, WIDTH, HEIGHT)){
					playSound(Assets.tap);
					game.setScreen(new MenuScreen(game));
				}
				//play button tapped
				else if (inBounds(event, PLAY_X, NAVIGATION_Y, WIDTH, HEIGHT)){
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
		
		//high scores
		g.drawPixmap(Assets.highScores, LABELS_X, HIGH_SCORES_Y, ALPHA);
		g.drawText(Assets.font, Align.CENTER, Color.rgb(RED, GREEN, BLUE), straightScore, FONT_SIZE, TEXT_X, STRAIGHT_SCORE_Y);
		g.drawText(Assets.font, Align.CENTER, Color.rgb(RED, GREEN, BLUE), zigzagScore, FONT_SIZE, TEXT_X, ZIGZAG_SCORE_Y);
		g.drawText(Assets.font, Align.CENTER, Color.rgb(RED, GREEN, BLUE), randomScore, FONT_SIZE, TEXT_X, RANDOM_SCORE_Y);
		
		//total games
		g.drawPixmap(Assets.gamesPlayed, LABELS_X, GAMES_PLAYED_Y, ALPHA);
		g.drawText(Assets.font, Align.CENTER, Color.rgb(RED, GREEN, BLUE), totalGames, FONT_SIZE, TEXT_X, TOTAL_GAMES_Y);
		g.drawText(Assets.font, Align.CENTER, Color.rgb(RED, GREEN, BLUE), straightGames, FONT_SIZE, TEXT_X, STRAIGHT_GAMES_Y);
		g.drawText(Assets.font, Align.CENTER, Color.rgb(RED, GREEN, BLUE), zigzagGames, FONT_SIZE, TEXT_X, ZIGZAG_GAMES_Y);
		g.drawText(Assets.font, Align.CENTER, Color.rgb(RED, GREEN, BLUE), randomGames, FONT_SIZE, TEXT_X, RANDOM_GAMES_Y);
		
		//back button 
		g.drawPixmap(Assets.back, BACK_X, NAVIGATION_Y, ALPHA);
		//play button
		g.drawPixmap(Assets.play, PLAY_X, NAVIGATION_Y, ALPHA);

	}

	@Override
	public void pause() {
		

	}


	@Override
	public void resume() {
		Settings.load(game.getFileIO());
		
		//settings read from settings file when game resumed
		straightScore = "straight:" + Settings.straightHighScore;
		zigzagScore = "zigzag:" + Settings.zigzagHighScore;
		randomScore = "random:" + Settings.randomHighScore;
		straightGames = "straight:" + Settings.straightTotalGames;
		zigzagGames = "zigzag:" + Settings.zigzagTotalGames;
		randomGames = "random:" + Settings.randomTotalGames;
		totalGames = "total:" + Settings.totalGames;

	}

		@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
