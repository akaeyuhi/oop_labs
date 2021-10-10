package com.example.lab2oop

import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build

abstract class Editor(protected var shapes: ArrayList<Shape>, val context: CanvasView) {
    protected var x1: Float = 0F
    protected var x2: Float = 0F
    protected var y1: Float = 0F
    protected var y2: Float = 0F

    abstract fun onLBdown(x: Float, y: Float)
    abstract fun onLBup(x: Float, y: Float)
    abstract fun onMouseMove(x: Float, y: Float)
    abstract fun onPaint(canvas: Canvas, paint: Paint)

}

open class ShapeEditor(shapes: ArrayList<Shape>, context: CanvasView): Editor(shapes, context) {

    override fun onLBdown(x: Float, y: Float) {
        x1 = x
        y1 = x
        x2 = x
        y2 = y
    }

    override fun onLBup(x: Float, y: Float) {}

    override fun onMouseMove(x: Float, y: Float) {}

    override fun onPaint(canvas: Canvas, paint: Paint) {
        shapes.forEach {
            it.show(canvas, paint)
        }
    }
    protected fun addShape(shape: Shape) {
        shapes.add(shape)
    }

}

class PointEditor(shapes: ArrayList<Shape>, context: CanvasView) : ShapeEditor(shapes, context) {

    override fun onLBup(x: Float, y: Float) {
        val shape: Shape = PointShape()
        x1 = x
        y1 = y
        shape.set(x, y, x, y, false)
        addShape(shape)
    }
}

class LineEditor(shapes: ArrayList<Shape>, context: CanvasView): ShapeEditor(shapes, context) {

    override fun onLBup(x: Float, y: Float) {
        x2 = x
        y2 = y
        val shape: Shape = LineShape()
        shape.set(x1, y1, x2, y2, false)
        addShape(shape)
    }

    override fun onMouseMove(x: Float, y: Float) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shapes.removeIf { it.temp }
        }
        val tempShape = LineShape()
        tempShape.set(x1, y1, x, y, true)
        addShape(tempShape)
        context.invalidate()
    }

}

class RectEditor(shapes: ArrayList<Shape>, context: CanvasView): ShapeEditor(shapes, context) {

    override fun onLBup(x: Float, y: Float) {
        x2 = x
        y2 = y
        val shape: Shape = RectShape()
        shape.set(x1, y1, x2, y2, false)
        addShape(shape)
    }

    override fun onMouseMove(x: Float, y: Float) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shapes.removeIf { it.temp }
        }
        val tempShape = RectShape()
        tempShape.set(y1, x1, x, y, true)
        addShape(tempShape)
        context.invalidate()
    }


}

class EllipseEditor(shapes: ArrayList<Shape>, context: CanvasView): ShapeEditor(shapes, context) {

    override fun onLBup(x: Float, y: Float) {
        x2 = x
        y2 = y
        val shape: Shape = EllipseShape()
        shape.set(x1, y1, x2, y2, false)
        addShape(shape)
    }

    override fun onMouseMove(x: Float, y: Float) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shapes.removeIf { it.temp }
        }
        val tempShape = EllipseShape()
        tempShape.set(x1, y1, x, y, true)
        shapes.add(tempShape)
        context.invalidate()
    }


}