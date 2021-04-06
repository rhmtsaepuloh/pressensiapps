package com.folkatech.pressensi.model.persetujuan

import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 20,October,2019
 * Hvyz.anbiya@gmail.com
 */
data class PersetujuanLemburData(
    @SerializedName("id_lembur")
    val id_lembur : String,
    @SerializedName("user_id")
    val user_id : String,
    @SerializedName("tanggal_lembur")
    val tanggal_lembur : String,
    @SerializedName("lama_lembur")
    val lama_lembur : String,
    @SerializedName("keterangan")
    val keterangan : String,
    @SerializedName("status")
    val status : String,
    @SerializedName("nip")
    val nip : String,
    @SerializedName("nama_user")
    val nama_user : String,
    @SerializedName("foto_user")
    val foto_user : String,
    @SerializedName("nama_jabatan")
    val nama_jabatan : String
){
    constructor(): this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "")
}