package com.folkatech.pressensi.model.timelineprofile

import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 03,December,2019
 * Hvyz.anbiya@gmail.com
 */
data class TimelineData(
    @SerializedName("id_daily_activity")
    val id_daily_activity : String,
    @SerializedName("user_id")
    val user_id : String,
    @SerializedName("keterangan")
    val keterangan : String,
    @SerializedName("lat")
    val lat : String,
    @SerializedName("lng")
    val lng : String,
    @SerializedName("file_activity")
    val file_activity : String,
    @SerializedName("created_at")
    val created_at : String,
    @SerializedName("private")
    val private : String
)