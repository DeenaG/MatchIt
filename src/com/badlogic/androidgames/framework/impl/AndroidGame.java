package com.badlogic.androidgames.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input;
/*
Game class needs to do the following:
1. perform window management: setting up an activity and AndroidFastRenderView and handling the activity life cycle
2. use and manage WakeLock so the screen does not dim
3. instantiate and hand out references to Graphics, Audio, FileIO, and INput to interested parties
4. Mange Screens and integrate them with teh activity life cycle
5. goal: have single class called AndroidGame from which we can derive for other Games
*/
public abstract class AndroidGame extends Activity implements Game {
	//FastRenderView manages main game loop
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    //crrently active screen
    Screen screen;
    WakeLock wakeLock;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //make Activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set up the artificial framebuffer - set dimensions depending on the orientation
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 1920 : 1080;
        int frameBufferHeight = isLandscape ? 1080 : 1920;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);
        
        //calculate the scaleX and scaleY values for touchhandler classes
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this, "MatchItGame/");
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        //will be implemented by game
        screen = getStartScreen();
        //AndroidFastRenderView  will be the content view of the Activity
        setContentView(renderView);
        
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        //inform screen that game has been resumed
        screen.resume();
        //resume rendering thread (game main loop)
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //release the wakelock
        wakeLock.release();
        //terminate rendering thread (main loop)
        //before screen apush so that UI threa dna main loop won't access Screen at the same time
        renderView.pause();
        //pause the screen
        screen.pause();
        //screen should do cleanup work if the activity is terminated
       if (isFinishing())
            screen.dispose();
    }

    //getters
    public Input getInput() {
        return input;
    }

    public FileIO getFileIO() {
        return fileIO;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public Audio getAudio() {
        return audio;
    }

    //
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");
        //pause and dispose current screen
        this.screen.pause();
        this.screen.dispose();
        //resume and update the screen being passed in with delta time of 0
        screen.resume();
        screen.update(0);
        //set the data member screen to the argument screen
        this.screen = screen;
    }
    
    public Screen getCurrentScreen() {
        return screen;
    }    
}
