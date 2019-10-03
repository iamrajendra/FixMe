package com.iamrajendra.codenamefixme;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import static android.content.Context.SENSOR_SERVICE;

public class Shake implements SensorEventListener {
    private Context context;
    private SensorManager mSensorManager;
    private Sensor accSensor;
    private Sensor magnetSensor;
    private static final float SHAKE_THRESHOLD = 3.25f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;
    private  ShakeListener listener;

    public void setListener(ShakeListener listener) {
        this.listener = listener;
    }

    public Shake(Context context) {
        this.context = context;
    }

    public  void resume(){

        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, magnetSensor, SensorManager.SENSOR_DELAY_UI);
    }

    public void onPause() {

        mSensorManager.unregisterListener(this, accSensor);
        mSensorManager.unregisterListener(this, magnetSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                Log.d("log", "Acceleration is " + acceleration + "m/s^2");

                if (acceleration > SHAKE_THRESHOLD) {
                    mLastShakeTime = curTime;
                    Log.d("log", "Shake, Rattle, and Roll");
                    if (listener!=null)listener.onShake();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
