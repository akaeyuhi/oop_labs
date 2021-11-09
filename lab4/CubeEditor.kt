package com.example.lab3oop

class CubeEditor(shapes: ArrayList<Shape>, context: CanvasView) : RectEditor(shapes, context) {

    override fun onLBup(x: Float, y: Float) {
        x2 = x
        y2 = y
        val shape: Shape = CubeShape()
        shape.set(x1, y1, x2, y2, false)
        addShape(shape)
    }

    override fun onMouseMove(x: Float, y: Float) {
        clearTempShapes()
        val tempShape = CubeShape()
        tempShape.set(x1, y1, x, y, true)
        addShape(tempShape)
        context.invalidate()
    }
}