package com.folkatech.pressensiapps.ui.activity.splash

import android.os.Handler
import com.folkatech.pressensiapps.common.constant.AppConstants
import com.folkatech.pressensiapps.common.utils.AccountHelper
import com.folkatech.pressensiapps.common.utils.ValidationHelper


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class SplashPresenter : SplashContract.Presenter {
    private lateinit var view: SplashContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
    }

    override fun attach(view: SplashContract.View) {
        this.view = view
    }

    override fun initSplash() {
        Handler().postDelayed({
            //Check user token
            if (ValidationHelper.isEmpty(AccountHelper.keyToken))
                view.openLogin()
            else
                view.openHome()
        }, AppConstants.SPLASH_TIME_MILLISECOND)
    }
}