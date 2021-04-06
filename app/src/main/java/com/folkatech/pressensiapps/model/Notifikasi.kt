package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class Notifikasi(
    @SerializedName("kategori")
    val kategori: String,
    @SerializedName("keterangan")
    val keterangan: String,
    @SerializedName("tanggal_notifikasi")
    val tanggalNotifikasi: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("id_notifikasi")
    val id_notifikasi: String,
    @SerializedName("is_read")
    val isread: String
) {
    constructor() : this("", "", "","", "", "")
}
/*
    "keterangan": "Ismunandar Berulang Tahun Hari Ini.",
    "tanggal_notifikasi": "2017-11-14",
    "kategori": "ultah"
*/
