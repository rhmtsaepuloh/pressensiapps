package com.folkatech.pressensiapps.di.module

import com.folkatech.pressensiapps.ui.fragment.beranda.BerandaContract
import com.folkatech.pressensiapps.ui.fragment.beranda.BerandaPresenter
import dagger.Module
import dagger.Provides


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@Module
class FragmentModule {
    @Provides
    fun provideBerandaPresenter(): BerandaContract.Presenter = BerandaPresenter()
}