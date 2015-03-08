package com.badlogic.androidgames.framework;

import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public interface Pixmap {
	//return width and height of the Pixmap in pixels
    public int getWidth();
    public int getHeight();
    
    //returns the PixelFormat that the Pixmap is stored with in RAM 
    //PixmapFormat is an enum specified in Graphics.java
    /**
     * @return PixmapFormat of the pixmap
     */
    public PixmapFormat getFormat();
    
    /**
     * dispose Pixmap to free memory space
     */
    public void dispose();
}
