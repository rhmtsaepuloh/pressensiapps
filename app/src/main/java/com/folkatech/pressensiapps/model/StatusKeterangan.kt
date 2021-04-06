package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusKeterangan(
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("id_apel")
    val idApel: Int,
    @SerializedName("is_apel")
    val isApel: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)

/*
    "status": true,
    "message": "Anda sudah melakukan absensi.",
    "is_apel": false,
    "id_apel": null,
    "data": null
 */
