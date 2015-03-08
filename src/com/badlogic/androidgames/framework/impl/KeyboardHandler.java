package com.badlogic.androidgames.framework.impl;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnKeyListener;

import com.badlogic.androidgames.framework.Input.KeyEvent;
import com.badlogic.androidgames.framework.Pool;
import com.badlogic.androidgames.framework.Pool.PoolObjectFactory;

//1. connect with teh View from which the keyboard events are to be received
//2. store the current state of each key for polling
//3. Keep a list of KeyEvent instances for event-based input handling
//4. must properly synchronize everything since it will receive events from the UI thread while being polled from the main game loop
public class KeyboardHandler implements OnKeyListener  { //OnKeyListener receives key events from a View
	
	//store current state of each key in this array (indexed by the key's key code)
    boolean[] pressedKeys = new boolean[128];
    
    //the Pool holds the instances of the KeyEvent class to recycle
    Pool<KeyEvent> keyEventPool;
    
    //stores the KeyEvent instances that have not been consumed by the game
    List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>(); 
    
    //stores the KeyEvents that we return by calling the KeyboardHandler.getKeyEvents()
    List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();
    
    //constructor takes in the View that we'll be receiving events from
    public KeyboardHandler(View view) {
    	//create Pool instance with PoolObjectFactory
        PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>() {
            public KeyEvent createObject() {
                return new KeyEvent();
            }
        };
        keyEventPool = new Pool<KeyEvent>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        //make this view the focus view to make sure that it will receive key events
        view.requestFocus();
    }

    //called each time the View receives a new key event
    public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
    	//ignore multiple key events because nto relevant in this context
        if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
            return false;

        synchronized (this) {
        	//get new KeyEvent object from pool
            KeyEvent keyEvent = keyEventPool.newObject();
            //set keyCode and keyChar datea mamebers based on teh contents onf the Andorid KeyEvent that were passed into the method
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();
            
            //decode the Android KeyEvent type and set he type of our KeyEvent, as well as the element in the pressed Key array accordingly
            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                keyEvent.type = KeyEvent.KEY_DOWN;
                if(keyCode > 0 && keyCode < 127)
                    pressedKeys[keyCode] = true;
            }
            if (event.getAction() == android.view.KeyEvent.ACTION_UP) {
                keyEvent.type = KeyEvent.KEY_UP;
                if(keyCode > 0 && keyCode < 127)
                    pressedKeys[keyCode] = false;
            }
            
            //add keyEvent to the keyEventBuffer list
            keyEventsBuffer.add(keyEvent);
        }
        return false;
    }

    //returns true of the keyEvent is within normal parameters (no need to synchronize because working with primitive types)
    //pass an integer that specifies a key code, and return whether or not that key was pressed
    //this is done by looking up the sate of the key in the pressedKey array 
    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode > 127)
            return false;
        return pressedKeys[keyCode];
    }

    //synchronized block because this method will be called from a different thread
    //loop through he keyEvents array and insert of of its KeyEvents into our Pool
    public List<KeyEvent> getKeyEvents() {
        synchronized (this) {
            int len = keyEvents.size();
            for (int i = 0; i < len; i++) {
                keyEventPool.free(keyEvents.get(i));
            }
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }
}
