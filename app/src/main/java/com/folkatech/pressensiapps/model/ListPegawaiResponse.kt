package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class ListPegawaiResponse(
    @SerializedName("data")
    val `data`: List<DataListPegawai>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("total")
    val total: Int
)
/*
    "status": true,
    "message": "DataHistoryPengajuan ditemukan",
    "total": 7,
    "data": [
        {
            "nama_user": "Rahmat Saepuloh",
            "jabatan": "Kepala Seksi",
            "status_kehadiran": "Tidak Hadir",
            "foto_user": "http://103.219.112.43/ontime/assets//images/member-photos/default_photo.jpg",
            "tgl_ultah": "1995-12-11",
            "is_ultah": false
        },
        {
            "nama_user": "BUDI IRAWAN SUHENDAR,ST,Msi",
            "jabatan": "Kepala Seksi",
            "status_kehadiran": "Tidak Hadir",
            "foto_user": "http://103.219.112.43/ontime/assets//images/member-photos/default_photo.jpg",
            "tgl_ultah": "1980-03-27",
            "is_ultah": false
        }
    ]
*/
