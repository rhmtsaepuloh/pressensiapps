package com.folkatech.pressensi.model.lupapassword

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(

    @SerializedName("data")
    val `data`: DataForgotPassword,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean

)