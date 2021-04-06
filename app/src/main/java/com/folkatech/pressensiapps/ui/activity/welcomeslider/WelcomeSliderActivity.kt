package com.folkatech.pressensiapps.ui.activity.welcomeslider

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.folkatech.pressensi.model.welcomeslider.WelcomeSliderDataFactory
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.base.BaseActivity
import com.folkatech.pressensiapps.ui.activity.login.LoginActivity
import kotlinx.android.synthetic.main.activity_welcome_slider.*

class WelcomeSliderActivity : BaseActivity() {
    private val MAX_STEP = 4
    private lateinit var pagerAdapter: WelcomeSliderPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_slider)
    }

    override fun onInit() {
        WelcomeSliderDataFactory.setWelcomeSliderData(this)
        bottomProgressDots(0)

        pagerAdapter = WelcomeSliderPagerAdapter(WelcomeSliderDataFactory.getWelcomeSliderData())
        pager_welcome.adapter = pagerAdapter
        pager_welcome.addOnPageChangeListener(pagerPageChangeListener)

        btn_welcome_mulai.setOnClickListener {
            val current = pager_welcome.currentItem + 1
            pager_welcome.currentItem = current
        }

        btn_welcome_end.setOnClickListener {
            startActivity(LoginActivity::class.java)
            finish()
        }

        btn_skip.setOnClickListener {
            val current = 4
            pager_welcome.currentItem = current
        }
    }

    private fun bottomProgressDots(current_index: Int) {
        val dots = arrayOfNulls<ImageView>(MAX_STEP)

        layout_welcome_dots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val width_height = 15
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams(width_height, width_height))
            params.setMargins(10, 10, 10, 10)
            dots[i]?.layoutParams = params
            dots[i]?.setImageResource(R.drawable.shape_circle)
            dots[i]?.setColorFilter(
                resources.getColor(R.color.grey_400),
                PorterDuff.Mode.SRC_IN
            )
            layout_welcome_dots.addView(dots[i])
        }

        if (dots.isNotEmpty()) {
            dots[current_index]?.setImageResource(R.drawable.shape_circle)
            dots[current_index]?.setColorFilter(
                resources.getColor(R.color.colorPrimary),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    //  viewpager change listener
    private val pagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            bottomProgressDots(position)
            if (position == pagerAdapter.count - 1) {
                btn_welcome_end.visibility = View.VISIBLE;
                btn_welcome_mulai.visibility = View.GONE;
                btn_skip.visibility = View.GONE;
                btn_welcome_end.apply {
                    text = resources.getString(R.string.label_mulai)
                }
            } else {
                btn_welcome_end.visibility = View.GONE;
                btn_welcome_mulai.visibility = View.VISIBLE;
                btn_skip.visibility = View.VISIBLE;
                btn_welcome_mulai.apply {
                    text = resources.getString(R.string.label_next)
                }
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }
}