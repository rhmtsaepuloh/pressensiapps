package com.folkatech.pressensi.model.detailnews

import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 16,January,2020
 * Hvyz.anbiya@gmail.com
 */
data class NewsResponse(

    @SerializedName("data")
    val data: List<NewsData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("status")
    val status: Boolean
)