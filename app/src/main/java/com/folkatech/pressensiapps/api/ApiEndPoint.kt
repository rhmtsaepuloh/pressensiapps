package com.folkatech.pressensiapps.api


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
object ApiEndPoint {
    const val ENDPOINT_LOGIN = "api/auth/login"
    const val ENDPOINT_DETAIL_USER = "api/user/detailUser"
    const val ENDPOINT_CEK_ABSENSI = "api/absensi/cekAbsensi"
    const val ENDPOINT_LIST_PEGAWAI = "api/user/listPegawai"

    const val ENDPOINT_GET_TOTAL_NOTIFIKASI = "api/user/getTotalNotifikasi"
    const val ENDPOINT_CEK_STATUS_ABSENSI = "api/absensi/cekStatusAbsensi"
    const val ENDPOINT_ABSEN_MASUK = "api/absensi/absenMasuk"
    const val ENDPOINT_ABSEN_KELUAR = "api/absensi/absenKeluar"
    const val ENDPOINT_IZIN_PULANG = "api/absensi/izinPulang"
    const val ENDPOINT_GET_FOTO_ABSENSI = "api/absensi/getFotoAbsensi"
    const val ENDPOINT_UPDATE_PROFILE = "api/user/updateProfile"
    const val ENDPOINT_UPDATE_PASSWORD = "api/user/updatePassword"
    const val ENDPOINT_LIST_STATUS_PENGAJUAN = "api/absensi/listStatusPengajuan"
    const val ENDPOINT_ADD_PENGAJUAN = "api/absensi/addPengajuan"
    const val ENDPOINT_GET_PIE_CHART = "api/user/DonutJamKerja"
    const val ENDPOINT_HISTORY_PENGAJUAN = "api/user/historyPengajuan"
    const val ENDPOINT_INFORMASI_SALDO = "api/user/informasiSaldo"
    const val ENDPOINT_LOG_ABSENSI = "api/user/LogAbsensi"
    const val ENDPOINT_GET_INFORMASI = "api/informasi/getInformasi"
    const val ENDPOINT_LIST_REIMBURSE = "api/reimburse/listReimburse"
    const val ENDPOINT_CLAIM_REIMBURSE = "api/reimburse/claim"
    const val ENDPOINT_TIPE_REIMBURSE = "api/reimburse/tipeReimburse"
    const val ENDPOINT_DETAIL_REIMBURSE = "api/reimburse/detailReimburse"
    const val ENDPOINT_GET_NOTIFICATION = "api/user/getNotification"
    const val ENDPOINT_UPDATE_ISREAD_NOTIFIKASI = "api/notification/changeStatusNotification"
    const val ENDPOINT_LOG = "log"
    const val ENDPOINT_CHECK_EXPIRED = "api/auth/checkExpired"
    const val ENDPOINT_PENGAJUAN_LEMBUR = "api/absensi/addPengajuanLembur"
    const val ENDPOINT_CREATE_PENGGUNA_TRIAL = "api/new/auth/informasi_pengguna"
    const val ENDPOINT_LOGIN_BY_SOCIAL_MEDIA = "api/auth/login_by"
    const val ENDPOINT_CHECK_USER = "api/new/auth/checkUser"
    const val ENDPOINT_GET_PRICE_LIST = "api/new/subscribe/getPL"
    const val ENDPOINT_SET_USER_PEMBAYARAN = "api/new/subscribe/setUser"
    const val ENDPOINT_GET_PERSETUJUAN_BY_STATUS = "api/new/approval/listPengajuan"
    const val ENDPOINT_SET_STATUS_PERSETUJUAN = "api/new/approval/approvalPengajuan"
    const val ENDPOINT_GET_PERSETUJUAN_LEMBUR_LIST = "api/new/approval/listLembur"
    const val ENDPOINT_SET_STATUS_PERSETUJUAN_LEMBUR = "api/new/approval/approvalLembur"
    const val ENDPOINT_CEK_USER_RESET_PASSWORD = "api/new/user/checkUser"
    const val ENDPOINT_SEND_REQUEST_RESET_PASSWORD = "api/new/user/sendRequest"
    const val ENDPOINT_GET_DETAIL_INFORMATION = "api/informasi/getDetailInformasi"
    const val ENDPOINT_GET_DETAIL_PENGAJUAN = "api/new/notifikasi/detailPengajuan"
    const val ENDPOINT_GET_DETAIL_PEMBATALAN = "api/new/notifikasi/detailPembatalan"
    const val ENDPOINT_UPDATE_FOTO_PROFILE = "api/user/updateFotoProfile"
    const val ENDPOINT_UPDATE_FOTO_BANNER = "api/user/updateFotobanner"
    const val ENDPOINT_NEWS = "api/new/news"
    const val ENDPOINT_GET_LIST_TIMELINE_PROFILE = "api/new/activity/listActivity"
    const val ENDPOINT_GET_LIST_TIMELINE_ALL = "api/new/activity/timeline"
    const val ENDPOINT_ADD_ACTIVITY = "api/new/activity/send"
}