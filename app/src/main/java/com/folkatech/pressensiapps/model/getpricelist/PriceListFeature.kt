package com.folkatech.pressensi.model.getpricelist


import com.google.gson.annotations.SerializedName

data class PriceListFeature(
    @SerializedName("id_features")
    val idFeatures: Int, // 8
    @SerializedName("nama_features")
    val namaFeatures: String, // Feature 8
    @SerializedName("status")
    val status: Int // 1
)