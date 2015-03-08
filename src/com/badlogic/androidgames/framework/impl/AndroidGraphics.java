package com.badlogic.androidgames.framework.impl;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;

public class AndroidGraphics implements Graphics {
	//AssetManager will load the Bitmap instances
    AssetManager assets;
    //Bitmap memeber represetns artificial frambuffer
    Bitmap frameBuffer;
    //Canvas member will draw to the artificial frambuffer
    Canvas canvas;
    //paint member is used for drawing
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    // tries to load a Bitma from an asset file, using the specified PixMapFormat
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        Config config = null;
        //translate the PixmapFormat into one of the constants of the Android Config class 
        if (format == PixmapFormat.RGB565)
            config = Config.RGB_565;
        else if (format == PixmapFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        //create a new Options instance and set or preferred color format
        Options options = new Options();
        options.inPreferredConfig = config;

        //try to load a Bitmap from asset via BitmapFactory 
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        //construct new AndroidBitmap instance based on the Bitmap loaded, and the PixmapFormat
        if (bitmap.getConfig() == Config.RGB_565)
            format = PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = PixmapFormat.ARGB4444;
        else
            format = PixmapFormat.ARGB8888;
       
        return new AndroidPixmap(bitmap, format);
    }
    
    public Typeface newFont(String fileName){
    	InputStream in = null;
        Typeface typeface = null;
        try {
            in = assets.open(fileName);
            typeface = Typeface.createFromAsset(assets, fileName);
            if (typeface == null)
                throw new RuntimeException("Couldn't load font from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load font from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        
        return typeface;
    }
    	
    

    //extracts the red, green and blue components of the specified 32-bit ARGB color parameter and calls the Canvas.drawRGB() method
    //this clears the artificial framebuffer with that color
    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }

    //draws a pixel of our artificial FrameBuffer via the Canvas.drawPoint() method
  //set the pixel at (x,y) in the framebuffer to specified color
    //coordinates outside screen area are ignored
    public void drawPixel(int x, int y, int color, int alpha) {
        paint.setColor(color);
        paint.setAlpha(alpha);
        canvas.drawPoint(x, y, paint);
    }

    //draws the given line of the artificial frambuffer, using the Paint member to specify the color when calling hte Canvas.drawLine() method
    //specify start point and end point of line and color
    public void drawLine(int x, int y, int x2, int y2, int color, int alpha) {
        paint.setColor(color);
        paint.setAlpha(alpha);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    //sets the Paint member's color and style attributes so that we can draw a filled, colored rectangle
    //in the Canvas.drawRect() cal, we have to transform the x/y/width/height 
    	//parameters of the coordinates in the top-left and bottom-right corners of the rectangle 
  //draws a rectangle to the framebuffer
    //the (x,y) specifies the position of the rectangle's top-left corner in the framebuffer
    //width and height specify number of pixels in x and y
    //color argument will fill the rectangle
    public void drawRect(int x, int y, int width, int height, int color, int alpha) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        paint.setAlpha(alpha);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    //allows us to draw a portion of a Pixmap
    //draws rectangular portions of a Pixmap to the framebuffer
    //sets the source and destination of the Rect members that are used in the actual drawing call
    //the (x,y) coordinates specify top-left corner pposition 
    //scrWidth and srcHeight specify the size of the portion to take from the Pixmap
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight, int alpha) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;
        paint.setAlpha(alpha);
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, paint);
    }

    //draws rectangular portions of a Pixmap to the framebuffer
    //draws the complete Pixmap to the artificial frambuffer at the given oordinates
    public void drawPixmap(Pixmap pixmap, int x, int y, int alpha) {
    	paint.setAlpha(alpha);
        canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap, x, y, paint);
    }

    public void drawText(Typeface font, Align align, int color, String text, float size, float x, float y){
    	paint.setColor(color);
    	paint.setTextSize(size);
    	paint.setTextAlign(align);
    	paint.setTypeface(font);
    	canvas.drawText(text, x, y, paint);
    }

    //return the size of the artificial frambuffer stored by the AndroidGraphics class 
    public int getWidth() {
        return frameBuffer.getWidth();
    }
    public int getHeight() {
        return frameBuffer.getHeight();
    }
    
}

