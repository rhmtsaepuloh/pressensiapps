package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DetailPengumumanData(

	@field:SerializedName("is_all")
	val isAll: String,

	@field:SerializedName("penerima")
	val penerima: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("deskripsi_pengumuman")
	val deskripsiPengumuman: String,

	@field:SerializedName("tanggal")
	val tanggal: String,

	@field:SerializedName("id_unit")
	val idUnit: String,

	@field:SerializedName("tipe_pengumuman")
	val tipePengumuman: String,

	@field:SerializedName("judul_pengumuman")
	val judulPengumuman: String,

	@field:SerializedName("url_file_pengumuman")
	val urlFilePengumuman: String
)