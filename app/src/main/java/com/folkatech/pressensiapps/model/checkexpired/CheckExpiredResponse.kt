package com.folkatech.pressensi.model.checkexpired


import com.google.gson.annotations.SerializedName

data class CheckExpiredResponse(
    @SerializedName("status")
    val status: Boolean, // true
    @SerializedName("message")
    val message: String, // Actived
    @SerializedName("data")
    val `data`: Any // null
)