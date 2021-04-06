package com.folkatech.pressensiapps.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import com.folkatech.pressensiapps.PressensiApp


/**
 * Created by Rahmat Saefulloh on 15/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
object NetworkHelper {
    fun isNetworkConnected(): Boolean {
        val cm =
            PressensiApp.context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun wifiInfo(): WifiInfo {
        val wifiManager =
            PressensiApp.context!!.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.connectionInfo
    }
}