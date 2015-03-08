package com.rubin.matchit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;


public class MatchItGame extends AndroidGame {

	public Screen getStartScreen(){
		return new LoadingScreen(this);
	}

	public Context getContext(){
    	return this;
    }
	
	public void startNewActivity(Activity activity){
		Intent intent = new Intent();
	    intent.setClass(this.getContext(), activity.getClass());
	    startActivity(intent);
	}
	
	public void toast(String text){
		Toast.makeText(this.getContext(), text, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onBackPressed() {
		Toast.makeText(this, "button pressed", Toast.LENGTH_SHORT).show();
	}
}
