package com.folkatech.pressensi.model.detailnews

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 16,January,2020
 * Hvyz.anbiya@gmail.com
 */
data class NewsData(

    @SerializedName("judul")
    val judul: String?,
    @SerializedName("deskripsi")
    val deskripsi: String?,
    @SerializedName("link_gambar")
    val link_gambar: String?,
    @SerializedName("sumber")
    val sumber: String?,
    @SerializedName("terbit")
    val terbit:String?,
    @SerializedName("kategori")
    val kategori:String?,
    @SerializedName("country")
    val country:String?,
    @SerializedName("region")
    val region:String?,
    @SerializedName("geolocation")
    val geolocation:String?,
    @SerializedName("url")
    val url:String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(judul)
        parcel.writeString(deskripsi)
        parcel.writeString(link_gambar)
        parcel.writeString(sumber)
        parcel.writeString(terbit)
        parcel.writeString(kategori)
        parcel.writeString(country)
        parcel.writeString(region)
        parcel.writeString(geolocation)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsData> {
        override fun createFromParcel(parcel: Parcel): NewsData {
            return NewsData(parcel)
        }

        override fun newArray(size: Int): Array<NewsData?> {
            return arrayOfNulls(size)
        }
    }
}