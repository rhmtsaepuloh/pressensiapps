package com.folkatech.pressensiapps.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.utils.AccountHelper
import com.folkatech.pressensiapps.di.component.DaggerFragmentComponent
import com.folkatech.pressensiapps.di.component.FragmentComponent
import com.folkatech.pressensiapps.di.module.FragmentModule
import com.folkatech.pressensiapps.ui.activity.login.LoginActivity
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Rahmat Saefulloh on 18/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
abstract class BaseFragment : Fragment(), BaseContract.View {

    protected lateinit var fragmentComponent: FragmentComponent
    protected val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    @Suppress("DEPRECATION")
    private fun injectDependency() {
        fragmentComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInit()
    }

    val baseActivity: BaseActivity
        get() = activity as BaseActivity

    override val rootView: View
        get() = baseActivity.rootView

    override fun onError(message: String) {
        baseActivity.onError(message)
    }

    override fun showWarningAlert(message: String) {
        baseActivity.showWarningAlert(message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }

    abstract fun onInit()
}