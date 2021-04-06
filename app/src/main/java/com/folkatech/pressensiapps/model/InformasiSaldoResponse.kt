package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class InformasiSaldoResponse(
    @SerializedName("data")
    val `data`: DataInformasiSaldo,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)
/*
    "status": true,
    "message": "DataHistoryPengajuan ditemukan",
    "data": {
        "user": {
            "nama_user": "Demo Staff Satu",
            "nip": 1223311,
            "jabatan": "Kepala Seksi",
            "tanggal_periode": "2017-11-01 - 2017-11-30",
            "departemen": "Demo Satu"
        },
        "slip_gaji": {
            "saldo_user": 1000000,
            "potongan_keterlambatan": 82500,
            "potongan_tidak_hadir": null,
            "potongan_lainnya": 80000,
            "sisa_saldo": 837500
        }
    }
*/
