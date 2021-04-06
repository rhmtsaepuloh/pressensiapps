package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusAbsensi(
    @SerializedName("data")
    val `data`: DataAbsensi,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)

/*
    "status": false,
    "message": "DataUser ditemukan",
    "data": {
        "id_absensi": 356260,
        "status": "masuk_istirahat",
        "foto_absensi": "http://waktoo.com/assets/images/absensi/5351_155fasd3478449000.jpg",
        "jam_masuk": "08:47",
        "jam_keluar": "18:39"
    }
*/
