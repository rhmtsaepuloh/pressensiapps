package com.folkatech.pressensi.model

import com.google.gson.annotations.SerializedName

data class DataListPegawai(
    @SerializedName("foto_user")
    val fotoUser: String,
    @SerializedName("is_ultah")
    val isUltah: Boolean,
    @SerializedName("jabatan")
    val jabatan: String,
    @SerializedName("nama_user")
    val namaUser: String,
    @SerializedName("kehadiran")
    val statusKehadiran: String,
    @SerializedName("tgl_ultah")
    val tglUltah: String,
    @SerializedName("waktu_datang")
    val waktuDatang: String,
    @SerializedName("waktu_istirahat")
    val waktuIstirahat: String,
    @SerializedName("waktu_kembali")
    val waktuKembali: String,
    @SerializedName("waktu_pulang")
    val waktuPulang: String
) {
    constructor() : this("", false, "", "", "", "", "", "", "", "")
}
/*
    "nama_user": "Rahmat Saepuloh",
    "jabatan": "Kepala Seksi",
    "status_kehadiran": "Tidak Hadir",
    "foto_user": "http://103.219.112.43/ontime/assets//images/member-photos/default_photo.jpg",
    "tgl_ultah": "1995-12-11",
    "is_ultah": false
*/
