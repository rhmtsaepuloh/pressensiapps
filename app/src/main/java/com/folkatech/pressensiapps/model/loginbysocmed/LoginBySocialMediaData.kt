package com.folkatech.pressensi.model.loginbysocmed


import com.google.gson.annotations.SerializedName

data class LoginBySocialMediaData(
    @SerializedName("nip")
    val nip: String, // 895392054983
    @SerializedName("foto")
    val foto: String, // http://waktoo.com/assets/images/member-photos/895392054983_1541564064.jpg
    @SerializedName("token")
    val token: String, // 4f33b5c444a50ce66cbcd6d52fee3691
    @SerializedName("akses")
    val akses: String, // user
    @SerializedName("trial")
    val trial: String
)