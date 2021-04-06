package com.folkatech.pressensiapps.di.component

import com.folkatech.pressensiapps.di.module.FragmentModule
import com.folkatech.pressensiapps.ui.fragment.AkunFragment
import com.folkatech.pressensiapps.ui.fragment.beranda.BerandaFragment
import dagger.Component


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@Component(
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(berandaFragment: BerandaFragment)

    fun inject(akunFragment: AkunFragment)
}