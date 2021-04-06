package com.folkatech.pressensiapps.ui.activity.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.api.ApiServiceInterface
import com.folkatech.pressensiapps.common.utils.AccountHelper
import com.folkatech.pressensiapps.common.utils.NetworkHelper
import id.zelory.compressor.Compressor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/**
 * Created by Rahmat Saefulloh on 15/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class HomePresenter : HomeContract.Presenter {
    private val TAG = "HomePresenter"

    private lateinit var view: HomeContract.View
    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private val mEmail = AccountHelper.keyEmail

    private val mToken = AccountHelper.keyToken
    private val mMacAddress = AccountHelper.keyIdMacAddress

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    @SuppressLint("HardwareIds")
    override fun getSSID() {
        if (NetworkHelper.isNetworkConnected()) {
            AccountHelper.keyIdSsid = NetworkHelper.wifiInfo().ssid
            AccountHelper.keyIdMacAddress = NetworkHelper.wifiInfo().macAddress
        } else {
            AccountHelper.keyIdSsid = ""
            AccountHelper.keyIdMacAddress = ""
        }
    }

    override fun getStatusAbsensi() {
        subscriptions.add(
            api.getStatusAbsensi(mEmail!!, mToken!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response != null) {
                        view.onSuccessGetStatusAbsensi(response)
                        if (response.data != null) {
                            AccountHelper.keyStatus = response.data.status
                            if(response.data.idAbsensi != null){
                                AccountHelper.keyIdAbsen = response.data.idAbsensi.toString()
                            }
                        }
                    } else
                        view.onError(PressensiApp.context!!.resources.getString(R.string.label_message_error_absensi))
                }, { error ->
                    Log.e(TAG, "onError: ${error.message}")
                    view.showWarningAlert(PressensiApp.context!!.resources.getString(R.string.label_message_error_koneksi))
                })
        )
    }

    override fun enterAbsen(latitude: String, longtitude: String, compressedFile: File) {
        val currentEmail = AccountHelper.keyEmail
        val currentToken = AccountHelper.keyToken
        val currentMacAddress = AccountHelper.keyIdMacAddress
        val currentSsid = AccountHelper.keyIdSsid!!.replace("\"", "")

        val reqFile = RequestBody.create("image/*".toMediaTypeOrNull(), compressedFile)
        val body = MultipartBody.Part.createFormData("userfile", compressedFile.name, reqFile)
        val email = RequestBody.create("text/plain".toMediaTypeOrNull(), currentEmail!!)
        val token = RequestBody.create("text/plain".toMediaTypeOrNull(), currentToken!!)
        val ssid = RequestBody.create("text/plain".toMediaTypeOrNull(), currentSsid)
        val macAddress = RequestBody.create("text/plain".toMediaTypeOrNull(), currentMacAddress!!)
        val lat =
            RequestBody.create("text/plain".toMediaTypeOrNull(), latitude)
        val lng =
            RequestBody.create("text/plain".toMediaTypeOrNull(), longtitude)
        subscriptions.add(
            api.absenMasuk(email, token, lat, lng, ssid, macAddress, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response != null) {
                        if (response.status) {
                            if (AccountHelper.keyStatus == "belum_masuk" || AccountHelper.keyStatus != "masuk_istirahat")
                            //AccountHelper.keyStatus = "masuk"
                                AccountHelper.keyIdAbsen = response.data.idAbsensi.toString()

                            view.onSuccessEnterAbsen(response.message)
                        } else
                            view.onErrorEnterAbsen(response.message)
                    }
                }, { error ->
                    Log.e(TAG, "onError: ${error.message}")
                    view.showWarningAlert(PressensiApp.context!!.resources.getString(R.string.label_message_error_koneksi))
                })
        )
    }

    @Synchronized
    override fun compressImageFile(context: HomeActivity, imageFile: File) {
        subscriptions.add(
            Compressor(context)
                .setMaxWidth(480)
                .setMaxHeight(640)
                .setQuality(75)
                .setCompressFormat(Bitmap.CompressFormat.JPEG)
                .compressToFileAsFlowable(imageFile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ file ->
                }, { error ->
                    Log.e(TAG, "compressImageFile: ${error.message}")
                    view.onError(PressensiApp.context!!.resources.getString(R.string.label_message_error_ambil_foto))
                })
        )
    }

    override fun getDetailUser() {
        subscriptions.add(
            api.getDataUser(mEmail!!, mToken!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.status) {
                        with(response.dataUser) {
                        }
                    }
                }, { throwable ->
                    Log.e(TAG, "getDetailUser: $throwable")
                })
        )
    }

    override fun updateNotifikasi(nip : String, token: String, id : String) {
        subscriptions.add(
            api.updateNotification(nip, token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.status) {
                        Log.d("DetailNotifInfo :", "Sukses")
                    } else
                        Log.d("DetailNotifInfo :", "Gagal")
                }, { error ->
                    Log.e(TAG, "DetailNotifInfo: ${error.message}")
                })
        )
    }
}