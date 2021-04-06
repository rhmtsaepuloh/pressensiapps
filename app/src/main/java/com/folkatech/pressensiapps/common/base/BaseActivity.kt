package com.folkatech.pressensiapps.common.base

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.transition.Fade
import android.transition.Visibility
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.Common
import com.folkatech.pressensiapps.common.constant.AppConstants
import com.folkatech.pressensiapps.common.utils.AccountHelper
import com.folkatech.pressensiapps.common.utils.DateTimeHelper
import com.folkatech.pressensiapps.common.utils.Tools
import com.folkatech.pressensiapps.di.component.ActivityComponent
import com.folkatech.pressensiapps.di.component.DaggerActivityComponent
import com.folkatech.pressensiapps.di.module.ActivityModule
import com.folkatech.pressensiapps.ui.activity.login.LoginActivity
import id.zelory.compressor.Compressor
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import java.util.*


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(), BaseContract.View {
    private val TAG = "BaseActivity"

    private var mCurrentFragment: Fragment? = null

    protected lateinit var activityComponent: ActivityComponent

    val disposable = CompositeDisposable()

    private var exitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as PressensiApp).component)
            .build()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        Tools.systemBarLolipop(this)
        onInit()
    }

    override val rootView: View
        get() = window.decorView.rootView

    override fun onError(message: String) {
        Common.dismissProgressDialog()
        Common.showPopUpDialog(
            this,
            getString(R.string.title_dialog_error), message, SweetAlertDialog.ERROR_TYPE
        )

        redirectToLoginIfMust(message)
    }

    override fun showWarningAlert(message: String) {
        Common.dismissProgressDialog()

        Common.showPopUpDialog(
            this,
            getString(R.string.title_dialog_warning), message, SweetAlertDialog.WARNING_TYPE
        )

        redirectToLoginIfMust(message)
    }

    fun startActivityWithFade(clazz: Class<*>) {
        startActivity(clazz)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun startActivityWithFade(clazz: Class<*>, bundle: Bundle) {
        startActivity(clazz, bundle)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun startActivityFromRight(clazz: Class<*>, bundle: Bundle) {
        startActivity(clazz, bundle)
        overridePendingTransition(R.anim.anim_enter_right, R.anim.anim_sticky)
    }

    fun startActivityFromRight(clazz: Class<*>) {
        startActivity(clazz)
        overridePendingTransition(R.anim.anim_enter_right, R.anim.anim_sticky)
    }

    fun startActivityFromBottom(clazz: Class<*>) {
        startActivity(clazz)
        overridePendingTransition(R.anim.anim_enter_bottom, R.anim.anim_sticky)
    }

    fun startActivity(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    fun startActivity(clazz: Class<*>) {
        startActivity(clazz, null)
    }

    fun finishToRight() {
        finish()
        overridePendingTransition(R.anim.anim_sticky, R.anim.anim_leave_right)
    }

    fun finishWithFade() {
        finish()
        overridePendingTransition(android.R.anim.fade_out, R.anim.anim_leave_right)
    }

    /**
     * Redirect to login activity if there is "login kembali" message from the server
     * @param message The message string from the server
     */
    fun redirectToLoginIfMust(message: String) {
        if (message.toLowerCase().contains(resources.getString(R.string.label_login_kembali))) {
            AccountHelper.keyToken = ""
            startActivity(LoginActivity::class.java)
            finish()
        }
    }

    /**
     * To open user device camera
     */
    fun openCamera() {
        try {
            val imageUri: Uri

            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "")
            values.put(MediaStore.Images.Media.DESCRIPTION, "")

            imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!

            AccountHelper.keyImageUri = imageUri.toString()

            val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            // Ensure that there's a camera activity to handle the intent
            if (captureIntent.resolveActivity(packageManager) != null) {
                // Hide this to get thumbnail instead of full-sized photo
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

                // we will handle the returned data in onActivityResult
                startActivityForResult(captureIntent, AppConstants.REQUEST_CODE_IMAGE_CAPTURE)
            }
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, "openCamera: ${e.message}")
            Common.showToast(R.string.lable_message_error_crop_action)
        }
    }

    @Suppress("DEPRECATION")
    fun getRealPathFromURI(contentUri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = managedQuery(contentUri, proj, null, null, null)
        val column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }

    @SuppressLint("CheckResult")
    fun getCompressedFile(imageFile: File): File {
        /*Compressor(this)
            .setMaxWidth(480)
            .setMaxHeight(640)
            .setQuality(75)
            .setCompressFormat(Bitmap.CompressFormat.JPEG)
            .compressToFileAsFlowable(imageFile)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ file ->
                compressedFile = file
            }, { error ->
                Log.e(TAG, "onError: ${error.message}")
//                compressedFile = null!!
                return@subscribe
            })*/

        return Compressor(this)
            .setMaxWidth(480)
            .setMaxHeight(640)
            .setQuality(75)
            .setCompressFormat(Bitmap.CompressFormat.JPEG)
            /*.setDestinationDirectoryPath(
                Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).absolutePath
            )*/
            .compressToFile(imageFile)
    }

    fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, AppConstants.REQUEST_CODE_IMAGE_CAPTURE)
        }
    }

    fun dispatchGalleryPictureIntent() {
        // Create intent to Open Image applications like Gallery, Google Photos
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Start the Intent
        startActivityForResult(galleryIntent, AppConstants.REQUEST_CODE_IMAGE_GALLERY)
    }

    fun compressImageFile(imgFile: File): File {
        return Compressor(this).compressToFile(imgFile)
    }

    fun compressImageFileProfile(imgFile: File): File {
        return Compressor(this)
            .setMaxHeight(250)
            .setMaxWidth(250)
            .setQuality(80)
            .setCompressFormat(Bitmap.CompressFormat.JPEG)
            .compressToFile(imgFile)
    }

    fun compressImageFileBanner(imgFile: File): File {
        return Compressor(this)
            .setMaxHeight(300)
            .setMaxWidth(600)
            .setQuality(80)
            .setCompressFormat(Bitmap.CompressFormat.JPEG)
            .compressToFile(imgFile)
    }

    fun doExitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            finish()
        }
    }

    fun showDatePicker(tag: String, editText: TextView) {
        val newCalendar = Calendar.getInstance()
        val dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
            { view, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                editText.text = DateTimeHelper.dateFormatDefaultReverse.format(newDate.time)
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        dpd.minDate = newCalendar
        dpd.show(fragmentManager, tag)
    }

    fun showDatePicker(context: Context, editText: EditText) {
        val newCalendar = Calendar.getInstance()
        val dpd = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                editText.setText(DateTimeHelper.dateFormatDefaultReverse.format(newDate.time))
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show()
    }


    // This method is used to set the default fragment that will be shown.
    fun setDefaultFragment(defaultFragment: Fragment) {
//        this.replaceFragment(defaultFragment, R.string.title_fragment_home)
    }

    // Replace current Fragment with the destination Fragment.
    fun replaceFragment(destFragment: Fragment, titleResId: Int) {

        if (mCurrentFragment !== destFragment) {
            // First get FragmentManager object.
            val fragmentManager = this.supportFragmentManager

            // Begin Fragment transaction.
            val fragmentTransaction = fragmentManager.beginTransaction()

            destFragment.enterTransition = transitionWithFadeIn()
            destFragment.exitTransition = transitionWithFadeOut()

            // Replace the layout holder with the required Fragment object.
//            fragmentTransaction.replace(R.id.fragmentFrameLayout, destFragment)

            // Commit the Fragment replace action.
            fragmentTransaction.commit()
            setTitle(titleResId)
            mCurrentFragment = destFragment
        }
    }

    // Replace current Fragment with the destination Fragment.
    /*fun replaceFragment(destFragment: Fragment, titleResId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_home_container, destFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        setTitle(titleResId)
    }*/

    private fun transitionWithFadeIn(): Fade? {
        if (Build.VERSION.SDK_INT > 20) {
            val fadeIn = Fade()
            fadeIn.duration = 500
            fadeIn.mode = Visibility.MODE_IN
            return fadeIn
        } else
            return null
    }

    private fun transitionWithFadeOut(): Fade? {
        if (Build.VERSION.SDK_INT > 20) {
            val fadeOut = Fade()
            fadeOut.duration = 300
            fadeOut.mode = Visibility.MODE_OUT
            return fadeOut
        } else
            return null
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    protected abstract fun onInit()
}