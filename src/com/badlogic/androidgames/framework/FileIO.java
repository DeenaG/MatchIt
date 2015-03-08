package com.badlogic.androidgames.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//used for reading and writing to files
//reading files: used to read files packaged with the game such as images and audio
//writing files: used to save game state to resume if game is closed, or to save stats/high scores
public interface FileIO {
	/**
	 * @param fileName name of asset to read
	 * @return InputStream
	 * @throws IOException
	 */
	public InputStream readAsset(String fileName) throws IOException;

	/**
	 * @param fileName name of file to read
	 * @return Inputstream
	 * @throws IOException
	 */
	public InputStream readFile(String fileName) throws IOException;

	/**
	 * @param fileName name of file to write
	 * @return OutputStream
	 * @throws IOException
	 */
	public OutputStream writeFile(String fileName) throws IOException;
}
