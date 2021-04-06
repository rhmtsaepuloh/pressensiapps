package com.folkatech.pressensi.model.loginbysocmed


import com.google.gson.annotations.SerializedName

data class LoginBySocialMediaResponse(
    @SerializedName("status")
    val status: Boolean, // true
    @SerializedName("message")
    val message: String, // Login Berhasil
    @SerializedName("data")
    val `data`: LoginBySocialMediaData
)