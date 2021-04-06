package com.folkatech.pressensiapps.ui.activity.login

import android.util.Log
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.api.ApiServiceInterface
import com.folkatech.pressensiapps.common.utils.AccountHelper
import com.folkatech.pressensiapps.common.utils.Config
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Rahmat Saefulloh on 15/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class LoginPresenter : LoginContract.Presenter {
    private val TAG = "LoginPresenter"

    private lateinit var view: LoginContract.View
    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun submitLogin(
        email: String, password: String, versionName: String, firebaseToken: String
    ) {
        subscriptions.add(
            api.login(email, password, versionName, firebaseToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response != null) {
                        if (response.status) {
                            with(AccountHelper){
                                response.dataLogin.let {
                                    keyToken = it.token
                                    keyEmail = it.email
                                    keyTrial = it.trial
                                    keyAkses = it.akses
                                }
                            }
                            view.onSuccessSubmitLogin()
                        } else
                            view.showWarningAlert(response.message)
                    } else
                        view.onError(PressensiApp.context!!.resources.getString(R.string.label_message_tidak_dapat_login))
                }, { error ->
                    Log.e(TAG, "submitLogin: ${error.message}")
                    view.showWarningAlert(PressensiApp.context!!.resources.getString(R.string.label_message_error_koneksi))
                })
        )
    }

    override fun getUserData() {
        val nip = AccountHelper.keyEmail
        val token = AccountHelper.keyToken

        subscriptions.add(
            api.getDataUser(nip!!, token!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response != null) {
                        if (response.status) {
                            AccountHelper.saveUser(response)
                            view.onSuccessGetUserData()
                        } else
                            view.showWarningAlert(response.message)
                    } else
                        view.onError(PressensiApp.context!!.resources.getString(R.string.label_message_error_server))
                }, { error ->
                    Log.e(TAG, "getUserData: ${error.message}")
                    view.showWarningAlert(PressensiApp.context!!.resources.getString(R.string.label_message_error_koneksi))
                })
        )
    }

    override fun submitLoginBySocMed(
        token: String?,
        name: String?,
        email: String?
    ) {
        subscriptions.add(
            api.submitLoginBySocialMedia(token!!, email!!, Config.versionName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response != null) {
                        if (response.status) {

                            with(AccountHelper){
                                response.data.let {
                                    keyToken = it.token
                                    keyEmail = it.nip
                                    keyTrial = it.trial
                                    keyAkses = it.akses
                                }
                            }

                            view.onSuccessSubmitLogin()
                        } else
                            view.onFailureSubmitLogin(token, name!!, email, response.message)
//                            view.showWarningAlert(response.message)
                    } else
                        view.onError(PressensiApp.context!!.resources.getString(R.string.label_message_tidak_dapat_login))
                }, { throwable ->
                    Log.e(TAG, "submitLoginBySocMed: ${throwable.message}")
                    view.showWarningAlert(PressensiApp.context!!.resources.getString(R.string.label_message_error_koneksi))
                })
        )
    }
}