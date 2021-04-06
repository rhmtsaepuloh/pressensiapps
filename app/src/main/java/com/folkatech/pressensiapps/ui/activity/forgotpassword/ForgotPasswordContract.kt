package com.folkatech.pressensiapps.ui.activity.forgotpassword

import com.folkatech.pressensi.model.lupapassword.DataForgotPassword
import com.folkatech.pressensi.model.lupapassword.ForgotPasswordResponse
import com.folkatech.pressensiapps.common.base.BaseContract
import com.folkatech.pressensiapps.di.scope.PerActivity


/**
 * Created by Rahmat Saefulloh on 15/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class ForgotPasswordContract {

    interface View: BaseContract.View{

        /**
         * Determines if Email field is valid
         */
        var isNipFieldValid: Boolean

        /**
         * Listener method to validate user input on Nip field
         */
        fun onEmailFieldValidationListener()
        fun showLoading()
        fun hideLoading()
        fun onSuccessGetIdUser(response: DataForgotPassword)
        fun onSuccessResetPassword (response : ForgotPasswordResponse)

    }

    @PerActivity
    interface Presenter:BaseContract.Presenter<View>{

        fun submitCheck(nip : String)
        fun getUserData(user_id : String)

    }
}