package com.folkatech.pressensiapps.common.utils

import android.text.TextUtils
import com.folkatech.pressensi.model.StatusUser
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.common.constant.AppConstants.KEY_REG_ID
import com.folkatech.pressensiapps.model.AjukanReimburseRequest
import com.folkatech.pressensiapps.model.GetTipeReimburse
import com.google.gson.Gson


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
object AccountHelper {
    val KEY_TOKEN = "Pref.KeyToken"
    val KEY_EMAIL = "Pref.KeyNIP"
    val KEY_ID_ABSEN = "Pref.KeyIDABSEN"
    val KEY_ID_SSID = "Pref.KeySSID"
    val KEY_ID_MAC_ADDRESS = "Pref.KeyMacAddress"
    val KEY_ID_UNIQID = "Pref.KeyUniqID"
    val KEY_PROFILE_USER = "Pref.StatusUser"
    val KEY_KETERANGAN = "Pref.Keterangan"
    val KEY_SELFIE = "Pref.Selfie"
    val INVALID_SESSION = "Pref.InvalidSession"
    val KEY_APEL = "Pref.Apel"
    val KEY_STATUS = "Pref.Status"
    val KEY_TRIAL = "Pref.KeyTrial"
    val KEY_AKSES = "Pref.KeyAdmin"
    val KEY_STATE_FRAGMENT = "0"

    val KEY_PROFILE_REIMBURSE = "Pref.Reimburse"
    val KEY_TIPE_REIMBURSE = "Pref.TipeReimburse"
    val KEY_IMAGE_URI = "Pref.KeyImageUri"

    val KEY_LATITUDE = "Pref.Latitude"
    val KEY_LONGTITUDE = "Pref.Longtitude"
    val KEY_ADDRESS = "Pref.Address"

    val KEY_KATEGORI = "Pref.Kategori"
    val KEY_IDKATEGORI = "Pref.IdKategori"
    val KEY_ISREAD = "Pref.IsRead"

    fun getUser(): StatusUser? {
        val s = PressensiApp.sharedPreferences.getString(KEY_PROFILE_USER, "")
        return if (TextUtils.isEmpty(s)) {
            null
        } else {
            Gson().fromJson<StatusUser>(s, StatusUser::class.java)
        }
    }

    fun saveUser(statusUser: StatusUser) {
        PressensiApp.sharedPreferences.edit().putString(KEY_PROFILE_USER, Gson().toJson(statusUser))
            .apply()
    }

    var keyKategori: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_KATEGORI, null)
        set(value) = PressensiApp.sharedPreferences.edit().putString(KEY_KATEGORI, value).apply()

    var keyIdKategori: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_IDKATEGORI, null)
        set(value) = PressensiApp.sharedPreferences.edit().putString(KEY_IDKATEGORI, value).apply()

    var keyIsRead: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_ISREAD, null)
        set(value) = PressensiApp.sharedPreferences.edit().putString(KEY_ISREAD, value).apply()

    var keyAddress: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_ADDRESS, "")
        set(value) = PressensiApp.sharedPreferences.edit().putString(KEY_ADDRESS, value).apply()

    var keyLatitude: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_LATITUDE, "")
        set(value) = PressensiApp.sharedPreferences.edit().putString(KEY_LATITUDE, value).apply()

    var keyLongtitude: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_LONGTITUDE, "")
        set(value) = PressensiApp.sharedPreferences.edit().putString(KEY_LONGTITUDE, value).apply()

    var keyImageUri: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_IMAGE_URI, "")
        set(stringUri) = PressensiApp.sharedPreferences.edit().putString(
            KEY_IMAGE_URI,
            stringUri
        ).apply()

    var keyStateFragment: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_STATE_FRAGMENT, "0")
        set(value) = PressensiApp.sharedPreferences.edit().putString(KEY_STATE_FRAGMENT, value).apply()

    var keyAkses: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_AKSES, "")
        set(value) = PressensiApp.sharedPreferences.edit().putString(KEY_AKSES, value).apply()

    var keyToken: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_TOKEN, "")
        set(token) = PressensiApp.sharedPreferences.edit().putString(KEY_TOKEN, token).apply()

    var keyEmail: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_EMAIL, null)
        set(email) = PressensiApp.sharedPreferences.edit().putString(KEY_EMAIL, email).apply()

    var keyTrial: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_TRIAL, "")
        set(trial) = PressensiApp.sharedPreferences.edit().putString(KEY_TRIAL, trial).apply()

    var keyIdSsid: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_ID_SSID, null)
        set(idSsid) = PressensiApp.sharedPreferences.edit().putString(KEY_ID_SSID, idSsid).apply()

    var keyIdMacAddress: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_ID_MAC_ADDRESS, "")
        set(idMac) = PressensiApp.sharedPreferences.edit().putString(KEY_ID_MAC_ADDRESS, idMac).apply()

    var keyApel: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_APEL, null)
        set(idApel) = PressensiApp.sharedPreferences.edit().putString(KEY_APEL, idApel).apply()

    var keyStatus: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_STATUS, null)
        set(idStatus) = PressensiApp.sharedPreferences.edit().putString(KEY_STATUS, idStatus).apply()

    var keyIdAbsen: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_ID_ABSEN, null)
        set(idAbsen) = PressensiApp.sharedPreferences.edit().putString(KEY_ID_ABSEN, idAbsen).apply()

    var keyRegId: String?
        get() = PressensiApp.sharedPreferences.getString(KEY_REG_ID, null)
        set(regId) = PressensiApp.sharedPreferences.edit().putString(KEY_REG_ID, regId).apply()

    var formReimburse: AjukanReimburseRequest?
        get() {
            val json = PressensiApp.sharedPreferences.getString(KEY_PROFILE_REIMBURSE, null)
            return if (TextUtils.isEmpty(json)) null else Gson().fromJson(
                json, AjukanReimburseRequest::class.java
            )
        }
        set(value) {
            val userJson = Gson().toJson(value)
            PressensiApp.sharedPreferences.edit().putString(KEY_PROFILE_REIMBURSE, userJson).apply()
        }

    var tipeReimburse: GetTipeReimburse?
        get() {
            val json = PressensiApp.sharedPreferences.getString(KEY_TIPE_REIMBURSE, null)
            return if (TextUtils.isEmpty(json)) null else Gson().fromJson(
                json, GetTipeReimburse::class.java
            )
        }
        set(value) {
            val userJson = Gson().toJson(value)
            PressensiApp.sharedPreferences.edit().putString(KEY_TIPE_REIMBURSE, userJson).apply()
        }

    fun clearUserData() {
        PressensiApp.sharedPreferences.edit().putString(KEY_PROFILE_USER, "").apply()
        PressensiApp.sharedPreferences.edit().putString(KEY_TOKEN, "").apply()
    }

    fun clearImageUriData() {
        PressensiApp.sharedPreferences.edit().putString(KEY_IMAGE_URI, "").apply()
    }
}