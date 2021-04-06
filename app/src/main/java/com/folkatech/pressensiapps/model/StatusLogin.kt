package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class StatusLogin(
    @SerializedName("data")
    val dataLogin: DataLogin,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean


)

/*
    "status": true,
    "message": "StatusLogin Berhasil",
    "data": {
        "nip": 19912421,
        "foto": "http://waktoo.com/assets/images/member-photos/19912421_1553176441.jpg",
        "token": "9033d784f529f142f1as1bb34d33a7e4c800b",
        "akses": "user"
    }
*/

