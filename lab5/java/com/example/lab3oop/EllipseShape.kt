package com.example.lab3oop

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

open class EllipseShape(id: Int?) : Shape(id) {

    override var xs1: Float = 0F
    override var xs2: Float = 0F
    override var ys1: Float = 0F
    override var ys2: Float = 0F

    private val backgroundPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.CYAN
    }

    private fun setBackground(canvas: Canvas) {
        canvas.drawOval(xs1, ys1, xs2, ys2, backgroundPaint)
    }

    override fun show(canvas: Canvas) {
        setStyle()
        if(!temp) setBackground(canvas)
        canvas.drawOval(xs1, ys1, xs2, ys2, paint)
    }
}