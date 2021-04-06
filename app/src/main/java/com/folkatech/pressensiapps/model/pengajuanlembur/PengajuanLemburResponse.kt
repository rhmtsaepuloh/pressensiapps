package com.folkatech.pressensi.model.pengajuanlembur


import com.google.gson.annotations.SerializedName

data class PengajuanLemburResponse(
    @SerializedName("status")
    val status: Boolean, // true
    @SerializedName("message")
    val message: String, // Success
    @SerializedName("data")
    val `data`: Any // null
)