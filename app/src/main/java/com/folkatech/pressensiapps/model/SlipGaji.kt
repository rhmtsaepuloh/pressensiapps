package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class SlipGaji(
    @SerializedName("potongan_keterlambatan")
    val potonganKeterlambatan: Int,
    @SerializedName("potongan_lainnya")
    val potonganLainnya: Int,
    @SerializedName("potongan_tidak_hadir")
    val potonganTidakHadir: Any,
    @SerializedName("saldo_user")
    val saldoUser: Int,
    @SerializedName("sisa_saldo")
    val sisaSaldo: Int
)
/*
    "saldo_user": 1000000,
    "potongan_keterlambatan": 82500,
    "potongan_tidak_hadir": null,
    "potongan_lainnya": 80000,
    "sisa_saldo": 837500
*/
