package com.rubin.matchit;
import com.badlogic.androidgames.framework.Pixmap;

public class Label {
	private Pixmap pix;
	private boolean pressed;
	
	
	/**
	 * Constructor
	 * @param pix pixmap to draw
	 */
	public Label (Pixmap pix){
		this.pix = pix;
		this.pressed = false;
	}
	
	/**
	 * @return Pixmap to draw
	 */
	public Pixmap getPix() {
		return pix;
	}

	/**
	 * @param pix Pixmap to draw
	 */
	public void setPix(Pixmap pix) {
		this.pix = pix;
	}

	/**
	 * @return true if Pixmap is pressed 
	 */
	public boolean isPressed() {
		return pressed;
	}

	/**
	 * @param pressed true if element is pressed
	 */
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
}
