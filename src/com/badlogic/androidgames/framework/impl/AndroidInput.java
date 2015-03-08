package com.badlogic.androidgames.framework.impl;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;

import com.badlogic.androidgames.framework.Input;

//this class ties together all of the handlers that have been developed.
//all method calls are delegated to the corresponding handler
//1. polling access to the touchscreen, keyboard and accelerometer
//2. event based access to the touchscreen and keyboard
@TargetApi(4)
public class AndroidInput implements Input {  
	//delegate method calls to these handlers
    AccelerometerHandler accelHandler;
    KeyboardHandler keyHandler;
    TouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        accelHandler = new AccelerometerHandler(context);
        keyHandler = new KeyboardHandler(view);       
      //choose the TouchHandler based on the Android version being used
        if (Build.VERSION.SDK_INT < 5) 
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        else
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);        
    }

    //polling methods
    
    public boolean isKeyPressed(int keyCode) {
        return keyHandler.isKeyPressed(keyCode);
    }

    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    public float getAccelX() {
        return accelHandler.getAccelX();
    }

    public float getAccelY() {
        return accelHandler.getAccelY();
    }

    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }

    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
    
    public List<KeyEvent> getKeyEvents() {
        return keyHandler.getKeyEvents();
    }
}
