package com.folkatech.pressensi.model.getpricelist


import com.google.gson.annotations.SerializedName

data class PriceListResponse(
    @SerializedName("status")
    val status: Boolean, // true
    @SerializedName("message")
    val message: String, // Success
    @SerializedName("data")
    val `data`: List<PriceListData>
)