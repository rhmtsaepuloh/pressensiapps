package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class Informasi(
    @SerializedName("deskripsi_pengumuman")
    var deskripsiPengumuman: String,
    @SerializedName("foto_channel")
    var fotoChannel: String,
    @SerializedName("judul_pengumuman")
    var judulPengumuman: String,
    @SerializedName("tanggal")
    var tanggal: String,
    @SerializedName("url_file_pengumuman")
    var urlFilePengumuman: String,
    @SerializedName("id_pengumuman")
    var idPengumuman: String
) {
    constructor() : this("","", "", "", "", "")


}