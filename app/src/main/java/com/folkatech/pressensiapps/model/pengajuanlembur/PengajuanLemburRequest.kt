package com.folkatech.pressensi.model.pengajuanlembur

import com.google.gson.annotations.SerializedName

/**
 * Created by Luthfan Maftuh on 7/18/2019.
 * Email luthfanmaftuh@gmail.com
 */
data class PengajuanLemburRequest(
    @SerializedName("nip")
    val nip: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("tanggal")
    val tanggal: String,
    @SerializedName("lama_jam")
    val lama_jam: String,
    @SerializedName("keterangan")
    val keterangan: String
)