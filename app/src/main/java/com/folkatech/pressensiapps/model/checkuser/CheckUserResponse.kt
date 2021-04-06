package com.folkatech.pressensi.model.checkuser


import com.google.gson.annotations.SerializedName

data class CheckUserResponse(
    @SerializedName("status")
    val status: Boolean, // true
    @SerializedName("message")
    val message: String, // User ditemukan
    @SerializedName("data")
    val `data`: Any // null
)