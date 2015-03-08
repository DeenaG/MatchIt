package com.rubin.matchit;

import java.util.Random;

public class ShapePattern extends ShapeColumn{
	
	private static final int SHAPE_INITIAL_X = 65;
	private static final int SHAPE_Y = 150;

	/**
	 * @param size of Shape array
	 */
	public ShapePattern (int size){
		super(size);
		fill();
	}

	@Override
	public void fill() {
		Random rand = new Random();
		int type;
		int color = Settings.theme;
		int x = SHAPE_INITIAL_X;
		for (int i=0; i<shapes.length; i++){
			type = rand.nextInt(ShapeColumn.TYPE_RANGE);
			this.shapes[i] = new Shape(x, SHAPE_Y, type, color);
			x += 250;
		}
	}

}
