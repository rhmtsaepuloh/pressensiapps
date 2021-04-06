package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataUser(

    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("banner")
    val banner: String,
    @SerializedName("jabatan")
    val jabatan: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("nip")
    val nip: String,
    @SerializedName("telp")
    val telp: String,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("is_trial")
    val isTrial: String
)