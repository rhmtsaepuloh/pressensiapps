package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusPengajuan(
    @SerializedName("data")
    val `data`: List<DataPengajuan>,
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
            "id_status_pengajuan": 1,
            "nama_status_pengajuan": "Cuti"
        },
        {
            "id_status_pengajuan": 2,
            "nama_status_pengajuan": "Izin"
        },
        {
            "id_status_pengajuan": 3,
            "nama_status_pengajuan": "Sakit"
        }
    ]
*/
