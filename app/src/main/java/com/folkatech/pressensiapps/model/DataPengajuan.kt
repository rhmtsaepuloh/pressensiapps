package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataPengajuan(
    @SerializedName("id_status_pengajuan")
    val idStatusPengajuan: Int,
    @SerializedName("nama_status_pengajuan")
    val namaStatusPengajuan: String
)

/*
    [
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
