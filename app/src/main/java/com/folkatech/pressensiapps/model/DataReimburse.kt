package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataReimburse(
    @SerializedName("acc")
    val acc: String,
    @SerializedName("id_reimburse")
    val idReimburse: String,
    @SerializedName("jumlah")
    val jumlah: String,
    @SerializedName("tanggal")
    val tanggal: String,
    @SerializedName("total")
    val total: String
) {
    constructor() : this("", "", "", "", "")
}