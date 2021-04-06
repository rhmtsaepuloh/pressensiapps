package com.folkatech.pressensiapps.common.base


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class BaseContract {
    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
        /**
         * To get root view
         */
        val rootView: android.view.View

        /**
         * Show error message when any process related to the server has failed
         * @param message The error message string
         */
        fun onError(message: String)

        fun showWarningAlert(message:String)
    }
}