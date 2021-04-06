package com.folkatech.pressensiapps.services

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.folkatech.pressensiapps.common.constant.AppConstants
import com.folkatech.pressensiapps.common.constant.AppConstants.KEY_REG_ID
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val refreshedToken = FirebaseInstanceId.getInstance().token

        storeRegIdInPref(refreshedToken)

        sendRegistrationToServer(refreshedToken)

        val registrationComplete = Intent(AppConstants.REGISTRATION_COMPLETE)
        registrationComplete.putExtra("token", refreshedToken)
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete)
    }

    private fun sendRegistrationToServer(token: String?) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token!!)
        println("sendRegistrationToServer: $token")

    }

    private fun storeRegIdInPref(token: String?) {
        val pref = applicationContext.getSharedPreferences(AppConstants.SHARED_PREF, 0)
        val editor = pref.edit()
        editor.putString(KEY_REG_ID, token)
        editor.apply()
    }

    companion object {
        private val TAG = MyFirebaseInstanceIDService::class.java.simpleName
    }

}