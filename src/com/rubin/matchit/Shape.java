package com.rubin.matchit;

public class Shape {
	
	public int x, y, type, color;
	public boolean target = false;
	
	
	/**
	 * Constructor
	 * @param x coordinate of shape
	 * @param y coordiante of shape
	 * @param type of shape
	 * @param color or shape
	 */
	public Shape (int x, int y, int type, int color){
		this.x = x;
		this.y = y;
		this.type = type;
		this.color = color;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * Shapes are equal if they have the same type and color
	 */
	@Override
	public boolean equals (Object otherObject){
		boolean equals = false;
		if(otherObject != null && otherObject instanceof Shape){
			Shape otherShape = (Shape)otherObject;
			if (this.type == otherShape.type && this.color == otherShape.color)
				equals = true;
		}
		return equals;
	}
	
	@Override
	public String toString(){
		return "type: " + this.type + " color: " + this.color + " x: " + this.x + " y: " + this.y;
	}
	
	
	
}
