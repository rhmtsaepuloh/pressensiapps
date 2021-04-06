package com.folkatech.pressensi.model.userinfo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Luthfan Maftuh on 8/19/2019.
 * Email luthfanmaftuh@gmail.com
 */
data class UserInfo(val token:String?, val nama: String?, val email: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
        parcel.writeString(nama)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserInfo> {
        override fun createFromParcel(parcel: Parcel): UserInfo {
            return UserInfo(parcel)
        }

        override fun newArray(size: Int): Array<UserInfo?> {
            return arrayOfNulls(size)
        }
    }

}