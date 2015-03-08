package com.badlogic.androidgames.framework.impl;
////////////
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.badlogic.androidgames.framework.FileIO;

//used for reading and writing to files
//reading files: used to read files packaged with the game such as images and audio
//writing files: used to save game state to resume if game is closed, or to save stats/high scores
public class AndroidFileIO implements FileIO {
    Context context;
    AssetManager assets;
    String externalStoragePath;
    
    public AndroidFileIO(Context context, String folderName) {
        this.context = context;
        this.assets = context.getAssets();
        try{
        	//get root directory of external storage
        	this.externalStoragePath = context.getExternalFilesDir(null).getAbsolutePath() + File.separator + folderName;
        	File dir = new File(context.getExternalFilesDir(null), folderName);
        	Log.d("path", dir.toString());
        	dir.mkdirs();
        }
        catch(Exception e){
        	Log.e("error", e.toString());
        }
    }

    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
    
    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
