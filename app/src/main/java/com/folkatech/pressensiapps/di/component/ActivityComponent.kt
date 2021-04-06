package com.folkatech.pressensiapps.di.component

import com.folkatech.pressensiapps.di.module.ActivityModule
import com.folkatech.pressensiapps.di.scope.PerActivity
import com.folkatech.pressensiapps.ui.activity.forgotpassword.ForgotPasswordActivity
import com.folkatech.pressensiapps.ui.activity.home.HomeActivity
import com.folkatech.pressensiapps.ui.activity.login.LoginActivity
import com.folkatech.pressensiapps.ui.activity.splash.SplashActivity
import dagger.Component


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(forgotPasswordActivity: ForgotPasswordActivity)
    fun inject(homeActivity: HomeActivity)
}