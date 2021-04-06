package com.folkatech.pressensiapps.ui.fragment.beranda.adapter

import android.view.View
import com.folkatech.pressensi.model.Informasi


/**
 * Created by Rahmat Saefulloh on 17/03/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
interface OnPengumumanItemClickListener {
    fun onClickItem(view: View, obj: Informasi, position: Int)
}