package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataAbsensi(
    @SerializedName("foto_absensi")
    val fotoAbsensi: String,
    @SerializedName("id_absensi")
    val idAbsensi: Int,
    @SerializedName("jam_keluar")
    val jamKeluar: String,
    @SerializedName("jam_masuk")
    val jamMasuk: String,
    @SerializedName("status")
    val status: String
)

/*
    "id_absensi": 356260,
    "status": "masuk_istirahat",
    "foto_absensi": "http://waktoo.com/assets/images/absensi/5351_155fasd3478449000.jpg",
    "jam_masuk": "08:47",
    "jam_keluar": "18:39"
*/
