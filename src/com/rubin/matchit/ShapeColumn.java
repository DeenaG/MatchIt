package com.rubin.matchit;

public abstract class ShapeColumn {
	
	public Shape [] shapes;
	
	protected static final int TYPE_RANGE = 4; //number of shape types
	protected static final int COLOR_RANGE = 4; //number of shape colors
	
	/**
	 * @param size of Shape array
	 */
	public ShapeColumn(int size){
		shapes = new Shape [size];
	}
	
	/**
	 * fill shape array with shapes of random type and color
	 */
	public abstract void fill();
}
