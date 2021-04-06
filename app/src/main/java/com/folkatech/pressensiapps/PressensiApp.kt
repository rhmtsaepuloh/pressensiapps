package com.folkatech.pressensiapps

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.folkatech.pressensiapps.di.component.ApplicationComponent
import com.folkatech.pressensiapps.di.component.DaggerApplicationComponent
import com.folkatech.pressensiapps.di.module.ApplicationModule
import okhttp3.OkHttpClient
import javax.inject.Inject


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class PressensiApp : MultiDexApplication() {
    private val TAG = "Pressensi"

    @Inject
    lateinit var okHttpClient: OkHttpClient

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        context = this
        singleton = this

        injectApplicationComponent()
        client = okHttpClient
        initFacebookSDK()
    }

    private fun initFacebookSDK() {
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)
    }

    @Suppress("DEPRECATION")
    private fun injectApplicationComponent() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        private val TAG = "PressensiApp"

        @SuppressLint("StaticFieldLeak")
                /**
                 * The application [Context] made static.
                 * Do **NOT** use this as the context for a view,
                 * this is mostly used to simplify calling of resources
                 * (esp. String resources) outside activities.
                 */
        var context: Context? = null
            private set

        var client: OkHttpClient? = null
            private set

        @SuppressLint("StaticFieldLeak")
        var singleton: PressensiApp? = null
            private set

        val getInstance: PressensiApp?
            get() = singleton

        val sharedPreferences: SharedPreferences
            get() = PressensiApp.getInstance!!.getSharedPreferences("mypref", Context.MODE_PRIVATE)
    }
}