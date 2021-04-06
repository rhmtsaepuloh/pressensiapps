package com.folkatech.pressensiapps.ui.activity.forgotpassword

import android.util.Log
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.api.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Rahmat Saefulloh on 15/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class ForgotPasswordPresenter : ForgotPasswordContract.Presenter {

    private val TAG = "ForgotPasswordPresenter"

    private lateinit var view: ForgotPasswordContract.View
    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ForgotPasswordContract.View) {
        this.view = view
    }

    override fun submitCheck(nip: String) {
        view.showLoading()
        subscriptions.add(
            api.getUserId(nip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    view.hideLoading()
                    if (response != null) {
                        if (response.status) {
                            view.onSuccessGetIdUser(response.data)
                        } else
                            view.showWarningAlert(response.message)
                    } else
                        view.onError("Please try again")
                }, { error ->
                    Log.e(TAG, "submitLogin: ${error.message}")
                    view.showWarningAlert(PressensiApp.context!!.resources.getString(R.string.label_message_error_koneksi))
                }))
    }

    override fun getUserData(user_id : String) {
        view.showLoading()
        subscriptions.add(
            api.getForgotPassword(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    view.hideLoading()
                    if (response != null) {
                        if (response.status) {
                            view.onSuccessResetPassword(response)
                        } else
                            view.showWarningAlert(response.message)
                    } else
                        view.onError("Please try again")
                }, { error ->
                    Log.e(TAG, "submitLogin: ${error.message}")
                    view.showWarningAlert(PressensiApp.context!!.resources.getString(R.string.label_message_error_koneksi))
                }))
    }
}