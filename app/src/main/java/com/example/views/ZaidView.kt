package com.example.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class ZaidView @JvmOverloads
constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0, private val defStyleRes: Int = 0)
    : View (ctx, attributeSet, defStyleAttr, defStyleRes)
{


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(measuredWidth,measuredHeight)
    }
    var path = Path()
    var firstPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#000000")
        style = Paint.Style.FILL
        strokeWidth = 5f

        this.isAntiAlias = true
    }
    var secondPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#a9a9a9")
        style = Paint.Style.FILL
        strokeWidth = 5f

        this.isAntiAlias = true
    }






    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val center2x=width/2f
        val center2y = height/2f
        canvas?.drawCircle(center2x,center2y,200f,secondPaint)
        invalidate()

    }

}