package com.rubin.matchit;

import java.util.Random;

public class ShapeScroll extends ShapeColumn{
	private Random rand = new Random();
	
	private static final int Y_INCREMENT = 1;
	private static final int END_Y = 1920;
	private static final int INITIAL_X = 390;
	private static final int INITIAL_Y = 0;
	private boolean goingRight = true;
	private int count =0;

	/**
	 * @param size of shape array to create
	 */
	public ShapeScroll (int size){
		super (size);
		fill();
	}
	
	/* (non-Javadoc)
	 * @see com.rubin.matchit.ShapeColumn#fill()
	 * The x value of the shapes will be determined by the mode
	 * for straight: center of screen
	 * for zigzag: incremented by 100 right or left
	 * for random: randomly selected x
	 */
	@Override
	public void fill(){
		int x = -1;
		int y = INITIAL_Y;
		if (Settings.mode.equals(Constants.STRAIGHT_MODE))
			x = INITIAL_X; //the shapes will all be in the middle of the screen
		else if (Settings.mode.equals(Constants.RANDOM_MODE))
			x = rand.nextInt(780);
		else if (Settings.mode.equals(Constants.ZIGZAG_MODE))
			x = 200;
		
		int type;
		int color = Settings.theme;
		for (int i=0; i<shapes.length; i++){
			type = rand.nextInt(ShapeColumn.TYPE_RANGE);
			
			this.shapes[i] = new Shape(x, y, type, color);
			y -= 350; //the shapes will be separated by 50 pixels
			if (Settings.mode.equals(Constants.RANDOM_MODE))
				x = rand.nextInt(780);
			else if (Settings.mode.equals(Constants.ZIGZAG_MODE)){
				x+=100;
			}
		}
	}
	
	/**
	 * The shapes will scroll down on the screen
	 * When y coordinate is greater than the length of the screen, it will be replaced 
	 * with a new shape and the y coordinate will move to the top of the screen
	 */
	public void advance(){
		int previousIndex;
		for (int i=0; i<shapes.length; i++){
			if (shapes[i].y <= END_Y){
				shapes[i].y += Y_INCREMENT;
			}
			else{
				if (i == 0)
					previousIndex = shapes.length-1;
				else
					previousIndex = i-1;
				replace (shapes[i].x, shapes[i].y, i);
				shapes[i].y = -220;
				if (Settings.mode.equals(Constants.RANDOM_MODE))
					shapes[i].x = rand.nextInt(780);
				else if (Settings.mode.equals(Constants.ZIGZAG_MODE)){
					if (shapes[previousIndex].x > 650)
						goingRight = false;
					else if (shapes[previousIndex].x < 200)
						goingRight = true;
					if (goingRight)
						shapes[i].x = shapes[previousIndex].x + 100;
					else
						shapes[i].x =shapes[previousIndex].x - 100;
				}
			}
		}
	}
	
	private void replace(int x, int y, int index){
		int type = rand.nextInt(ShapeColumn.TYPE_RANGE);
		int color = Settings.theme;
		shapes[index] = new Shape(x, y, type, color);
	}
	

	
}
