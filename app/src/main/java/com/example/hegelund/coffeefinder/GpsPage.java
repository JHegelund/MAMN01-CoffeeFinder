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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_page);

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
