package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class PengumumanResponse(
    @SerializedName("data")
    val `data`: DataPengumuman,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)