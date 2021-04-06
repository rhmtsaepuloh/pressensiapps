package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("akses")
    val akses: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("nip")
    val nip: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("trial")
    val trial: String
)