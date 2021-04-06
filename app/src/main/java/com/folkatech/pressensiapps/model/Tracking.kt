package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class Tracking(
    @SerializedName("data")
    val `data`: TrackingData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)