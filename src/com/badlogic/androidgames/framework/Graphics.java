package com.badlogic.androidgames.framework;

import android.graphics.Typeface;
import android.graphics.Paint.Align;

//draws images (knows as bitmaps) to the screen
public interface Graphics {
	
	//includes different pixel formats that will be supported
    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }
    
    //will load an image (either JPEG or PNG) 
    //specify a desired format for the resulting Pixmap
    //Pixmap might have a different format
    //filneame refers to asset in APK file
    /**
     * @param fileName name of Pixmap file
     * @param format desired format for the Pixmap
     * @return
     */
    public Pixmap newPixmap(String fileName, PixmapFormat format);
    
    /**
     * @param fileName name of Typeface font file
     * @return Typeface font
     */
    public Typeface newFont(String fileName);
    
    /**
     * clears the framebuffer with specified color
     * @param color to draw to the framebuffer
     */
    public void clear(int color);
    
    /**
     * draws a pixel to the framebuffer
     * @param x x coordinate of pixel
     * @param y y coordinate of pixel
     * @param color color of pixel
     * @param alpha alpha value of pixel
     */
    public void drawPixel(int x, int y, int color, int alpha);
    
    /**
     * draws a line to the framebuffer
     * @param x starting x coordinate of the line
     * @param y starting y coordinate of the line
     * @param x2 ending x coordinate of a line
     * @param y2 ending y coordinate of a line
     * @param color color of the line
     * @param alpha alpha value of the line
     */
    public void drawLine(int x, int y, int x2, int y2, int color, int alpha);

    /**
     * @param x top-left x coordinate
     * @param y top-left y coordinate
     * @param width width of rectangle
     * @param height height of rectangle
     * @param color color of rectangle
     * @param alpha alpha value of rectangle
     */
    public void drawRect(int x, int y, int width, int height, int color, int alpha);
    
    /**
     * draws rectangular portions of a Pixmap to the framebuffer
     * @param pixmap pixmap to be drawn to the screen
     * @param x top-left x coordinate
     * @param y top-left y coordiante
     * @param srcX size of portion to take from the pixmap
     * @param srcY size of portion to take from the pixmap
     * @param srcWidth width of pixmap
     * @param srcHeight height of pixmap
     * @param alpha
     */
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight, int alpha);
    
    /**
     * @param pixmap pixmap to be drawn to the framebuffer
     * @param x top-left x coordinate
     * @param y top-left y coordinate
     * @param alpha alpha value
     */
    public void drawPixmap(Pixmap pixmap, int x, int y, int alpha);
    
    /**
     * draws text to the framebuffer
     * @param font specifies font to draw
     * @param align alignment of the text
     * @param color color of the text
     * @param text text to draw tot eh screen
     * @param size font size of the text
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void drawText (Typeface font, Align align, int color, String text, float size, float x, float y);
    
    /**
     * @return width of the framebuffer in pixels
     */
    public int getWidth();
    
    /**
     * @return height of the framebuffer in pixels
     */
    public int getHeight();
}
