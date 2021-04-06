package com.folkatech.pressensiapps.common

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog
import com.folkatech.pressensiapps.BuildConfig
import com.folkatech.pressensiapps.PressensiApp
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.constant.AppConstants.IMAGE_DIRECTORY_NAME
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@Suppress("DEPRECATION")
object Common {

    private val TAG = "Common"

    /** The loading progress dialog object */
    var progressDialog: ProgressDialog? = null

    /**
     * Shows a loading progress dialog.
     * @param context the context
     * @param message the dialog message string
     * @param onBackPressListener the back button press listener when loading is shown
     */
    fun showProgressDialog(context: Context, message: String) {
        dismissProgressDialog()
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(false)
        if (context is Activity && !context.isFinishing) progressDialog!!.show()
    }

    /** Hides the currently shown loading progress dialog */
    fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    /** Hides the currently shown loading progress dialog */
    fun dismissProgressDialog12312412() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    /**
     * Sets the progress dialog progress in percent.
     * @param progress The loading progress in percent
     */
    fun setProgressDialogProgress(progress: Int) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.progress = progress
            progressDialog!!.setMessage("$progress%")
        }
    }

    /**
     * Sets the progress dialog progress indeterminate state.
     * @param isIndeterminate Determines if progress dialog is indeterminate
     */
    fun setProgressDialogIndeterminate(isIndeterminate: Boolean) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.isIndeterminate = isIndeterminate
        }
    }

    /**
     * Sets the progress dialog message.
     * @param message The dialog message string
     */
    fun setProgressDialogMessage(message: String) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.setMessage(message)
        }
    }

    /**
     * Display a simple [Toast].
     * @param stringRes The message string resource id
     */
    fun showToast(stringRes: Int) {
        showToast(PressensiApp.context!!.getString(stringRes))
    }

    /**
     * Display a simple [Toast].
     * @param message The message string
     * @param toastLength The toast length int
     */
    fun showToast(message: String, toastLength: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(PressensiApp.context, message, toastLength).show()
    }

    /**
     * Display a simple [AlertDialog] with a simple OK button.
     * If the dismiss listener is specified, the dialog becomes uncancellable
     * @param context The context
     * @param title The title string
     * @param message The message string
     * @param dismissListener The dismiss listener
     */
    fun showMessageDialog(
        context: Context, title: String?, message: String?,
        dismissListener: DialogInterface.OnDismissListener? = null
    ) {
        val builder = AlertDialog.Builder(context, R.style.AppTheme_Dialog_Alert)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok, { dialog, _ -> dialog.dismiss() })

        val dialog = builder.create()
        if (dismissListener != null) {
            dialog.setOnDismissListener(dismissListener)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
        }
        dialog.show()
    }

    /**
     * Display a [SweetAlertDialog]
     * @param context The context
     * @param title The title string
     * @param message The message string
     * @param alertType The alert type int
     */
    fun showPopUpDialog(
        context: Context,
        title: String,
        message: String,
        alertType: Int = SweetAlertDialog.SUCCESS_TYPE
    ) {
        val dialog = SweetAlertDialog(context, alertType)
        dialog.apply {
            titleText = title
            contentText = message
            confirmText = "OK"
            setConfirmClickListener(null)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }
        dialog.show()
    }

    fun showPopUpDialogWaring(
        context: Context,
        title: String,
        message: String,
        alertType: Int = SweetAlertDialog.WARNING_TYPE
    ) {
        val dialog = SweetAlertDialog(context, alertType)
        dialog.apply {
            titleText = title
            contentText = message
            confirmText = "OK"
            setConfirmClickListener(null)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }
        dialog.show()
    }

    fun createImageFile(imgBitmap: Bitmap): File {
//create a file to write bitmap data
        val mediaStorageDir = File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            IMAGE_DIRECTORY_NAME
        )
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(
                    TAG, "Oops! Failed create "
                            + IMAGE_DIRECTORY_NAME + " directory"
                )
            }
        }

        val timeStamp = SimpleDateFormat(
            "yyyyMMdd_HHmmss",
            Locale.getDefault()
        ).format(Date())

        val f = File(
            mediaStorageDir.path + File.separator
                    + "IMG_" + timeStamp + ".jpg"
        )

        f.createNewFile()

        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        assert(imgBitmap != null)
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 75, bos)
        val bitmapdata = bos.toByteArray()

        val fos = FileOutputStream(f)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()

        return f
    }

    fun createImageFile(context: Context, imgUri: Uri): File {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

        // Get the cursor
        val cursor = context.contentResolver.query(
            imgUri,
            filePathColumn, null, null, null
        )!!
        // Move to first row
        cursor!!.moveToFirst()

        val columnIndex = cursor!!.getColumnIndex(filePathColumn[0])
        val imgDecodableString = cursor!!.getString(columnIndex)
        cursor!!.close()

        val imgBitmap = BitmapFactory
            .decodeFile(imgDecodableString)

        return createImageFile(imgBitmap)
    }

    /**
     * Prints an exception's stack trace.
     * Stack traces printed via this method will only show up on debug builds.
     * @param throwable the throwable
     */
    fun printStackTrace(throwable: Throwable) {
        if (BuildConfig.DEBUG) throwable.printStackTrace()
    }

    /**
     * Prints a [Log] message.
     * Log messages printed via this method will only show up on debug builds.
     * @param type The specified log type, may be [Log.DEBUG], [Log.INFO], and other log types
     * @param tag The log tag to print
     * @param message The log message to print
     */
    fun log(type: Int = Log.DEBUG, tag: String? = "BaseProject", message: String?) {
        if (BuildConfig.DEBUG) {
            var logMessage = message
            if (logMessage.isNullOrEmpty()) logMessage =
                "Message is null, what exactly do you want to print?"
            Log.println(type, tag, logMessage)
        }
    }

    /**
     * Get the specified URL according to the different app builds.
     * @return The specified web URL
     */
    val webURL: String
        get() = BuildConfig.M_WEB_URL

    /**
     * Obtain the API URL for API calls.
     * This method and [webURL] are differentiated, to handle if API calls uses a different URL.
     * @return API URL in String
     */
    val apiURL: String
        get() = BuildConfig.M_API_URL

    /**
     * Obtain the API key for API calls.
     * @return API key in String
     */
    val apiKey: String
        get() = BuildConfig.M_API_KEY
}