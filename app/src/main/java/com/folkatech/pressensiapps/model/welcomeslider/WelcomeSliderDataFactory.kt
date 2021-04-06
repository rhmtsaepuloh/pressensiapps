package com.folkatech.pressensi.model.welcomeslider

import android.content.Context
import com.folkatech.pressensiapps.R

/**
 * Created by Luthfan Maftuh on 8/13/2019.
 * Email luthfanmaftuh@gmail.com
 */
object WelcomeSliderDataFactory {

    private val welcomeDatas: MutableList<WelcomeSliderData> = mutableListOf()

    fun setWelcomeSliderData(context: Context) {
        if (welcomeDatas.isNullOrEmpty()) {
            val welcomeDrawables = context.resources.obtainTypedArray(R.array.drawable_welcome_sliders)
            val welcomeTitles = context.resources.getStringArray(R.array.title_welcome_sliders)
            val welcomeCaptions = context.resources.getStringArray(R.array.caption_welcome_sliders)

            for (i in 0 until welcomeTitles.size) {
                welcomeDatas.add(
                    i, WelcomeSliderData(
                        welcomeDrawables.getResourceId(i, 0),
                        welcomeTitles[i],
                        welcomeCaptions[i]
                    )
                )
            }
        }
    }

    fun getWelcomeSliderData(): List<WelcomeSliderData> = welcomeDatas
}