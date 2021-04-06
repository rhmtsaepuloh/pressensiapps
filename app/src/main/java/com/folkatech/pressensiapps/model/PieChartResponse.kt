package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class PieChartResponse(
    @SerializedName("data")
    val `data`: List<DataPieChart>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)
/*
    "status": true,
    "message": "DataHistoryPengajuan ditemukan",
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
