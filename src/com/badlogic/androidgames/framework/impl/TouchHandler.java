package com.badlogic.androidgames.framework.impl;

import java.util.List;

import android.view.View.OnTouchListener;

import com.badlogic.androidgames.framework.Input.TouchEvent;

//this interface will allow us to use two touch event handlers interchangeably
//Android version 1.6 and below will use the SingleTouchHandler
//Android 1.7 ad above will use the MultiTouchHandler

//All TouchHandlers must implement the OnTouchListener interface, which is used to register the handler with a View
public interface TouchHandler extends OnTouchListener {
	//first three methods are for polling the state of a specific pointer ID
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    //get TouchEvents with which to perform event-based input handling
    public List<TouchEvent> getTouchEvents();
}
