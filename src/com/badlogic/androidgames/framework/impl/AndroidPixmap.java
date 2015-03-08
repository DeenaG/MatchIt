package com.badlogic.androidgames.framework.impl;

import android.graphics.Bitmap;

import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Pixmap;

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;
    
    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

  //return width and height of the Pixmap in pixels
    public int getWidth() {
        return bitmap.getWidth();
    }
    public int getHeight() {
        return bitmap.getHeight();
    }
    
  //returns the PixelFormat that the Pixmap is stored with in RAM 
    //PixmapFormat is an enum specified in Graphics.java
    public PixmapFormat getFormat() {
        return format;
    }

    //dispose to free memory space
    public void dispose() {
        bitmap.recycle();
    }      
}
