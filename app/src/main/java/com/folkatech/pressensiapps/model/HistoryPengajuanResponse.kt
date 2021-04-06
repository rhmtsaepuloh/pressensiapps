package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class HistoryPengajuanResponse(
    @SerializedName("data")
    val `data`: DataHistoryPengajuan,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("total")
    val total: Int
)
/*
    "status": true,
    "message": "Data ditemukan",
    "total": 1,
    "data": {
        "data": [
        {
            "tanggal_awal_pengajuan": "2019-03-28",
            "tanggal_akhir_pengajuan": "2019-03-29",
            "url_file_pengajuan": null,
            "keterangan_pengajuan": "jdjd",
            "status_approval": "Pending",
            "kategori": "Cuti"
        }
        ],
        "detail": {
            "kuota_cuti": 14,
            "pemakaian": 1
        }
    }
*/
