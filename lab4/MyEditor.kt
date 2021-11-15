package com.example.lab3oop

import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.view.MotionEvent

class MyEditor(private val context: CanvasView) {

    private var currShape: Shape? = null

    private val shapes: ArrayList<Shape> = arrayListOf()
    private var x1: Float = 0F
    private var x2: Float = 0F
    private var y1: Float = 0F
    private var y2: Float = 0F

    private fun addShape(shape: Shape) {
        shapes.add(shape)
    }

    private fun clearTempShapes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shapes.removeIf { it.temp }
        }
    }

    fun start(currentShape: Shape) {
        currShape = currentShape
    }

    fun onLBdown(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        x1 = x
        y1 = x
        x2 = x
        y2 = y
    }

    fun onLBup(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        x2 = x
        y2 = y
        val shape: Shape? = currShape?.let { getTempInst(it) }
        shape?.set(x1, y1, x2, y2, false)
        if (shape != null) {
            addShape(shape)
        }
    }

    fun onMouseMove(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        clearTempShapes()
        val tempShape = currShape?.let { getTempInst(it) }
        tempShape?.set(x1, y1, x, y, true)
        if (tempShape != null) {
            addShape(tempShape)
        }
        context.invalidate()
    }

    fun onPaint(canvas: Canvas) {
        shapes.forEach {
            it.show(canvas)
        }
    }

    fun clearShapes() {
        shapes.clear()
    }


    private fun getFingerPos(event: MotionEvent): Array<Float> {
        return arrayOf(event.x, event.y)
    }

    private fun getTempInst(shape: Shape): Shape? {
        var returnShape: Shape? = null
        when(shape){
            is PointShape -> returnShape = PointShape()
            is LineShape -> returnShape = LineShape()
            is RectShape -> returnShape = RectShape()
            is EllipseShape -> returnShape = EllipseShape()
            is EllipseLineShape -> returnShape = EllipseLineShape()
            is CubeShape -> returnShape = CubeShape()
        }
        return returnShape
    }

}