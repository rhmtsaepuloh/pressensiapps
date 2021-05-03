package com.folkatech.pressensiapps.ui.fragment.beranda

import android.util.Log
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.api.ApiServiceInterface
import com.folkatech.pressensiapps.common.utils.AccountHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Rahmat Saefulloh on 18/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class BerandaPresenter: BerandaContract.Presenter {
    private val TAG = "BerandaPresenter"

    private lateinit var view: BerandaContract.View
    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private val email = AccountHelper.keyEmail!!
    private val token = AccountHelper.keyToken!!

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: BerandaContract.View) {
        this.view = view
    }

    override fun getPengumuman(page: Int) {
        subscriptions.add(
            api.getPengumuman(email, token, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.status) {
                        if (response.data != null) {
                            view.onSuccessGetPengumuman(response.data.informasi)
                        } else {
                            view.onErrorGetPengumuman(response.message)
                        }
                    } else {
                        view.onErrorGetPengumuman(response.message)
                    }
                }, { error ->
                    Log.e(TAG, "onError: ${error.message}")
                    view.onErrorGetPengumuman("Gagal Memuat News")
                })
        )
    }

    override fun getAbsensiHarian(page: Int) {
        subscriptions.add(
            api.getListPegawai(token, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.status) {
                        if (response.data != null)
                            view.onSuccessGetListPegawai(response.data)
                        else
                            view.onErrorGetListPegawai()
                    } else
                        view.onErrorGetListPegawai()
                }, { error ->
                    Log.e(TAG, "getListPegawai: ${error.message}")
                    view.onErrorGetListPegawai()
                    view.showWarningAlert(WaktooApp.context!!.getString(R.string.label_message_error_koneksi))
                })
        )
    }
}