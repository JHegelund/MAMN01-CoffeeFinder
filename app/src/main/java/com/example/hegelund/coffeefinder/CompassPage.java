package com.example.hegelund.coffeefinder;

import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Vibrator;
import android.app.Activity;

public class CompassPage extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mRotation;
    //private float mPrevDegree = 0f;

    private double minAngle = 10;
    Location testLocation, ourLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass_page);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mRotation = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        testLocation = new Location("");
        testLocation.setLatitude(55.7168195);
        testLocation.setLongitude(13.1967485);

        ourLocation = new Location("");
        ourLocation.setLatitude(ourLocation.getLatitude());
        ourLocation.setLongitude(ourLocation.getLongitude()); //switch place on long and lat?
        //  lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mRotation, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mRotation);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //----TYPE_ROTATION_VECTOR--------------------------------------------
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] r = new float[9];
            float[] mOrientation2 = new float[3];
            SensorManager.getRotationMatrixFromVector(r, event.values);
            SensorManager.getOrientation(r, mOrientation2);
            float azimuthInDegress = (float) (Math.toDegrees(mOrientation2[0]) + 360) % 360; //idea: (int)
            int nbr = Math.round(azimuthInDegress);
            String str = Integer.toString(nbr);


            double angle = Math.abs(angelToLocation(ourLocation)-nbr);
            if(angle>360){
                angle = angle-360;
            }


            //CHECK ANGLE TO LOCATION
            if(angle < minAngle){
                Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                v.vibrate(500);
            }
        }
    }

    public double angelToLocation(Location ourLocation){
        return ourLocation.bearingTo(testLocation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

}