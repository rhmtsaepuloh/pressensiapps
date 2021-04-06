package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusAbsenMasuk(
    @SerializedName("data")
    val `data`: DataAbsenMasuk,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)
/*
    "status": true,
    "message": "Proses absen berhasil",
    "data": {
        "id_absensi": 1
    }
*/
