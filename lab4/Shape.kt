package com.example.lab3oop

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint


abstract class Shape {
    abstract var xs1: Float
    abstract var xs2: Float
    abstract var ys1: Float
    abstract var ys2: Float
    var temp: Boolean = false
    protected val paint: Paint = Paint()

    abstract fun show(canvas: Canvas)
    fun set(x1: Float, y1: Float, x2: Float, y2: Float, isTemp: Boolean): Shape {
        xs1 = x1
        xs2 = x2
        ys1 = y1
        ys2 = y2
        temp = isTemp
        return this
    }

    protected open fun setStyle() {
        paint.strokeWidth = 5.0f
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        if(temp) {
            paint.strokeWidth = 2.0f
            paint.pathEffect = DashPathEffect(floatArrayOf(1f, 5f), 7F)
        }
        else {
            paint.pathEffect = null
        }
    }

}