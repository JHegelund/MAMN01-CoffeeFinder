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

    private Button btn1;
    Location location2 = new Location("");

    //LED Coordinates
    double latitude = 55.7106742;
    double longitude = 13.2101104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_page);

        location2.setLatitude(latitude);
        location2.setLongitude(longitude);

        btn1 = (Button) findViewById(R.id.vibrationButton);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(500);
            }
        });

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.

                float radius = (float) 50.0;
                float distance = location.distanceTo(location2);
                if (distance < radius){
                    Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vb.vibrate(500);
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
