package com.badlogic.androidgames.framework.impl;

import android.media.SoundPool;

import com.badlogic.androidgames.framework.Sound;

//plays shorter sound effects stored in memory
public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;
    
    //store the SoundPool and the ID of the loaded sound effect for later playback and disposal
    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    public void dispose() {
        soundPool.unload(soundId);
    }
}
