package com.badlogic.androidgames.framework;

//plays shorter sound effects stored in memory
public interface Sound {
	
    /**
     * plays a sound
     * @param volume level of volume to play between 0 and 1
     */
    public void play(float volume);
    
    /**
     * disposes of sound when no longer needed
     */
    public void dispose();
}
