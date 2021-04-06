package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("Error")
    val error: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)