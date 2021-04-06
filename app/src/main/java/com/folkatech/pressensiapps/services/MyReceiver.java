package com.folkatech.pressensiapps.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import static android.R.attr.action;

/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Location location = intent.getParcelableExtra(LocationUpdateService.EXTRA_LOCATION);
        if (location != null) {
//            Toast.makeText(context, Utilities.getLocationText(location),
//                    Toast.LENGTH_SHORT).show();
        }
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals (action)) {
            NetworkInfo netInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (ConnectivityManager.TYPE_WIFI == netInfo.getType()) {

            }
        }
    }
}
