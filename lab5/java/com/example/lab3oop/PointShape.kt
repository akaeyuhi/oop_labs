package com.example.lab3oop

import android.graphics.Canvas

class PointShape(id: Int?) : Shape(id){
    override var xs1: Float = 0F
    override var xs2: Float = 0F
    override var ys1: Float = 0F
    override var ys2: Float = 0F

    override fun show(canvas: Canvas) {
        setStyle()
        canvas.drawPoint(xs1, ys1, paint)
    }
}