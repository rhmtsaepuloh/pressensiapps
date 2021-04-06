package com.folkatech.pressensiapps.common.library

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.view.View


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@SuppressLint("ViewConstructor")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class CustomViewWithTypeFaceSupport(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var paint: Paint? = null
    private var textBounds: Rect? = null
    private var width: Int? = 0
    private var height: Int? = 0

    init {
        init()
    }

    private fun init() {
        paint = Paint()
        paint!!.textSize = 50f
        textBounds = Rect()
    }

    override fun onDraw(canvas: Canvas) {
        val text = "This is a custom view with setTypeface support"
        val fm = paint!!.fontMetrics
        paint!!.getTextBounds(text, 0, text.length, textBounds)

        width = textBounds!!.left + textBounds!!.right + paddingLeft + paddingRight
        height = (Math.abs(fm.top) + fm.bottom).toInt()

        canvas.drawText(text, 0f, -fm.top + paddingTop, paint!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(width!!, height!!)
    }

    /**
     * Used by Calligraphy to change view's typeface
     */
    fun setTypeface(tf: Typeface) {
        paint!!.typeface = tf
        invalidate()
    }
}