package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("kuota_cuti")
    val kuotaCuti: Int,
    @SerializedName("pemakaian")
    val pemakaian: Int
)
/*
    "kuota_cuti": 14,
    "pemakaian": 1
*/
