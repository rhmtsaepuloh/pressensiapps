package com.folkatech.pressensi.model.persetujuan

import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 16,October,2019
 * Hvyz.anbiya@gmail.com
 */
data class PersetujuanData(
    @SerializedName("id_pengajuan")
    val id_pengajuan : String,
    @SerializedName("user_id")
    val user_id : String,
    @SerializedName("status_pengajuan")
    val status_pengajuan : String,
    @SerializedName("tanggal_awal_pengajuan")
    val tanggal_awal_pengajuan : String,
    @SerializedName("tanggal_akhir_pengajuan")
    val tanggal_akhir_pengajuan : String,
    @SerializedName("url_file_pengajuan")
    val url_file_pengajuan : String,
    @SerializedName("keterangan_pengajuan")
    val keterangan_pengajuan : String,
    @SerializedName("status_approval")
    val status_approval : String,
    @SerializedName("created_pengajuan")
    val created_pengajuan : String,
    @SerializedName("nama_user")
    val nama_user : String,
    @SerializedName("nama_status_pengajuan")
    val nama_status_pengajuan : String,
    @SerializedName("foto_user")
    val foto_user : String,
    @SerializedName("nip")
    val nip : String,
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
        "",
        "",
        "",
        "",
        "")
}