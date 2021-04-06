package com.folkatech.pressensiapps.ui.activity.home

import com.folkatech.pressensi.model.StatusAbsensi
import com.folkatech.pressensiapps.common.base.BaseContract
import java.io.File


/**
 * Created by Rahmat Saefulloh on 15/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class HomeContract {
    interface View : BaseContract.View {

        fun initMenuPersetujuan()
        /**
         * Set up absen button visibility
         * @param btnMulaiVisibility The button mulai visibility type
         * @param btnKeluarVisibility The button keluar visibility type
         */
        fun setupAbsenButton(btnMulaiVisibility: Int, btnKeluarVisibility: Int)
        fun onSuccessEnterAbsen(message: String)
        fun onSuccessGetStatusAbsensi(response: StatusAbsensi)
        fun onErrorEnterAbsen(message: String)
        fun onFakeGPSDetected()
    }

    interface Presenter : BaseContract.Presenter<View> {

        /**
         * Get SSID then save into preferences
         */
        fun getSSID()

        /**
         * Get status absensi from the server
         */
        fun getStatusAbsensi()

        /**
         * To mulai absen
         * @param compressedFile The compressed image file
         */

        fun enterAbsen(latitude: String,
                       longtitude: String,
                       compressedFile: File
        )

        /**
         * To exit from absensi status
         * @param akses The akses int
         * @param gpsTracker The gpsTracker object
         */

        /** Compress captured image imageFile from camera */
        fun compressImageFile(context: HomeActivity, imageFile: File)

        /**
         * Get user data from the server
         */
        fun getDetailUser()

        fun updateNotifikasi(nip : String, token: String, id : String)


    }
}