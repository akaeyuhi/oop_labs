package com.example.lab3oop

import android.graphics.Canvas

class EllipseLineShape: Shape() {
    override var xs1: Float = 0F
    override var xs2: Float = 0F
    override var ys1: Float = 0F
    override var ys2: Float = 0F
    private var ellipses = Array(2) { EllipseShape() }
    private var line = LineShape()

    override fun show(canvas: Canvas) {
        ellipses[0].set(xs1 - 25, ys1 - 25, xs1 + 25, ys1 + 25, temp)
        ellipses[1].set(xs2 - 25, ys2 - 25, xs2 + 25, ys2 + 25, temp)
        line.set(xs1, ys1, xs2, ys2, temp)
        line.show(canvas)
        ellipses.forEach {
            it.show(canvas)
        }
    }
}