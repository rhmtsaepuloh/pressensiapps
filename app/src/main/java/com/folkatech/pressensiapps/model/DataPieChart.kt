package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataPieChart(
    @SerializedName("x")
    val x: String,
    @SerializedName("y")
    val y: Int
)
/*
    "data": [
        {
            "x": "Tepat waktu",
            "y": 2
        },
        {
            "x": "Tidak Hadir",
            "y": 14
        },
        {
            "x": "Terlambat",
            "y": 0
        }
    ]
*/
