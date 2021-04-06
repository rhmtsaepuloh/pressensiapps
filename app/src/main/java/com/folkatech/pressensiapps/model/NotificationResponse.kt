package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("data")
    val `data`: NotificationData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)

/*
    "status": true,
    "message": "DataHistoryPengajuan ditemukan",
    "data": {
    "notifikasi": [
            {
                "keterangan": "Ismunandar Berulang Tahun Hari Ini.",
                "tanggal_notifikasi": "2017-11-14",
                "kategori": "ultah"
            },
            {
                "keterangan": "Apel Tes Apel di Lapangan Gasibu pada hari Rabu",
                "tanggal_notifikasi": "2017-11-14",
                "kategori": "apel"
            }
        ]
    }
*/

