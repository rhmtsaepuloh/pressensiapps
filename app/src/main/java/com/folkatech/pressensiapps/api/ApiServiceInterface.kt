package com.folkatech.pressensiapps.api

import com.folkatech.pressensi.model.*
import com.folkatech.pressensi.model.checkexpired.CheckExpiredResponse
import com.folkatech.pressensi.model.checkuser.CheckUserResponse
import com.folkatech.pressensi.model.createpengguna.CreatePenggunaResponse
import com.folkatech.pressensi.model.detailnews.NewsResponse
import com.folkatech.pressensi.model.getpricelist.PriceListResponse
import com.folkatech.pressensi.model.loginbysocmed.LoginBySocialMediaResponse
import com.folkatech.pressensi.model.lupapassword.ForgotPasswordResponse
import com.folkatech.pressensi.model.pengajuanlembur.PengajuanLemburResponse
import com.folkatech.pressensi.model.persetujuan.PersetujuanLemburResponse
import com.folkatech.pressensi.model.persetujuan.PersetujuanLemburStatusResponse
import com.folkatech.pressensi.model.persetujuan.PersetujuanResponse
import com.folkatech.pressensi.model.persetujuan.PersetujuanStatusResponse
import com.folkatech.pressensi.model.setuserpembayaran.SetUserPembayaranResponse
import com.folkatech.pressensi.model.timeline.TimelineAllResponse
import com.folkatech.pressensi.model.timelineprofile.TimelineResponse
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_ABSEN_KELUAR
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_ABSEN_MASUK
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_ADD_PENGAJUAN
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_CEK_ABSENSI
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_CEK_STATUS_ABSENSI
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_CLAIM_REIMBURSE
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_DETAIL_REIMBURSE
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_DETAIL_USER
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_GET_FOTO_ABSENSI
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_GET_INFORMASI
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_GET_NOTIFICATION
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_GET_PIE_CHART
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_GET_TOTAL_NOTIFIKASI
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_HISTORY_PENGAJUAN
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_INFORMASI_SALDO
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_IZIN_PULANG
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_LIST_PEGAWAI
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_LIST_REIMBURSE
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_LIST_STATUS_PENGAJUAN
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_LOG
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_LOGIN
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_LOG_ABSENSI
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_TIPE_REIMBURSE
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_UPDATE_FOTO_BANNER
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_UPDATE_FOTO_PROFILE
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_UPDATE_ISREAD_NOTIFIKASI
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_UPDATE_PASSWORD
import com.folkatech.pressensiapps.api.ApiEndPoint.ENDPOINT_UPDATE_PROFILE
import com.folkatech.pressensiapps.common.Common
import com.folkatech.pressensiapps.model.DetailListReimburse
import com.folkatech.pressensiapps.model.GetTipeReimburse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
interface ApiServiceInterface {
    @FormUrlEncoded
    @POST(ENDPOINT_LOGIN)
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("version") version: String,
        @Field("reg_id") reg_id: String
    ): Observable<StatusLogin>

    @FormUrlEncoded
    @POST(ENDPOINT_DETAIL_USER)
    fun getDataUser(
        @Field("nip") nip: String,
        @Field("token") token: String
    ): Observable<StatusUser>

    @FormUrlEncoded
    @POST(ENDPOINT_CEK_ABSENSI)
    fun getKeterangan(
        @Field("nip") nip: String,
        @Field("token") token: String
    ): Single<StatusKeterangan>

    @GET(ENDPOINT_LOG)
    fun startTracking(
        @Query("email") email: String,
        @Query("token") token: String,
        @Query("id_absensi") id_absensi: Int,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("ssid") ssid: String,
        @Query("macWifi") macWifi: String
    ): Single<Tracking>

    @FormUrlEncoded
    @POST(ENDPOINT_GET_NOTIFICATION)
    fun getNotification(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("page") page: Int
    ): Single<NotificationResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_UPDATE_ISREAD_NOTIFIKASI)
    fun updateNotification(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("id_notifikasi") page: String
    ): Single<NotificationResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_GET_TOTAL_NOTIFIKASI)
    fun getTotalNotifikasi(
        @Field("nip") nip: String,
        @Field("token") token: String
    ): Single<StatusNotifikasi>

    @FormUrlEncoded
    @POST(ENDPOINT_CEK_STATUS_ABSENSI)
    fun getStatusAbsensi(
        @Field("nip") nip: String,
        @Field("token") token: String
    ): Single<StatusAbsensi>

    @FormUrlEncoded
    @POST(ENDPOINT_GET_FOTO_ABSENSI)
    fun getFotoSelfie(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("id_absensi") id_absensi: Int
    ): Single<StatusFotoAbsensi>

    @Multipart
    @POST(ENDPOINT_ABSEN_MASUK)
    fun absenMasuk(
        @Part("nip") nip: RequestBody,
        @Part("token") token: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("lng") lng: RequestBody,
        @Part("ssid") ssid: RequestBody,
        @Part("macWifi") macWifi: RequestBody,
        @Part file: MultipartBody.Part
    ): Single<StatusAbsenMasuk>


    @FormUrlEncoded
    @POST(ENDPOINT_ABSEN_KELUAR)
    fun absenKeluar(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("akses") akses: Int,
        @Field("lat") lat: Double,
        @Field("lng") lng: Double,
        @Field("ssid") ssid: String,
        @Field("macWifi") macWifi: String
    ): Single<StatusAbsenKeluar>

    @FormUrlEncoded
    @POST(ENDPOINT_IZIN_PULANG)
    fun izinPulang(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("status_pengajuan") status_pengajuan: Int,
        @Field("keterangan_izin_pulang") keterangan_izin_pulang: String,
        @Field("lat") lat: Double,
        @Field("lng") lng: Double,
        @Field("ssid") ssid: String,
        @Field("macWifi") macWifi: String
    ): Single<ApiResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_UPDATE_PROFILE)
    fun updateProfile(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("telp") telp: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("email") email: String
    ): Single<StatusUser>

    @FormUrlEncoded
    @POST(ENDPOINT_UPDATE_PASSWORD)
    fun updatePassword(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("password") password: String,
        @Field("password_lama") password_lama: String,
        @Field("ulangi_password") ulangi_password: String
    ): Single<ApiResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_LIST_STATUS_PENGAJUAN)
    fun getListPengajuan(
        @Field("nip") nip: String,
        @Field("token") token: String
    ): Single<StatusPengajuan>

    @FormUrlEncoded
    @POST(ENDPOINT_ADD_PENGAJUAN)
    fun submitPengajuan(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("tipe_izin") tipe_izin: Int,
        @Field("tanggal_awal") tanggal_awal: String,
        @Field("tanggal_akhir") tanggal_akhir: String,
        @Field("keterangan") keterangan: String
    ): Single<ApiResponse>

    @Multipart
    @POST(ENDPOINT_ADD_PENGAJUAN)
    fun submitPengajuanSakit(
        @Part("nip") nip: RequestBody,
        @Part("token") token: RequestBody,
        @Part("tipe_izin") tipe_izin: RequestBody,
        @Part("tanggal_awal") tanggal_awal: RequestBody,
        @Part("tanggal_akhir") tanggal_akhir: RequestBody,
        @Part("keterangan") keterangan: RequestBody,
        @Part file: MultipartBody.Part
    ): Single<ApiResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_GET_PIE_CHART)
    fun getPieChart(
        @Field("nip") nip: String,
        @Field("token") token: String
    ): Single<PieChartResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_HISTORY_PENGAJUAN)
    fun getHistoryPengajuan(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("page") page: Int,
        @Field("kategori") kategori: String
    ): Observable<HistoryPengajuanResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_INFORMASI_SALDO)
    fun getInformasiSaldo(
        @Field("nip") nip: String,
        @Field("token") token: String
    ): Single<InformasiSaldoResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_LOG_ABSENSI)
    fun getLogAbsensi(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("dari") dari: String
    ): Single<LogAbsensiResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_LIST_PEGAWAI)
    fun getListPegawai(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("page") page: Int
    ): Single<ListPegawaiResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_GET_INFORMASI)
    fun getPengumuman(
        @Field("email") email: String,
        @Field("token") token: String,
        @Field("page") page: Int
    ): Single<PengumumanResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_LIST_REIMBURSE)
    fun getListReimburse(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("page") page: Int
    ): Single<ReimburseResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_CLAIM_REIMBURSE)
    fun claimReimburse(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("data") kategori: String
    ): Single<ApiResponse>

    @FormUrlEncoded
    @POST(ENDPOINT_TIPE_REIMBURSE)
    fun getTipeReimburse(
        @Field("nip") nip: String,
        @Field("token") token: String
    ): Single<GetTipeReimburse>

    @FormUrlEncoded
    @POST(ENDPOINT_DETAIL_REIMBURSE)
    fun getDetailReimburse(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("id_reimburse") id_reimburse: String
    ): Single<DetailListReimburse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_CHECK_EXPIRED)
    fun checkExpired(
        @Field("nip") nip: String
    ): Single<CheckExpiredResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_PENGAJUAN_LEMBUR)
    fun submitPengajuanLembur(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("tanggal") tanggal: String,
        @Field("lama_jam") lama_jam: String,
        @Field("keterangan") keterangan: String
    ): Single<PengajuanLemburResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_CREATE_PENGGUNA_TRIAL)
    fun createPenggunaTrial(
        @Field("reg_id") token: String,
        @Field("nama_user") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("ulangi_password") konfirmasiPassword: String,
        @Field("telepon") nomorTelepon: String,
        @Field("alamat") alamat: String,
        @Field("perusahaan") namaPerusahaan: String,
        @Field("telepon_perusahaan") teleponPerusahaan: String,
        @Field("jumlah_pegawai") jumlahPegawai: String,
        @Field("alamat_perusahaan") alamatPerusahaan: String
    ): Single<CreatePenggunaResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_LOGIN_BY_SOCIAL_MEDIA)
    fun submitLoginBySocialMedia(
        @Field("reg_id") token: String,
        @Field("email") email: String,
        @Field("version") versionName: String
    ): Single<LoginBySocialMediaResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_CHECK_USER)
    fun checkUser(
        @Field("email") email: String
    ): Single<CheckUserResponse>

    @GET(ApiEndPoint.ENDPOINT_GET_PRICE_LIST)
    fun getPriceList(): Single<PriceListResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_SET_USER_PEMBAYARAN)
    fun setUserPembayaran(
        @Field("nip") nip: String,
        @Field("id_price") idPrice: Int,
        @Field("jumlah_user") jumlahUser: Int,
        @Field("lama_berlangganan") lamaBerlangganan: Int,
        @Field("transaction_id") transactionId: String,
        @Field("order_id") orderId: String,
        @Field("gross_amount") grossAmount: Double,
        @Field("payment_type") paymentType: String,
        @Field("transaction_time") transactionTime: String,
        @Field("transaction_status") transactionStatus: String,
        @Field("bank_name") bankName: String,
        @Field("card_type") cardType: String
    ): Single<SetUserPembayaranResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_GET_PERSETUJUAN_BY_STATUS)
    fun getPersetujuanList(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("page") page: Int,
        @Field("status") status: String
    ): Single<PersetujuanResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_SET_STATUS_PERSETUJUAN)
    fun setPersetujuanStatus(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("id_pengajuan") id_pengajuan: String,
        @Field("status") status: String
    ): Single<PersetujuanStatusResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_GET_PERSETUJUAN_LEMBUR_LIST)
    fun getPersetujuanLemburList(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("page") page: Int
    ): Single<PersetujuanLemburResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_SET_STATUS_PERSETUJUAN_LEMBUR)
    fun setPersetujuanLemburStatus(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("id_lembur") id_lembur: String,
        @Field("status") status: String
    ): Single<PersetujuanLemburStatusResponse>

    @GET(ApiEndPoint.ENDPOINT_CEK_USER_RESET_PASSWORD)
    fun getUserId(
        @Query("username") username: String
    ): Single<ForgotPasswordResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_SEND_REQUEST_RESET_PASSWORD)
    fun getForgotPassword(
        @Field("user_id") user_id: String
    ): Single<ForgotPasswordResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_GET_DETAIL_INFORMATION)
    fun getDetailInformation(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("id_pengumuman") id_pengumuman: String
    ): Single<DetailPengumumanResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_GET_DETAIL_PENGAJUAN)
    fun getDetailPengajuan(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("id_pengajuan") id_pengajuan: String
    ): Single<DetailPengumumanResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_GET_DETAIL_PEMBATALAN)
    fun getDetailPembatalan(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("id_pembatalan") id_pembatalan: String
    ): Single<DetailPengumumanResponse>

    @Multipart
    @POST(ENDPOINT_UPDATE_FOTO_PROFILE)
    fun updateFotoProfile(
        @Part("nip") nip: RequestBody,
        @Part("token") token: RequestBody,
        @Part file: MultipartBody.Part
    ): Single<StatusUser>

    @Multipart
    @POST(ENDPOINT_UPDATE_FOTO_BANNER)
    fun updateFotoBanner(
        @Part("nip") nip: RequestBody,
        @Part("token") token: RequestBody,
        @Part file: MultipartBody.Part
    ): Single<StatusUser>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_GET_LIST_TIMELINE_PROFILE)
    fun getTimelineProfile(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("page") page: Int
    ): Single<TimelineResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_GET_LIST_TIMELINE_ALL)
    fun getTimelineAll(
        @Field("nip") nip: String,
        @Field("token") token: String,
        @Field("page") page: Int
    ): Single<TimelineAllResponse>

    @Multipart
    @POST(ApiEndPoint.ENDPOINT_ADD_ACTIVITY)
    fun addActivity(
        @Part("nip") nip: RequestBody,
        @Part("token") token: RequestBody,
        @Part("keterangan") keterangan: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("lng") lng: RequestBody,
        @Part("private") private: RequestBody,
        @Part file: MultipartBody.Part
    ): Single<ApiResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.ENDPOINT_NEWS)
    fun getNews(
        @Field("limit") limit: Int,
        @Field("page") page: Int
    ): Single<NewsResponse>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(PressensiApp.client!!)
                .baseUrl(Common.apiURL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}