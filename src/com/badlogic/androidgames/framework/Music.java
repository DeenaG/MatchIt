package com.badlogic.androidgames.framework;

//larger music files to be streamed
public interface Music {

	/**
	 * starts laying the music stream
	 */
	public void play();

	/**
	 * stops the music stream
	 */
	public void stop();
 
	/**
	 * pauses the music stream
	 */
	public void pause();

	
	/**
	 * sets the loop playback - will automatically start playing from the
	 * beginning when the end of the file is reached
	 * @param looping 
	 */
	public void setLooping(boolean looping);

	/**
	 * sets volume between 0 and 1
	 * @param volume 
	 */
	public void setVolume(float volume);
	
	//getter methods allow us to poll the current state of the Music instance
	/**
	 * @return true if the music is playing
	 */
	public boolean isPlaying();
	
	/**
	 * @return true if music is stopped
	 */
	public boolean isStopped();
	
	/**
	 * @return true if music is looping
	 */
	public boolean isLooping();
	
	/**
	 * 	dispose of the music instance when it is no longer needed
	 */
	public void dispose();
}
