package com.example.hegelund.coffeefinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.location.LocationManager;
import android.content.Context;
import android.location.LocationListener;
import android.location.Location;
import android.widget.Toast;

public class GpsPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_page);

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
