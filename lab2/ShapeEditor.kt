package com.example.lab2oop

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

class ShapeObjectsEditor(private val context: CanvasView) {

    private var shapeEditor: ShapeEditor? = null

    private val shapes: ArrayList<Shape> = arrayListOf()

    fun startPointEditor() {
        shapeEditor = PointEditor(shapes, context)
    }

    fun startLineEditor() {
        shapeEditor = LineEditor(shapes, context)
    }

    fun startRectEditor() {
        shapeEditor = RectEditor(shapes, context)
    }

    fun startEllipseEditor() {
        shapeEditor = EllipseEditor(shapes, context)
    }

    fun onLBdown(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        shapeEditor?.onLBdown(x, y)
    }
    fun onLBup(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        shapeEditor?.onLBup(x, y)
    }
    fun onMouseMove(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        shapeEditor?.onMouseMove(x, y)
    }
    fun onPaint(canvas: Canvas, paint: Paint) {
        shapeEditor?.onPaint(canvas, paint)
    }

    fun clearShapes() {
        shapes.clear()
    }

    private fun getFingerPos(event: MotionEvent): Array<Float> {
        return arrayOf(event.x, event.y)
    }

}