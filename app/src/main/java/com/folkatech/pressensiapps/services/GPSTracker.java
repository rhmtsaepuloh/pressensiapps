package com.folkatech.pressensiapps.services;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.folkatech.pressensiapps.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
public class GPSTracker extends Service {

    private static final String TAG = "GPSTracker";

    private Context mContext;

    // Flag for GPS status
    boolean isGPSEnabled = false;

    // Flag for network status
    boolean isNetworkEnabled = false;

    // Flag for GPS status
    boolean canGetLocation = false;

    Location location; // Location
    double latitude; // Latitude
    double longitude; // Longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1000; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    Activity activity;

    public GPSTracker() {
    }

    public GPSTracker(Context context, Activity activity) {
        this.mContext = context;
        this.activity = activity;
        getLocation();
//        setlastKnownLocation();
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        try {


            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            // Getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // Getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // No network provider is enabled
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
                    int requestPermissionsCode = 50;

                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, mLocationListener);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
                            mFusedLocationClient.getLastLocation()
                                    .addOnSuccessListener(activity, mLocation -> {
                                        // Got last known location. In some rare situations this can be null.
                                        if (mLocation != null) {
                                            // Logic to handle location object
                                            location = mLocation;
                                            Log.d(TAG, "getLocation: " + mLocation.getLongitude());
                                            //location = service.getLastKnownLocation(provider);
                                            if (location != null) {
                                                latitude = location.getLatitude();
                                                longitude = location.getLongitude();
                                            }
                                        }
                                    });
                        }
                    }
                }
            }
            // If GPS enabled, get latitude/longitude using GPS Services
            if (isGPSEnabled) {
                if (location == null) {
                    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 50);

                    } else {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, mLocationListener);
                        Log.d(TAG, "GPS Enabled");
                        if (locationManager != null) {

                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                Log.d(TAG, "getLocation: location " + location.getLatitude());
                            } else {
                                Log.d(TAG, "getLocation: location null");
                            }
                            if (location != null) {
                                FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
                                mFusedLocationClient.getLastLocation()
                                        .addOnSuccessListener(activity, mLocation -> {
                                            // Got last known location. In some rare situations this can be null.
                                            if (mLocation != null) {
                                                // Logic to handle location object
                                                location = mLocation;
                                                Log.d(TAG, "getLocation: mLocation " + mLocation.getLatitude());
                                                //location = service.getLastKnownLocation(provider);
                                                if (location != null) {
                                                    latitude = location.getLatitude();
                                                    longitude = location.getLongitude();
                                                }
                                            }
                                        });
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }


    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app.
     */
    public void stopUsingGPS() {

    }

    @SuppressLint("MissingPermission")
    public Location getLastKnownLocation() {
//        setlastKnownLocation();

        return location;
    }

    @SuppressLint("MissingPermission")
    public void setlastKnownLocation() {
        /*isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);*/

        getLocation();

//        if (location == null) {
        if (isGPSEnabled) {
            location = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(activity, mLocation -> {
                        // Got last known location. In some rare situations this can be null.
                        if (mLocation != null) {
                            // Logic to handle location object
                            location = mLocation;
                            Log.d(TAG, "setlastKnownLocation: mLocation" + location.getLatitude());
                        }
                    });
            if (location != null) {
                Log.d(TAG, "setlastKnownLocation: location" + location.getLatitude());
            } else {
                Log.d(TAG, "setlastKnownLocation: null");
            }
        } else {
            showSettingsAlert();
        }
        /*}
        else {
            Log.d(TAG, "setlastKnownLocation: latitude = " + location.getLatitude() + " longitude = " + location.getLongitude());
        }*/
    }


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {

//            if (location != null) {
//                latitude = location.getLatitude();
//                longitude = location.getLongitude();
//            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    /**
     * Function to get latitude
     */
    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }


    /**
     * Function to get longitude
     */
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/Wi-Fi enabled
     *
     * @return boolean
     */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }


    /**
     * Function to show settings alert dialog.
     * On pressing the Settings button it will launch Settings Options.
     */
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle(mContext.getString(R.string.title_gps_disabled));

        // Setting Dialog Message
        alertDialog.setMessage(mContext.getString(R.string.label_message_gps_disabled));

        // On pressing the Settings button.
        alertDialog.setPositiveButton(mContext.getString(R.string.settings), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
                dialog.dismiss();
            }
        });

        // On pressing the cancel button
        alertDialog.setNegativeButton(mContext.getString(R.string.label_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
