package com.folkatech.pressensi.model.createpengguna


import com.google.gson.annotations.SerializedName

data class CreatePenggunaResponse(
    @SerializedName("status")
    val status: Boolean, // true
    @SerializedName("message")
    val message: String, // Data Personal & Perusahaan Berhasil disimpan.
    @SerializedName("data")
    val `data`: CreatePenggunaData
)