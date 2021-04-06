package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class NotificationData(
    @SerializedName("notifikasi")
    val notifikasi: List<Notifikasi>
)
/*
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
*/
