package com.folkatech.pressensiapps.di.component

import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: PressensiApp)
}