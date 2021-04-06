package com.folkatech.pressensi.model.setuserpembayaran


import com.google.gson.annotations.SerializedName

data class SetUserPembayaranResponse(
    @SerializedName("status")
    val status: Boolean, // true
    @SerializedName("message")
    val message: String, // Success
    @SerializedName("data")
    val `data`: List<Any>
)