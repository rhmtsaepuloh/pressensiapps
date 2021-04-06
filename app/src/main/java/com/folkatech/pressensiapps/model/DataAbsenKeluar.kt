package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataAbsenKeluar(
    @SerializedName("id_absensi")
    val idAbsensi: Int
)