package com.badlogic.androidgames.framework.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable {
	Handler innerHandler;
    AndroidGame game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
  //the volatile flag means that statements usingz it must be executed in order
    volatile boolean running = false;

    //call the the base class's constructor with teh AndroidGame parameter (an Activity)
    public AndroidFastRenderView(AndroidGame game, Bitmap framebuffer) {
        super(game);
        this.game = game;
        this.framebuffer = framebuffer;
      //create instance of SurfaceHolder class (allows us to render to a SurfaceView from a different thread than the UI thread)
        //Surface is created asynchronously
        this.holder = getHolder();
    }

    //this method makes sure that our thread interacts with activity lifecycle
    public void resume() { 
        running = true;
        renderThread = new Thread(this);
        renderThread.start();         
    }      

    
    public void run() {
    	
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        while(running) {  
        	//isValid() returns true if the Surface has been created
            if(!holder.getSurface().isValid())
                continue;           
            //tracks delta time between each frame - take the difference between the last loop iteration's start time and the current time
            //System.nanoTime returns the current time in nanoseconds as long (1 billionth of second)
            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            //get current timestamp - to use in next loop iteration
            startTime = System.nanoTime();
            
            //update the game logic and render things to the artificial framebuffer
            game.getCurrentScreen().update(deltaTime);
            game.getCurrentScreen().present(deltaTime);
            
            //get hold of the Canvas for the SurfaceVies and draw teh artificial framebuffer
            Canvas canvas = holder.lockCanvas();
            
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(framebuffer, null, dstRect, null);
          //unlocks the Surface again and makes sure that what we've drawn via the Canvas gets displayed on the screen
            holder.unlockCanvasAndPost(canvas);
        }
    }

    //terminates the rendering/main loop thread and waits for it to die before returning
    public void pause() {      
    	//running is volatile, so must be set to false before the loop is implemented (prevents infinite loop)
        running = false;                        
        while(true) {
            try {
            	//waits for thread to die
                renderThread.join();
                return;
            } catch (InterruptedException e) {
                // retry
            }
        }
    }        
}
