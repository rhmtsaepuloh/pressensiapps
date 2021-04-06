package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataLogAbsensi(
    @SerializedName("foto_absensi")
    val fotoAbsensi: String,
    @SerializedName("keterangan")
    val keterangan: String,
    @SerializedName("url_file_absensi")
    val urlFileAbsensi: String,
    @SerializedName("waktu_datang")
    val waktuDatang: String,
    @SerializedName("waktu_istirahat")
    val waktuIstirahat: String,
    @SerializedName("waktu_kembali")
    val waktuKembali: String,
    @SerializedName("waktu_pulang")
    val waktuPulang: String,
    @SerializedName("tanggal")
    val tanggal: String
)
/*
    "waktu_datang": "2017-09-08 14:38:41",
    "waktu_istirahat": "2017-09-08 14:39:54",
    "waktu_kembali": "2017-09-08 14:40:14",
    "waktu_pulang": "2017-09-08 14:40:32",
    "keterangan": null,
    "url_file_absensi": "1234_1504856319.jpg",
    "foto_absensi": null
*/
