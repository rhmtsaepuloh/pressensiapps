package com.folkatech.pressensi.model.createpengguna


import com.google.gson.annotations.SerializedName

data class CreatePenggunaData(
    @SerializedName("nip")
    val nip: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("akses")
    val akses: String
)

/*
* {
        "nip": "0895392054983",
        "foto": "http://waktoo.com/assets/images/member-photos/default_photo.jpg",
        "token": "86c40123c9de9f80281bdbf94eb6f4ea",
        "akses": "admin"
    }
*/