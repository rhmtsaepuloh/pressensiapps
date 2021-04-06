package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataAbsenMasuk(
    @SerializedName("id_absensi")
    val idAbsensi: Int
)

/*
    "id_absensi": 1
*/
