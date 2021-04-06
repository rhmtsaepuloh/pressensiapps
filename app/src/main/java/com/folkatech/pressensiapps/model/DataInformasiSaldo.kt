package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataInformasiSaldo(
    @SerializedName("slip_gaji")
    val slipGaji: SlipGaji,
    @SerializedName("user")
    val user: User
)

/*
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
*/
