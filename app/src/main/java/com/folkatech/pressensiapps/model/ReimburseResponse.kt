package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class ReimburseResponse(
    @SerializedName("data")
    val `data`: List<DataReimburse>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("total")
    val total: String
)