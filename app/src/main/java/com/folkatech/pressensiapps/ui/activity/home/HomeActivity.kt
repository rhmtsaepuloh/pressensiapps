package com.folkatech.pressensiapps.ui.activity.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PorterDuff
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.folkatech.pressensi.model.StatusAbsensi
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.Common
import com.folkatech.pressensiapps.common.base.BaseActivity
import com.folkatech.pressensiapps.common.constant.AppConstants
import com.folkatech.pressensiapps.common.constant.AppConstants.LOCATION_INTENT
import com.folkatech.pressensiapps.common.constant.AppConstants.LOCATION_PERMISSION_REQUEST_CODE
import com.folkatech.pressensiapps.common.constant.AppConstants.REQUEST_CODE_IMAGE_CAPTURE
import com.folkatech.pressensiapps.common.utils.AccountHelper
import com.folkatech.pressensiapps.common.utils.NotificationUtils
import com.folkatech.pressensiapps.services.GPSTracker
import com.folkatech.pressensiapps.services.LocationUpdateService
import com.folkatech.pressensiapps.services.MyReceiver
import com.folkatech.pressensiapps.ui.activity.absen.AbsenActivity
import com.folkatech.pressensiapps.ui.fragment.beranda.adapter.PengumumanAdapter
import com.folkatech.pressensiapps.ui.fragment.AkunFragment
import com.folkatech.pressensiapps.ui.fragment.beranda.BerandaFragment
import com.github.javiersantos.appupdater.AppUpdater
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_bottom_menu.*
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View, View.OnClickListener {
    private val TAG = "HomeActivity"

    @Inject
    lateinit var presenter: HomeContract.Presenter

    @Inject
    lateinit var appUpdater: AppUpdater

    @Inject
    lateinit var broadcastReceiver: BroadcastReceiver

    @Inject
    lateinit var myReceiver: MyReceiver

    lateinit var gpsTracker: GPSTracker

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var berandaFragment: BerandaFragment
    private lateinit var akunFragment: AkunFragment

    private lateinit var dialog: Dialog
    private lateinit var geocoder: Geocoder

    internal var mFusedLocationClient: FusedLocationProviderClient? = null
    internal lateinit var mLastLocation: Location

    var address: List<Address> = emptyList()
    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false
    private val linearLayoutManager = LinearLayoutManager(this)
    private var pengumumanAdapter: PengumumanAdapter = PengumumanAdapter()

    private val btnId = intArrayOf(
        R.id.btn_home_04,
        R.id.btn_akun_04
    )

    private val tvId = intArrayOf(
        R.id.tv_beranda_04,
        R.id.tv_akun_04,
    )

    private val PAGE_SIZE = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        super.onCreate(savedInstanceState)
        gpsTracker = GPSTracker(this, this)
        instance = this
        context = this
        activityComponent.inject(this)
        presenter.attach(this)
        appUpdater.start()
        setContentView(R.layout.activity_home)
    }

    override fun onInit() {
        initFragment()
        setupAbsenButton(View.VISIBLE, View.GONE)
        menuOnclick()

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        geocoder = Geocoder(this, Locale.getDefault())

        AccountHelper.keyLatitude = null
        AccountHelper.keyLongtitude = null
        AccountHelper.keyAddress = null

        setUpMap()

        presenter.getSSID()
        presenter.getStatusAbsensi()

        cekNotifikasi()

        btn_mulai_absen_04.setOnClickListener(this)
        btn_keluar_absen_04.setOnClickListener(this)
    }

    override fun onSuccessGetStatusAbsensi(response: StatusAbsensi) {
        if (response.data != null) {
            AccountHelper.keyStatus = response.data.status
            when (AccountHelper.keyStatus) {
                "belum_masuk" -> setupAbsenButton(View.VISIBLE, View.GONE)
                "masuk_istirahat" -> setupAbsenButton(View.VISIBLE, View.GONE)
                "masuk" -> setupAbsenButton(View.GONE, View.VISIBLE)
                "keluar" -> setupAbsenButton(View.GONE, View.VISIBLE)
                else -> setupAbsenButton(View.GONE, View.GONE)
            }

        }
        if (response.status) {
            setupAbsenButton(View.GONE, View.VISIBLE)
        }
        else {
            redirectToLoginIfMust(response.message)

            setupAbsenButton(View.VISIBLE, View.GONE)

            if(AccountHelper.keyStatus == "keluar"){
                setupAbsenButton(View.GONE, View.VISIBLE)
            }
        }
    }

    override fun onSuccessEnterAbsen(message: String) {
        Common.dismissProgressDialog()

        setupAbsenButton(View.GONE, View.VISIBLE)
        presenter.getStatusAbsensi()

        deleteImageFile()

        val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
        dialog.apply {
            titleText = "Pemberitahuan!"
            contentText = message
            confirmText = "OK"
            setConfirmClickListener {
                val intent = Intent(this@HomeActivity, AbsenActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
            }
            setCancelable(false)
            setCanceledOnTouchOutside(false)

        }
        dialog.show()

    }

    override fun onErrorEnterAbsen(message: String) {
        Common.dismissProgressDialog()

        Common.showPopUpDialog(
            this, resources.getString(R.string.title_dialog_warning),
            message, SweetAlertDialog.WARNING_TYPE
        )
    }

    // To delete stored image camera from internal storage
    private fun deleteImageFile() {
        contentResolver.delete(Uri.parse(AccountHelper.keyImageUri), null, null)
        AccountHelper.clearImageUriData()
    }

    override fun initMenuPersetujuan() {
        TODO("Not yet implemented")
    }

    override fun setupAbsenButton(btnMulaiVisibility: Int, btnKeluarVisibility: Int) {
        btn_mulai_absen_04.visibility = btnMulaiVisibility
        ripple_masuk_04.visibility = btnMulaiVisibility
        btn_keluar_absen_04.visibility = btnKeluarVisibility
        ripple_keluar_04.visibility = btnKeluarVisibility

        if (btnMulaiVisibility == View.VISIBLE) {
            ripple_masuk_04.startRippleAnimation()
            ripple_keluar_04.stopRippleAnimation()
        } else {
            ripple_masuk_04.stopRippleAnimation()
            ripple_keluar_04.startRippleAnimation()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_mulai_absen_04 -> {

                if (AccountHelper.keyStatus != "keluar") {
                    presenter.getSSID()
                    openCamera()
                }

            }
            R.id.btn_keluar_absen_04 -> {

                presenter.getSSID()
                startActivity(Intent(this, AbsenActivity::class.java))

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE_CAPTURE) {

            if (resultCode == RESULT_OK) {

                showDialog()

            }
        } else if (requestCode == LOCATION_INTENT) {

            if (resultCode == RESULT_OK) {
//                dialog.tv_lokasi.text = AccountHelper.keyAddress
            }

        }
    }

    private fun showDialog() {

        val file = File(getRealPathFromURI(Uri.parse(AccountHelper.keyImageUri)))

        val compressedFile = getCompressedFile(file)!!
//        dialog.setContentView(R.layout.submit_absen_dialog)
//        dialog.window!!.apply {
//            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            setLayout(
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.WRAP_CONTENT
//            )
//        }

//        dialog.tv_lokasi.text = AccountHelper.keyAddress

//        Glide.with(PressensiApp.context!!)
//            .load(file)
//            .placeholder(context?.resources?.getDrawable(R.drawable.image_default_profile))
//            .into(dialog.iv_fotokaryawan_dialog)

//        dialog.btnKeluar.setOnClickListener {
//            Common.dismissProgressDialog()
//            dialog.dismiss()
//        }

//        dialog.btn_maps.setOnClickListener {
//
//            val intent = Intent(this, AmbilLokasiActivity::class.java)
//            startActivityForResult(intent, LOCATION_INTENT)
//        }

//        dialog.btn_kirim.setOnClickListener {
//            Common.showProgressDialog(this, getString(R.string.LOADING))
//            presenter.enterAbsen(
//                AccountHelper.keyLatitude!!,
//                AccountHelper.keyLongtitude!!,
//                compressedFile
//            )
//
//            dialog.dismiss();
//
//        }

//        dialog.show()

    }

    private fun setUpMap() {

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        mFusedLocationClient?.lastLocation?.addOnSuccessListener(this) { location ->

            if (location != null) {

                mLastLocation = location

                getAlamat(location.latitude, location.longitude)

                AccountHelper.keyLatitude = location.latitude.toString()
                AccountHelper.keyLongtitude = location.longitude.toString()

            }

        }

    }

    fun getAlamat(latitude: Double, longtitude: Double) {
        val alamatlengkap: String?
        val kata: List<String>?
        val i : Int

        try {
            address = geocoder.getFromLocation(latitude, longtitude, 1)
        } catch (ioException: IOException) {
            // Catch network or other I/O problems.
            Toast.makeText(this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show()
        } catch (illegalArgumentException: IllegalArgumentException) {
            // Catch invalid latitude or longitude values.
            Toast.makeText(
                this,
                "Ada kesalahan, silahkan coba beberapa saat lagi",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (address.isEmpty()) {
            Toast.makeText(this, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
        } else {
            alamatlengkap = address!!.get(0).getAddressLine(0)
            kata = alamatlengkap.split(",")
            for(i in kata.indices) {
                AccountHelper.keyAddress = kata[0]
            }
        }

    }

    override fun onFakeGPSDetected() {
        Common.dismissProgressDialog()

        Common.showPopUpDialog(
            this,
            getString(R.string.title_fake_gps_detected),
            getString(R.string.label_message_fake_gps_detected),
            SweetAlertDialog.WARNING_TYPE
        )

        deleteImageFile()
    }

    override fun onBackPressed() {
        doExitApp()
    }

    override fun onResume() {
        super.onResume()

        LocalBroadcastManager.getInstance(this).registerReceiver(
            myReceiver,
            IntentFilter(LocationUpdateService.ACTION_BROADCAST)
        )

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
            broadcastReceiver,
            IntentFilter(AppConstants.REGISTRATION_COMPLETE)
        )

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(
            broadcastReceiver,
            IntentFilter(AppConstants.PUSH_NOTIFICATION)
        )

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver(), IntentFilter("trigger-menu"))

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(applicationContext)

        presenter.getStatusAbsensi()

        cekNotifikasi()
    }

    private fun menuOnclick() {
        swichFragment(berandaFragment)
        menu_beranda_04.setOnClickListener {
            trigger(0)
            removeFragment(akunFragment)
            swichFragment(berandaFragment)

        }
        menu_akun_04.setOnClickListener {
            trigger(1)
            removeFragment(berandaFragment)
            swichFragment(akunFragment)
        }

        btn_home_04.setColorFilter(
            Color.parseColor("#00A4E1"),
            PorterDuff.Mode.SRC_IN
        )

        tv_beranda_04.setTextColor(Color.parseColor("#00A4E1"))
//
//        btn_bottom_pengajuan_11.setOnClickListener { startActivity(Intent(context, PengajuanIzinActivity::class.java)) }
//        btn_bottom_kegiatan_11.setOnClickListener { startActivity(Intent(context, KegiatanActivity::class.java)) }
//        btn_bottom_reimburse_11.setOnClickListener { startActivity(Intent(context, ReimburseActivity::class.java)) }
//        btn_bottom_absensi_harian_11.setOnClickListener { startActivity(Intent(context, AbsensiHarianActivity::class.java)) }
//        btn_bottom_pengumuman_11.setOnClickListener { startActivity(Intent(context, PengumumanActivity::class.java)) }
//        btn_bottom_persetujuan_11.setOnClickListener { startActivity(Intent(context, PersetujuanActivity::class.java)) }
//        btn_bottom_jadwal_11.setOnClickListener { Common.showPopUpDialog(context!!, "Cooming Soon !", "Mohon maaf, \n Menu belum tersedia", SweetAlertDialog.WARNING_TYPE) }
//        btn_bottom_obrolan_11.setOnClickListener { Common.showPopUpDialog(context!!, "Cooming Soon !", "Mohon maaf, \n Menu belum tersedia", SweetAlertDialog.WARNING_TYPE) }
    }

    private fun removeFragment(fragment: Fragment?) {
        val switch: FragmentTransaction = supportFragmentManager.beginTransaction()
        switch.remove(fragment!!)
        switch.commit()
    }

    private fun setStateSelected(imageView: ImageView, textView: TextView) {
        imageView.setColorFilter(
            Color.parseColor("#00A4E1"),
            PorterDuff.Mode.SRC_IN
        )

        textView.setTextColor(Color.parseColor("#00A4E1"))

    }

    private fun trigger(int: Int) {
        val i = Intent("trigger-menu")
        i.putExtra("TRIGGER", int.toString())
        LocalBroadcastManager.getInstance(this).sendBroadcast(i)
    }

    private fun swichFragment(fragment: Fragment?) {
        val switch: FragmentTransaction = supportFragmentManager.beginTransaction()
        switch.replace(R.id.fl_home_04, fragment!!)
        switch.commit()
    }

    private fun initFragment() {
        berandaFragment =
            BerandaFragment()
        akunFragment = AkunFragment()
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver())
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
        super.onPause()
    }

    private fun cekNotifikasi(){
//        if(keyKategori != null) {
//            if (keyKategori == "pembatalan") {
//                if (keyIsRead == "0") {
//                    presenter.updateNotifikasi(keyEmail!!, keyToken!!, keyIdKategori!!)
//                    trigger(2)
//                    swichFragment(statisikFragment)
//                    keyKategori = null
//                    keyIdKategori = null
//                    keyIsRead = null
//
//                } else {
//                    trigger(2)
//                    swichFragment(statisikFragment)
//                    keyKategori = null
//                    keyIdKategori = null
//                    keyIsRead = null
//                }
//            } else if (keyKategori == "pengajuan") {
//                if (keyIsRead == "0") {
//                    presenter.updateNotifikasi(keyEmail!!, keyToken!!, keyIdKategori!!)
//                    trigger(1)
//                    swichFragment(riwayatFragment)
//                    keyKategori = null
//                    keyIdKategori = null
//                    keyIsRead = null
//
//                } else {
//                    trigger(1)
//                    swichFragment(riwayatFragment)
//                    keyKategori = null
//                    keyIdKategori = null
//                    keyIsRead = null
//                }
//            }
//        }else{
//            Log.d("HomeActivity", "Kateogri kosong")
//        }
    }

    private fun receiver() = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val i = intent!!.getStringExtra("TRIGGER")

            if (i != null) {
                imageView = when (i) {
                    "0" -> findViewById(R.id.btn_home_04)
                    else -> findViewById(R.id.btn_akun_04)
                }

                textView = when (i) {
                    "0" -> findViewById(R.id.tv_beranda_04)
                    else -> findViewById(R.id.tv_akun_04)
                }

                Log.d(TAG, "onReceive: ${i}")

                btnId.toMutableList().removeAt(i.toInt())
                btnId.forEach {
                    val x: ImageView = findViewById(it)
                    x.setColorFilter(
                        Color.parseColor("#DADADA"),
                        PorterDuff.Mode.SRC_IN
                    )
                }

                tvId.toMutableList().removeAt(i.toInt())
                tvId.forEach {
                    val x: TextView = findViewById(it)
                    x.setTextColor(Color.parseColor("#DADADA"))
                }
                setStateSelected(imageView, textView)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: HomeActivity? = null
            private set

        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }
}