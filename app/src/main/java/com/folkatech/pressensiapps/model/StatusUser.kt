package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusUser(
    @SerializedName("data")
    val dataUser: DataUser,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)