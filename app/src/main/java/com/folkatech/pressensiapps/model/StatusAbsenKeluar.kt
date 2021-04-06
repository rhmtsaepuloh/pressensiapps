package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusAbsenKeluar(
    @SerializedName("data")
    val `data`: DataAbsenKeluar,
    @SerializedName("is_pulang")
    val isPulang: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)