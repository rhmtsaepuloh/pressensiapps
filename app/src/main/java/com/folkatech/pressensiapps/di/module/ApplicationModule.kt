package com.folkatech.pressensiapps.di.module

import android.app.Application
import com.folkatech.pressensiapps.BuildConfig
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.library.CustomViewWithTypeFaceSupport
import com.folkatech.pressensiapps.common.library.TextField
import com.folkatech.pressensiapps.di.scope.PerApplication
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@Module
class ApplicationModule(private val pressensiApp: PressensiApp) {
    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application = pressensiApp

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                if (BuildConfig.DEBUG) {
                    addInterceptor(ChuckInterceptor(PressensiApp.context))
                    addInterceptor(interceptor)
                }
            }
            .build()
}