package com.badlogic.androidgames.framework;

import java.util.List;

//this interface gives 
//1. polling access to the touchscreen, keyboard and accelerometer
//2. event based access to the touchscreen and keyboard
public interface Input {

	// defines constants that encode a keyEvent's type
	public static class KeyEvent {
		public static final int KEY_DOWN = 0;
		public static final int KEY_UP = 1;

		// type of KeyEvent instance (KEY_DOWN or KEY_UP)
		public int type;
		// code of the KeyEvent instance (which key pressed)
		public int keyCode;
		// unicode character (used if event is KEY_UP)
		public char keyChar;

		public String toString() {
			StringBuilder builder = new StringBuilder();
			if (type == KEY_DOWN)
				builder.append("key down, ");
			else
				builder.append("key up, ");
			builder.append(keyCode);
			builder.append(",");
			builder.append(keyChar);
			return builder.toString();
		}
	}

	// defines constants that encode a TouchEvent type
	 public static class TouchEvent {
		public static final int TOUCH_DOWN = 0;
		public static final int TOUCH_UP = 1;
		public static final int TOUCH_DRAGGED = 2;

		// type of TouchEvent(TOUCH_DOWN, TOUCH_UP, or TOUCH_DRAGGED)
		public int type;
		// position of finger relative to point of origin
		public int x, y;
		// the pointer ID that was given to the finger by the screen
		// when a finger touches the screen, the first available id is usually
		// assigned to it, but this is not guarateed. 
		public int pointer;

		public String toString() {
			StringBuilder builder = new StringBuilder();
			if (type == TOUCH_DOWN)
				builder.append("touch down, ");
			else if (type == TOUCH_DRAGGED)
				builder.append("touch dragged, ");
			else
				builder.append("touch up, ");
			builder.append(pointer);
			builder.append(",");
			builder.append(x);
			builder.append(",");
			builder.append(y);
			return builder.toString();
		}
	}

	// polling methods
	/**
	 * @param keyCode key that was pressed
	 * @return true if specified key was pressed
	 */
	public boolean isKeyPressed(int keyCode);
	
	/**
	 * @param pointer
	 * @return true if current pointer is touched down
	 */
	public boolean isTouchDown(int pointer);
	
	/**
	 * coordinates will be undefined if the specified pointer is not touched down
	 * @param pointer
	 * @return the x coordinate of pointer touch down
	 */
	public int getTouchX(int pointer);
	/**
	 * coordinates will be undefined if the specified pointer is not touched down
	 * @param pointer
	 * @return they y coordinate of pointer touch down
	 */
	public int getTouchY(int pointer);
	
	/**
	 * @return acceleration value of x axis
	 */
	public float getAccelX();
	
	/**
	 * @return acceleration value of y axis
	 */
	public float getAccelY();
	
	/**
	 * @return acceleration value of z axis
	 */
	public float getAccelZ();
	
	//used for event-based handling
	//return the KeyEvent and TouchEvent instances recorded since the last time these methods were called
	//ordered chronologically with newest at end of the list
	/**
	 * @return the KeyEvent instances recorded since the last time this method was called
	 */
	public List<KeyEvent> getKeyEvents();
	/**
	 * @return the TouchEvent instances recorded since the last time this method was called
	 */
	public List<TouchEvent> getTouchEvents();
}
