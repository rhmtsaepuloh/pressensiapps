package com.folkatech.pressensiapps.ui.fragment.beranda

import com.folkatech.pressensi.model.DataPengumuman
import com.folkatech.pressensi.model.Informasi
import com.folkatech.pressensiapps.common.base.BaseContract


/**
 * Created by Rahmat Saefulloh on 18/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class BerandaContract {
    interface View : BaseContract.View {

        fun initView()
        fun initbottomSheet()
        fun onClick()
        fun setText()
        fun initCurrentDate()
        fun initBgBeranda()
        fun onSuccessGetPengumuman(data: List<Informasi>)
        fun onErrorGetPengumuman(message: String)

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getPengumuman(page: Int)
    }
}