package com.folkatech.pressensiapps.ui.activity.welcomeslider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.folkatech.pressensi.model.welcomeslider.WelcomeSliderData
import com.folkatech.pressensiapps.R
import kotlinx.android.synthetic.main.welcome_slider_item.view.*


/**
 * Created by Rahmat Saefulloh on 13/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
class WelcomeSliderPagerAdapter (private val dataSources: List<WelcomeSliderData>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val data = dataSources[position]

        val view =
            LayoutInflater.from(container.context).inflate(R.layout.welcome_slider_item, container, false)

        view.tv_welcome_title_item.text = data.title
        view.tv_welcome_caption_item.text = data.caption

        Glide.with(container.context)
            .load(data.drawableResId)
            .dontAnimate()
            .into(view.iv_welcome_item)

        container.addView(view)

        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object`

    override fun getCount(): Int = dataSources.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}