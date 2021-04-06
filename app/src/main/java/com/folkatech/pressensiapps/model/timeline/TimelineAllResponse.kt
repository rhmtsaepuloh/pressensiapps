package com.folkatech.pressensi.model.timeline

import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 03,December,2019
 * Hvyz.anbiya@gmail.com
 */
data class TimelineAllResponse(
    @SerializedName("status")
    val status : Boolean,
    @SerializedName("message")
    val message : String,
    @SerializedName("total")
    val total : Int,
    @SerializedName("data")
    val data : List<TimelineAllData>
)