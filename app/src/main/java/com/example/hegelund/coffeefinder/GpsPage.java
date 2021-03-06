package com.example.hegelund.coffeefinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.location.LocationManager;
import android.content.Context;
import android.location.LocationListener;
import android.location.Location;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.os.Vibrator;

public class GpsPage extends ActionBarActivity {

    //private Button btn1;
    Location location2 = new Location("");
    Location location3 = new Location("");

    boolean stopGps = false;

    //LED Coordinates
    double latitude = 55.7168195;
    double longitude = 13.1967485;

    double latitude2 = 55.7152585;
    double longitude2 = 13.2125805;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_page);

        location2.setLatitude(latitude);
        location2.setLongitude(longitude);

        location3.setLatitude(latitude2);
        location3.setLongitude(longitude2);


        /*btn1 = (Button) findViewById(R.id.vibrationButton);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(500);
            }
        });*/

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.

                long[] pattern = {0, 1000, 1000, 1000, 1000, 1000};
                float radius = (float) 200.0;
                float distance = location.distanceTo(location2);
                float distance2 = location.distanceTo(location3);
                if (distance < radius && stopGps == false){
                    Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    //vb.vibrate(1000);
                    vb.vibrate(pattern, -1);
                    stopGps = true;
                    Intent i = new Intent(GpsPage.this, CompassPage.class);
                    startActivity(i);
                } else if (distance > radius){
                    stopGps = false;
                    Toast.makeText(getBaseContext(), "You are more than 200 away from coffee, keep walking", Toast.LENGTH_LONG).show();
                }

                if(distance2 < radius && stopGps == false){
                    Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    //vb.vibrate(1000);
                    vb.vibrate(pattern, -1);
                    stopGps = true;
                    Intent i = new Intent(GpsPage.this, CompassPage.class);
                    startActivity(i);
                } else if (distance2 > radius){
                    //stopGps = false;
                }


                String msg = "New Latitude: " + location.getLatitude()
                        + "New Longitude: " + location.getLongitude();

                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        String locationProvider = LocationManager.NETWORK_PROVIDER;

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

        //Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        //locationManager.removeUpdates(locationListener);

    }

}
