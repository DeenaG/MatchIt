package com.rubin.matchit;

import java.util.Random;

import android.util.Log;

public class World {
	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = .002f;
	static final float TICK_DECREMENT = .0005f;
	static final int PATTERN_SIZE = 4;
	static final int SCROLL_SIZE = 6;
	
	public ShapePattern pattern;
	public ShapeScroll scroll;
	public boolean gameOver = false;
	public int score = 0;
	
	Random rand = new Random();
	float tickTime = 0;
	float tick = TICK_INITIAL;
	
	/**
	 * Constructor
	 * create pattern and scroll arrays
	 */
	public World (){
		pattern = new ShapePattern (PATTERN_SIZE);
		scroll = new ShapeScroll (SCROLL_SIZE);
	}
	
	/**
	 * @param deltaTime time since screen has last updated
	 * advances the screen 
	 */
	public void update(float deltaTime, float totalTime){
		if(gameOver)
			return;
		tickTime += deltaTime;
		while (tickTime > tick){
			tickTime -= tick;
			scroll.advance();
		}
		if (totalTime < 10)
			tick -= .0000002;
		else if (totalTime < 20)
			tick -= .0000003;
		else if (totalTime <25)
			tick -= .0000004;
		else if (totalTime < 30)
			tick -=.0000005;
		else if (totalTime < 60)
			tick -= .0000006;
			
	
	}
}
