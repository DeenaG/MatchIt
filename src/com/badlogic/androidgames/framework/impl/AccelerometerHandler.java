package com.badlogic.androidgames.framework.impl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHandler implements SensorEventListener {
    float accelX;
    float accelY;
    float accelZ;

    //constructor takes a Context, from which it gets a sensorManager instance to set up the event listening
    public AccelerometerHandler(Context context) {
    	 //tells us whether an accelerometer is installed, and registers listener
        SensorManager manager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        //check to make sure that device has accelerometer (currently, all android devices have accelerometer, but this may change)
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
        	//register the listener
            Sensor accelerometer = manager.getSensorList(
                    Sensor.TYPE_ACCELEROMETER).get(0);
            //SENSOR_DELAY_GAME is a constant designed for games that specifies how often the lister should be updated with accelerometer state
            manager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // nothing to do here
    }

    public void onSensorChanged(SensorEvent event) {
    	//values is a public float array (from SensorEvent) that holds the current acceleration values fo the three axes
    	//values[0] holds x axis, values[1] holds y axis, values [2] holds z axis
        accelX = event.values[0];
        accelY = event.values[1];
        accelZ = event.values[2];
    }

    public float getAccelX() {
        return accelX;
    }

    public float getAccelY() {
        return accelY;
    }

    public float getAccelZ() {
        return accelZ;
    }
}
