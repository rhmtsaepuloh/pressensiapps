package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusNotifikasi(
    @SerializedName("data")
    val `data`: DataNotifikasi,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)

/*
    "status": true,
    "message": "DataUser ditemukan",
    "data": {
        "total": 0
    }
*/
