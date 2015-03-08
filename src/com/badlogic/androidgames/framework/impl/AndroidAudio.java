package com.badlogic.androidgames.framework.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.Music;
import com.badlogic.androidgames.framework.Sound;

//used to create new Music and Sound interfaces
//all filenames refer to asset files in the APK file
public class AndroidAudio implements Audio {
	//AssetManager used to load sound effects from asset files into the SoundPool
    AssetManager assets;
    SoundPool soundPool;

    //Activity is passed to the constructor to set the volume control and to use the AssetManager from the activity 
    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    //creates an AndroidMusic instance
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            //AndroidMusic constructor takes an AssetFileDescriptor, which is used to create an internal MediaPlayer
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }
    
    //Loads a sound effect from an asset into the SoundPool and returns and AndroidSound instance. 
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            //AndroidSoundPool constructor takes in the SoundPool and the ID of the sound effect assigned to it by the SoundPool
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}