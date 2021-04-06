package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class LogAbsensiResponse(
    @SerializedName("data")
    val `data`: DataLogAbsensi,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)
/*
    "status": true,
    "message": "DataHistoryPengajuan ditemukan",
    "data": {
        "waktu_datang": "2017-09-08 14:38:41",
        "waktu_istirahat": "2017-09-08 14:39:54",
        "waktu_kembali": "2017-09-08 14:40:14",
        "waktu_pulang": "2017-09-08 14:40:32",
        "keterangan": null,
        "url_file_absensi": "1234_1504856319.jpg",
        "foto_absensi": null
    }
*/
