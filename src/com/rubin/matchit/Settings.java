package com.rubin.matchit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.util.Log;

import com.badlogic.androidgames.framework.FileIO;

public class Settings {
	//tutorial
	protected static boolean tutorialOn = true;
	
	//sound setting
	protected static boolean soundEnabled = true;
	
	//game mode
	protected static String mode = Constants.STRAIGHT_MODE;
	
	//theme
	protected static int theme = Constants.BLUE;
	
	//stats
	protected static int totalGames = 0;
	protected static int straightTotalGames = 0;
	protected static int zigzagTotalGames = 0;
	protected static int randomTotalGames = 0;
	
	//high scores
	protected static double totalScore = 0.0;
	protected static int straightHighScore = 0;
	protected static int zigzagHighScore = 0;
	protected static int randomHighScore = 0;
	
	//current page
	protected static int currentPage = 0;
	
	 public static boolean isSoundEnabled() {
		return soundEnabled;
	}

	/**
	  * load sets data members based on settings file stored in external storage
	 * @param files
	 */
	public static void load(FileIO files) {
	        BufferedReader in = null;
	        try {
	            in = new BufferedReader(new InputStreamReader(
	            		files.readFile("matchit_settings.txt")));
	            tutorialOn = Boolean.parseBoolean(in.readLine());
	            soundEnabled = Boolean.parseBoolean(in.readLine());
	            mode = in.readLine();
	            theme = Integer.parseInt(in.readLine());
	            totalGames = Integer.parseInt(in.readLine());
	            straightTotalGames = Integer.parseInt(in.readLine());
	            zigzagTotalGames = Integer.parseInt(in.readLine());
	            randomTotalGames = Integer.parseInt(in.readLine());
	            totalScore = Double.parseDouble(in.readLine()); //used for calculating average
	            straightHighScore = Integer.parseInt(in.readLine());
	            zigzagHighScore = Integer.parseInt(in.readLine());
	            randomHighScore = Integer.parseInt(in.readLine());  
	            currentPage = Integer.parseInt(in.readLine());
	        } catch (IOException e) {
	            Log.e("loading", e.toString());
	        } catch (NumberFormatException e) {
	        	Log.e("loading", e.toString());
	        } finally {
	            try {
	                if (in != null)
	                    in.close();
	            } catch (IOException e) {
	            }
	        }
	    }
	 
	 /**
	  * save writes data member values to external storage
	 * @param files
	 */
	public static void save(FileIO files) {
	        BufferedWriter out = null;
	        try {
	            out = new BufferedWriter(new OutputStreamWriter(
	                    files.writeFile("matchit_settings.txt")));
	            out.write(Boolean.toString(tutorialOn) + "\n");
	            out.write(Boolean.toString(soundEnabled) + "\n");
	            out.write(mode + "\n");
	            out.write(Integer.toString(theme) + "\n");
	            out.write(Integer.toString(totalGames) + "\n");
	            out.write(Integer.toString(straightTotalGames) + "\n");
	            out.write(Integer.toString(zigzagTotalGames) + "\n");
	            out.write(Integer.toString(randomTotalGames) + "\n");
	            out.write(Double.toString(totalScore) + "\n");
	            out.write(Integer.toString(straightHighScore) + "\n");
	            out.write(Integer.toString(zigzagHighScore) + "\n");
	            out.write(Integer.toString(randomHighScore) + "\n");
	            out.write(Integer.toString(currentPage) + "\n");
	        } catch (IOException e) {
	        	Log.e("saving", e.toString());
	        
	        } finally {
	            try {
	                if (out != null)
	                    out.close();
	            } catch (IOException e) {
	            }
	        }
	    }
	 

}
