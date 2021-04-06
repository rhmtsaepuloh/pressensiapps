package com.folkatech.pressensi.model.personaldata

import android.os.Parcel
import android.os.Parcelable
import com.folkatech.pressensiapps.common.utils.ValidationHelper

/**
 * Created by Luthfan Maftuh on 8/19/2019.
 * Email luthfanmaftuh@gmail.com
 */
data class PersonalDataRequest(
    val token: String?,
    val nama: String?,
    val email: String?,
    val password: String?,
    val konfirmasiPassword: String?,
    val nomorTelepon: String?,
    val alamat: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    fun isValidPersonalData(): Int {
        return when {
            nama.isNullOrEmpty() -> 0
            email.isNullOrEmpty() -> 1
            password.isNullOrEmpty() -> 2
            konfirmasiPassword.isNullOrEmpty() -> 3
            !ValidationHelper.isValidPassword(password) -> 7
            !ValidationHelper.isValidPassword(konfirmasiPassword) -> 8
            konfirmasiPassword != password -> 4
            nomorTelepon.isNullOrEmpty() -> 5
            alamat.isNullOrEmpty() -> 6
            else -> -1
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
        parcel.writeString(nama)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(konfirmasiPassword)
        parcel.writeString(nomorTelepon)
        parcel.writeString(alamat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonalDataRequest> {
        override fun createFromParcel(parcel: Parcel): PersonalDataRequest {
            return PersonalDataRequest(parcel)
        }

        override fun newArray(size: Int): Array<PersonalDataRequest?> {
            return arrayOfNulls(size)
        }
    }

}