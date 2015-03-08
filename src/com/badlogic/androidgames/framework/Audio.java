package com.badlogic.androidgames.framework;

//used to create new Music and Sound interfaces
//all filenames refer to asset files in the APK file
public interface Audio {
	
    /**
     * represents streamed audio file
     * @param filename of the music file
     * @return Music object
     */
    public Music newMusic(String filename);

    /**
     * represents a short sound effect
     * @param filename of the audio file
     * @return Sound object
     */
    public Sound newSound(String filename);
}
