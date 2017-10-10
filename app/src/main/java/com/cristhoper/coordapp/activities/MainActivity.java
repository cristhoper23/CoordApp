package com.cristhoper.coordapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cristhoper.coordapp.R;
import com.cristhoper.coordapp.fragments.CoordFragment;
import com.cristhoper.coordapp.fragments.StartFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragMan;
    double latitude = 0.0 , longitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //"getSupportFragmentManager" returns the FragmentManager for interacting with fragments associated with this activity.
        fragMan = getSupportFragmentManager();

        Fragment startFrag = new StartFragment();

        fragMan.beginTransaction().replace(R.id.contentFragment, startFrag).addToBackStack("startFragment").commit();
    }

    public void getCoordinates(View v){
        Fragment coordFrag = new CoordFragment();

        myLocation();

        Bundle args = new Bundle();
        args.putDouble("lat", latitude);
        args.putDouble("lon", longitude);
        coordFrag.setArguments(args);

        fragMan.beginTransaction().replace(R.id.contentFragment, coordFrag).addToBackStack("coordFragment").commit();
    }

    public void myLocation() {
        LocationManager locMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        getLatLon(loc);
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);
    }

    public void getLatLon(Location location){
        if(location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
