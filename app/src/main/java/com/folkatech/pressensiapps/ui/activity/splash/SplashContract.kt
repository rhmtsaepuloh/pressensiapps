package com.folkatech.pressensiapps.ui.activity.splash

import com.folkatech.pressensiapps.common.base.BaseContract
import com.folkatech.pressensiapps.di.scope.PerActivity


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class SplashContract {
    interface View : BaseContract.View {
        /**
         * Init all permissions
         */
        fun initPermission()

        /**
         * Open home screen if user has token
         */
        fun openHome()

        /**
         * Open login if user doesn't has token
         */
        fun openLogin()

        /**
         * To show permission alert dialog if user denied all permissions
         */
        fun showPermissionDialog()
    }

    @PerActivity
    interface Presenter : BaseContract.Presenter<SplashContract.View> {
        /**
         * Init splash screen
         */
        fun initSplash()

    }
}