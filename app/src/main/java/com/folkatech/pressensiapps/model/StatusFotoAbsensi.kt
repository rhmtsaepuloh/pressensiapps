package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusFotoAbsensi(
    @SerializedName("data")
    val `data`: DataFotoAbsensi,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)
/*
    "status": true,
    "message": "DataHistoryPengajuan ditemukan.",
    "data": {
        "url_file_absensi": "http://localhost/sistemabsensi/assets/images/absensi/1122_1504066188.PNG"
    }
*/
