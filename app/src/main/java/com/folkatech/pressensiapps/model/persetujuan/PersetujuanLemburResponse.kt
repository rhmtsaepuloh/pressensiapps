package com.folkatech.pressensi.model.persetujuan

import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 20,October,2019
 * Hvyz.anbiya@gmail.com
 */
data class PersetujuanLemburResponse(
    @SerializedName("status")
    val status : Boolean,
    @SerializedName("message")
    val message : String,
    @SerializedName("total")
    val total : Int,
    @SerializedName("data")
    val data : List<PersetujuanLemburData>
)