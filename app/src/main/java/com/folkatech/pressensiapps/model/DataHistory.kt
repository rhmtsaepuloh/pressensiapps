package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataHistory(
    @SerializedName("kategori")
    val kategori: String,
    @SerializedName("keterangan_pengajuan")
    val keteranganPengajuan: String,
    @SerializedName("status_approval")
    val statusApproval: String,
    @SerializedName("tanggal_akhir_pengajuan")
    val tanggalAkhirPengajuan: String,
    @SerializedName("tanggal_awal_pengajuan")
    val tanggalAwalPengajuan: String,
    @SerializedName("url_file_pengajuan")
    val urlFilePengajuan: Any
) {
    constructor() : this("", "", "", "", "", "")
}
/*
    [
        {
            "tanggal_awal_pengajuan": "2019-03-28",
            "tanggal_akhir_pengajuan": "2019-03-29",
            "url_file_pengajuan": null,
            "keterangan_pengajuan": "jdjd",
            "status_approval": "Pending",
            "kategori": "Cuti"
        }
    ]
*/
