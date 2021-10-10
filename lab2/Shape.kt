package com.example.lab2oop

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint


abstract class Shape {
    protected var xs1: Float = 0F
    protected var xs2: Float = 0F
    protected var ys1: Float = 0F
    protected var ys2: Float = 0F
    var temp: Boolean = false
    abstract fun show(canvas: Canvas, paint: Paint)
    fun set(x1: Float, y1: Float, x2: Float, y2: Float, isTemp: Boolean): Shape {
        xs1 = x1
        xs2 = x2
        ys1 = y1
        ys2 = y2
        temp = isTemp
        return this
    }
    open fun setStyle(paint: Paint, isTemp: Boolean) {
        paint.strokeWidth = 5.0f
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        if(isTemp) {
            paint.strokeWidth = 2.0f
            paint.pathEffect = DashPathEffect(floatArrayOf(1f, 5f), 7F)
        }
        else {
            paint.pathEffect = null
        }
    }

}

class PointShape: Shape(){
    override fun show(canvas: Canvas, paint: Paint) {
        setStyle(paint, temp)
        canvas.drawPoint(xs1, ys1, paint)
    }
}

class LineShape: Shape() {
    override fun show(canvas: Canvas, paint: Paint) {
        setStyle(paint, temp)
        canvas.drawLine(xs1, ys1, xs2, ys2, paint)
    }
}

class RectShape: Shape() {
    override fun show(canvas: Canvas, paint: Paint) {
        setStyle(paint, temp)
        canvas.drawRect(xs1, ys1, xs2, ys2, paint)
    }
}

class EllipseShape: Shape() {
    private fun setBackground(paint: Paint, canvas: Canvas) {
        paint.style = Paint.Style.FILL
        paint.color = Color.CYAN
        canvas.drawOval(xs1, ys1, xs2, ys2, borderPaint)

    }

    private val borderPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5F
        color = Color.BLACK
    }

    override fun show(canvas: Canvas, paint: Paint) {
        if(temp){
            setStyle(paint, temp)
        }
        else {
            setBackground(paint, canvas)
        }
        canvas.drawOval(xs1, ys1, xs2, ys2, paint)
    }
}