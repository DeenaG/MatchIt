package com.badlogic.androidgames.framework;


import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.rubin.matchit.Assets;
import com.rubin.matchit.Settings;

public abstract class Screen {
    protected final Game game;
  
    
    //constructor receives the Game instance and stores it in a final member accessible to all subclasses
    /**
     * constructor
     * @param game
     */
    public Screen(Game game) {
        this.game = game;
    }
    
    /**
	 * checks to see if event is in bounds
	 * @param event
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return true if event is in bounds
	 */
	public boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if ((event.x > x) && (event.x < x + width - 1) && (event.y > y)
				&& event.y < y + height - 1)
			return true;
		return false;
	}

	/**
	 * plays sound if sound is on
	 */
	public void playSound (Sound sound){
		if (Settings.isSoundEnabled())
			sound.play(1);
	}

    /**
     * updates the state of the screen
     * @param deltaTime
     */
    public abstract void update(float deltaTime);
    
    
    /**
     * presents the state of the screen
     * @param deltaTime
     */
    public abstract void present(float deltaTime);

    /**
     * called when game is paused
     */
    public abstract void pause();

    /**
     * called when game is resumed
     */
    public abstract void resume();

    /**
     * called when game is disposed
     */
    public abstract void dispose();
    
  
}
