package com.folkatech.pressensiapps.di.module

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.constant.AppConstants
import com.folkatech.pressensiapps.common.utils.NotificationUtils
import com.folkatech.pressensiapps.di.scope.PerActivity
import com.folkatech.pressensiapps.services.MyReceiver
import com.folkatech.pressensiapps.ui.activity.home.HomeContract
import com.folkatech.pressensiapps.ui.activity.home.HomePresenter
import com.folkatech.pressensiapps.ui.activity.login.LoginContract
import com.folkatech.pressensiapps.ui.activity.login.LoginPresenter
import com.folkatech.pressensiapps.ui.activity.splash.SplashContract
import com.folkatech.pressensiapps.ui.activity.splash.SplashPresenter
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @PerActivity
    fun provideSplashPresenter(): SplashContract.Presenter = SplashPresenter()

    @Provides
    fun provideLoginPresenter(): LoginContract.Presenter = LoginPresenter()

    @Provides
    fun provideHomePresenter(): HomeContract.Presenter = HomePresenter()

    @Provides
    fun provideBroadcastReceiver(): BroadcastReceiver =
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                // checking for type intent filter
                if (intent.action == AppConstants.REGISTRATION_COMPLETE) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(AppConstants.TOPIC_GLOBAL)

                } else if (intent.action == AppConstants.PUSH_NOTIFICATION) {
                    // new push notification is received
                    val message = intent.getStringExtra("message")
                    val title = intent.getStringExtra("title")
                    val notificationUtils = NotificationUtils(context)
                    notificationUtils.showNotificationMessage(title, message, "", intent)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        // Create channel to show notifications.
                        val channelId =
                            PressensiApp.context!!.getString(R.string.default_notification_channel_id)
                        val channelName =
                            PressensiApp.context!!.getString(R.string.default_notification_channel_name)
                        val notificationManager =
                            ContextCompat.getSystemService(
                                PressensiApp.context!!,
                                NotificationManager::class.java
                            )
                        notificationManager!!.createNotificationChannel(
                            NotificationChannel(
                                channelId,
                                channelName, NotificationManager.IMPORTANCE_LOW
                            )
                        )
                    }
                    Toast.makeText(
                        PressensiApp.context,
                        "Push notification: $message",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    @Provides
    fun provideMyReceiver(): MyReceiver = MyReceiver()

    @Provides
    fun provideAppUpdater(): AppUpdater =
        AppUpdater(PressensiApp.context).setUpdateFrom(UpdateFrom.GOOGLE_PLAY)
}