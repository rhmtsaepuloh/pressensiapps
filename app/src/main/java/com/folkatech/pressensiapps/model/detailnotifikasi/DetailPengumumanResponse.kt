package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DetailPengumumanResponse(

	@field:SerializedName("data")
	val `data`: DetailPengumumanData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)