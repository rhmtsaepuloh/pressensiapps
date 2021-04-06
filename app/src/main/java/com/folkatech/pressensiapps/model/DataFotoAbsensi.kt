package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataFotoAbsensi(
    @SerializedName("url_file_absensi")
    val urlFileAbsensi: String
)

/*
    "url_file_absensi": "http://localhost/sistemabsensi/assets/images/absensi/1122_1504066188.PNG"
*/
