package com.folkatech.pressensiapps.ui.activity.login

import com.folkatech.pressensiapps.common.base.BaseContract
import com.folkatech.pressensiapps.di.scope.PerActivity


/**
 * Created by Rahmat Saefulloh on 13/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class LoginContract {
    interface View : BaseContract.View {

        /**
         * Determines if Nip field is valid
         */
        var isEmailFieldValid: Boolean

        /**
         * Determines if Password field is valid
         */
        var isPasswordFieldValid: Boolean

        /**
         * Listener method to validate user input on Nip field
         */
        fun onNipFieldValidationListener()

        /**
         * Listener method to validate user input on Password field
         */
        fun onPasswordFieldValidationListener()

        /**
         * Get user dataUser when submit login to the server has successful
         */
        fun onSuccessSubmitLogin()

        /**
         * Open home when get dataUser user from the server has successful
         */
        fun onSuccessGetUserData()

        /**
         * Callback method that triggered when submits login forms data to the server has failed
         * @param token The token string
         * @param name The name string
         * @param email The email string
         * @param message The error message string
         */
        fun onFailureSubmitLogin(token: String, name: String, email: String?, message: String)
    }

    @PerActivity
    interface Presenter : BaseContract.Presenter<LoginContract.View> {

        /**
         * Submit login form to server
         * @param nip The user nip string
         * @param password The user password string
         * @param versionName The build version name string
         * @param firebaseToken The firebase token string
         */
        fun submitLogin(
            nip: String,
            password: String,
            versionName: String,
            firebaseToken: String
        )

        /**
         * Get user dataUser from server
         */
        fun getUserData()

        /**
         * Submit login by Social Media to server
         * @param token The access token string
         * @param name The email string
         * */
        fun submitLoginBySocMed(token: String?, name: String?, email: String?)

    }
}