package com.folkatech.pressensiapps.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.folkatech.pressensi.model.userinfo.UserInfo
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.Common
import com.folkatech.pressensiapps.common.base.BaseActivity
import com.folkatech.pressensiapps.common.constant.AppConstants
import com.folkatech.pressensiapps.common.utils.Config
import com.folkatech.pressensiapps.common.utils.ValidationHelper
import com.folkatech.pressensiapps.ui.activity.forgotpassword.ForgotPasswordActivity
import com.folkatech.pressensiapps.ui.activity.home.HomeActivity
import com.folkatech.pressensiapps.ui.activity.personaldataform.PersonalDataFormActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View, View.OnLayoutChangeListener {
    private val TAG = "LoginActivity"

    @Inject
    lateinit var presenter: LoginContract.Presenter

    private val subscriptions = CompositeDisposable()

    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var callbackManager: CallbackManager

    override var isEmailFieldValid: Boolean = false
    override var isPasswordFieldValid: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        presenter.attach(this)
        setContentView(R.layout.activity_login)
    }

    override fun onInit() {
        initLoginButton()
        initForgotPasswordButton()
        initGoogleSignIn()
//        setupFacebookLogin()

        //Listeners
        onNipFieldValidationListener()
        onPasswordFieldValidationListener()
        rootView.addOnLayoutChangeListener(this)

//        btn_login_sign_up.setOnClickListener {
//            startActivity(SignUpActivity::class.java)
//        }

        btn_google.setOnClickListener {
            signInWithGoogle()
        }

//        btn_login_facebook.setOnClickListener {
//            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
//        }
    }

    private fun initGoogleSignIn() {
        if (!::gso.isInitialized) {
            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_client_id))
                .requestEmail()
                .build()
        }

        if (!::mGoogleSignInClient.isInitialized) {
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        }
    }

    private fun setupFacebookLogin() {
        if (!::callbackManager.isInitialized) {
            callbackManager = CallbackManager.Factory.create()
        }

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    handleFacebookLoginResult(result?.accessToken)
                    Log.d(TAG, "onSuccess: ${result?.accessToken}")
                }

                override fun onCancel() {
                    Log.d(TAG, "onCancel: ")

                }

                override fun onError(error: FacebookException?) {
                    Log.e(TAG, "onError: ${error?.message}")

                }

            })
    }

    private fun handleFacebookLoginResult(accessToken: AccessToken?) {
        /**
        Creating the GraphRequest to fetch user details
        1st Param - AccessToken
        2nd Param - Callback (which will be invoked once the request is successful)
         **/
        val request = GraphRequest.newMeRequest(
            accessToken
        ) { `object`, response ->
            //OnCompleted is invoked once the GraphRequest is successful
            try {
                val name = `object`.getString("name")
                val email = `object`.getString("email")
                val token = accessToken?.token

                presenter.submitLoginBySocMed(getFcmToken(), name, email)

//                updateUI(UserInfo(accessToken?.token, name, email))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        // We set parameters to the GraphRequest using a Bundle.
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        // Initiate the GraphRequest
        request.executeAsync()

    }

    private fun signInWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, AppConstants.GOOGLE_SIGN_IN_LOGIN)
    }

    private fun initLoginButton() {
        btn_masuk_01.isEnabled = false

        btn_masuk_01.setOnClickListener {
            presenter.submitLogin(
                et_email_01.text.toString(),
                et_password_01.text.toString(),
                Config.versionName,
                getFcmToken()
            )
            Common.showProgressDialog(this, resources.getString(R.string.label_message_logging_in))
        }
    }

    private fun getFcmToken(): String {
        val pref = applicationContext.getSharedPreferences(AppConstants.SHARED_PREF, 0)
        return pref.getString(AppConstants.KEY_REG_ID, "")!!

    }

    override fun onNipFieldValidationListener() {
        subscriptions.add(
            et_email_01.textChanges()
                .skipInitialValue()
                .observeOn(Schedulers.io())
                .map { it.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (ValidationHelper.isEmpty(it)) {
                        til_email_01.error = resources.getText(R.string.error_email)
                        isEmailFieldValid = false
                    } else {
                        til_email_01.error = null
                        et_email_01.error = null
                        isEmailFieldValid = true
                    }
                }
        )
    }

    override fun onPasswordFieldValidationListener() {
        subscriptions.add(
            et_password_01.textChanges()
                .skipInitialValue()
                .observeOn(Schedulers.io())
                .map { it.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (ValidationHelper.isEmpty(it)) {
                        til_pw_01.error = resources.getText(R.string.error_password)
                        isPasswordFieldValid = false
                    } else {
                        til_pw_01.error = null
                        et_password_01.error = null
                        isPasswordFieldValid = true
                    }
                }
        )
    }

    private fun initForgotPasswordButton() {
        tv_lupapw_01.setOnClickListener {
            startActivity(ForgotPasswordActivity::class.java)
        }
    }

    override fun onSuccessSubmitLogin() {
        Common.setProgressDialogMessage(resources.getString(R.string.label_message_mengambil_data_user))
        presenter.getUserData()
    }

    override fun onSuccessGetUserData() {
        Common.dismissProgressDialog()
        startActivity(HomeActivity::class.java)
        finish()
    }

    override fun onLayoutChange(
        v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int,
        oldTop: Int, oldRight: Int, oldBottom: Int
    ) {
        btn_masuk_01.isEnabled = isEmailFieldValid && isPasswordFieldValid
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AppConstants.GOOGLE_SIGN_IN_LOGIN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    // Handle sign in result of google sign in
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
//            val token = account!!.idToken
//            val name = account.displayName
//            val email = account.email
            Log.d(TAG, "onActivityResult: ${account}")

//            presenter.submitLoginBySocMed(getFcmToken(), name, email)
        } catch (e: ApiException) {
            Log.d(TAG, "onActivityResult: ${e}")
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(TAG, "handleSignInResult: $e")
            Log.w(TAG, "signInResult:failed code=${e.statusCode}")
//            updateUI(null)
        }
    }

    override fun onFailureSubmitLogin(
        token: String,
        name: String,
        email: String?,
        message: String
    ) {
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Gagal")
            .setContentText("$message\nApakah anda ingin daftar akun baru?")
            .setConfirmText("Ya")
            .setCancelText("Tidak")
            .showCancelButton(true)
            .setCancelClickListener { sweetAlertDialog -> sweetAlertDialog.dismiss() }
            .setConfirmClickListener {
                val bundle = Bundle()
                bundle.putParcelable(
                    AppConstants.KEY_BUNDLE_USER_INFO,
                    UserInfo(token, name, email)
                )
                it.dismiss()
                startActivity(PersonalDataFormActivity::class.java, bundle)
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
        presenter.unsubscribe()
    }
}