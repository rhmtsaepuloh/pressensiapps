package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataHistoryPengajuan(
    @SerializedName("data")
    val `data`: List<DataHistory>,
    @SerializedName("detail")
    val detail: Detail
)
/*
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
