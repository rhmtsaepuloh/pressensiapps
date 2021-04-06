package com.folkatech.pressensi.model.persetujuan

import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 18,October,2019
 * Hvyz.anbiya@gmail.com
 */
data class PersetujuanStatusResponse (
    @SerializedName("status")
    val status : Boolean,
    @SerializedName("message")
    val message : String,
    @SerializedName("data")
    val data : String
)