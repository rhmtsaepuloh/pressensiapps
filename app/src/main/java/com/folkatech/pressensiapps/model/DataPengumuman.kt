package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataPengumuman(
    @SerializedName("informasi")
    val informasi: List<Informasi>,
    @SerializedName("total")
    val total: Int
)