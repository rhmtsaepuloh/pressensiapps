package com.folkatech.pressensi.model.lupapassword

import com.google.gson.annotations.SerializedName

data class DataForgotPassword(
    @SerializedName("user_id")
    val userid: String
)