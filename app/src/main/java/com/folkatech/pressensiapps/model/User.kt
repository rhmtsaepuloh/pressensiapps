package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("departemen")
    val departemen: String,
    @SerializedName("jabatan")
    val jabatan: String,
    @SerializedName("nama_user")
    val namaUser: String,
    @SerializedName("nip")
    val nip: Long,
    @SerializedName("tanggal_periode")
    val tanggalPeriode: String
)
/*
    "nama_user": "Demo Staff Satu",
    "nip": 1223311,
    "jabatan": "Kepala Seksi",
    "tanggal_periode": "2017-11-01 - 2017-11-30",
    "departemen": "Demo Satu"
*/
