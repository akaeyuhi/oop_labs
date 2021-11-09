package com.example.lab3oop

import android.graphics.Canvas

class CubeShape: Shape() {
    override var xs1: Float = 0F
    override var xs2: Float = 0F
    override var ys1: Float = 0F
    override var ys2: Float = 0F
    private var rects = Array(2){ RectShape() }
    private var lines = Array(4){ LineShape() }

    override fun show(canvas: Canvas) {
        rects[0].set(xs1, ys1, xs2 - 100, ys2 + 100, temp)
        rects[1].set(xs1 + 100, ys1 - 100, xs2, ys2, temp)
        lines[0].set(rects[0].xs1, rects[0].ys1, rects[1].xs1, rects[1].ys1, temp)
        lines[1].set(rects[0].xs2, rects[0].ys2, rects[1].xs2, rects[1].ys2, temp)
        lines[2].set(rects[0].xs1, rects[0].ys2, rects[1].xs1, rects[1].ys2, temp)
        lines[3].set(rects[0].xs2, rects[0].ys1, rects[1].xs2, rects[1].ys1, temp)
        rects.forEach {
            it.show(canvas)
        }
        lines.forEach {
            it.show(canvas)
        }
    }
}