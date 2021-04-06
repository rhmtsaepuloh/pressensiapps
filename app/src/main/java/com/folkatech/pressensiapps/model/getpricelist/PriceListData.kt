package com.folkatech.pressensi.model.getpricelist


import com.google.gson.annotations.SerializedName

data class PriceListData(
    @SerializedName("id_price")
    val idPrice: Int, // 2
    @SerializedName("nama_paket")
    val namaPaket: String, // Premium
    @SerializedName("harga")
    val harga: Int, // 30000
    @SerializedName("features")
    val features: List<PriceListFeature>
)