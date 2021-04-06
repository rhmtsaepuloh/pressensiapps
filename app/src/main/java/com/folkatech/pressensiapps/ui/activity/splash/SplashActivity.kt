package com.folkatech.pressensiapps.ui.activity.splash

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.base.BaseActivity
import com.folkatech.pressensiapps.common.constant.AppConstants
import com.folkatech.pressensiapps.common.utils.PermissionHelper
import com.folkatech.pressensiapps.ui.activity.home.HomeActivity
import com.folkatech.pressensiapps.ui.activity.welcomeslider.WelcomeSliderActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {
    private val TAG = "SplashActivity"

    @Inject
    lateinit var presenter: SplashContract.Presenter

    private lateinit var permissionHelper: PermissionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        presenter.attach(this)
        setContentView(R.layout.activity_splash)
    }

    override fun onInit() {
        initPermission()
    }

    override fun initPermission() {
        permissionHelper = PermissionHelper.Builder(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.RECEIVE_BOOT_COMPLETED
            )
            .withRequestCode(AppConstants.REQUEST_CODE_PERMISSION)
            .withListener(object : PermissionHelper.OnPermissionCheckedListener {
                override fun onPermissionGranted(isPermissionAlreadyGranted: Boolean) {
                    presenter.initSplash()
                }

                override fun onPermissionDenied() {
                    showPermissionDialog()
                }
            })
            .build()

        permissionHelper.requestPermission()
    }

    override fun showPermissionDialog() {
        AlertDialog.Builder(this, R.style.AppTheme)
            .setTitle(R.string.label_warning)
            .setCancelable(false)
            .setMessage(R.string.label_message_apps_requirement)
            .setPositiveButton(R.string.label_show_permission) { dialog, which ->
                dialog.dismiss()
                initPermission()
            }
            .setNegativeButton(R.string.label_close_app) { dialog, which ->
                dialog.dismiss()
                finish()
            }
            .show()
    }

    override fun openHome() {
        Log.d(TAG, "openHome")
        startActivityWithFade(HomeActivity::class.java)
        finish()
    }

    override fun openLogin() {
        Log.d(TAG, "openLogin")
        startActivityWithFade(WelcomeSliderActivity::class.java)
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //Permissions result handler
        permissionHelper.onRequestPermissionsResult(
            AppConstants.REQUEST_CODE_PERMISSION,
            null,
            null
        )

    }
}