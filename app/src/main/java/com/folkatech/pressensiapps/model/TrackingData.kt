package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class TrackingData(
    @SerializedName("id_absensi")
    val idAbsensi: Int,
    @SerializedName("id_jaringan")
    val idJaringan: Int,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)