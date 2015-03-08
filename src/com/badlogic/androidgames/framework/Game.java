package com.badlogic.androidgames.framework;

import com.badlogic.androidgames.framework.Screen;

import android.app.Activity;
import android.content.Context;

/*
game interface needs to do the following:
	1. set up the window and UI component and hook into callbacks so that we can receive window and input evetns 
	2. start th emain loop thread
	3. keep track of the current screen and tell it to update and present itself in every main look iteration (frame)
	4. transfer any window events (ex. pause and resume) from the UI thread to the main loop thread and pas them to the 
		current screen
	5. grant access to all the previously developed modules (Input, FileIO, Grahics, Audio
*/
public interface Game {
	//getter methods return instances of low-level modules
	//the Game implementation will instantiate and track these meodules
    /**
     * @return Input object
     */
    public Input getInput();
    
    /**
     * @return File object
     */
    public FileIO getFileIO();
    
    /**
     * @return Graphics object
     */
    public Graphics getGraphics();
    
    /**
     * @return Audio object
     */
    public Audio getAudio();
    
    /**
     * sets the current screen of the game
     * @param screen
     */
    public void setScreen(Screen screen);
    
    /**
     * @return the currently active Screen instance
     */
    public Screen getCurrentScreen();
    
    /**
     * @return the initial screen for the game
     */
    public Screen getStartScreen();
    
    /**
     * @return the context of the activity
     */
    public Context getContext();
    
    /**
     * @param activity starts a new activity
     */
    public void startNewActivity(Activity activity);
    
    /**
     * creates a toast
     * @param text text to display in the toast
     */
    public void toast(String text);
}