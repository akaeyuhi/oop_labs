package com.example.lab3oop

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.os.Build
import android.view.MotionEvent
import androidx.annotation.RequiresApi

/*class MyEditor private constructor() {*/
object MyEditor {

    private var currShape: Shape? = null
    private var context: CanvasView? = null

    private val shapes: ArrayList<Shape> = arrayListOf()
    private var x1: Float = 0F
    private var x2: Float = 0F
    private var y1: Float = 0F
    private var y2: Float = 0F


    /*
    companion object {
        private var inst: MyEditor? = null

        fun getInstance(): MyEditor? {
         if(inst != null) return inst
         else inst = MyEditor()
         return inst
        }
    }
    */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun addShape(shape: Shape) {
        shapes.add(shape)
        TableManager.notify("shape_update", shape)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun addShapes(newShapes: ArrayList<Shape>) {
        newShapes.forEach { addShape(it) }
    }

    private fun clearTempShapes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shapes.removeIf { it.temp }
        }
    }

    fun setContext(cont: CanvasView) {
        context = cont
    }

    fun start(currentShape: Shape) {
        currShape = currentShape
    }

    fun onLBdown(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        x1 = x
        y1 = y
        x2 = x
        y2 = y
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onLBup(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        x2 = x
        y2 = y
        val shape: Shape? = currShape?.let { getTempInst(it, shapes.size) }
        shape?.set(x1, y1, x2, y2, false)
        if (shape != null) {
            addShape(shape)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onMouseMove(event: MotionEvent) {
        val (x, y) = getFingerPos(event)
        clearTempShapes()
        val tempShape = currShape?.let { getTempInst(it, null) }
        tempShape?.set(x1, y1, x, y, true)
        if (tempShape != null) {
            addShape(tempShape)
        }
        context?.invalidate()
    }

    fun onPaint(canvas: Canvas) {
        shapes.forEach {
            it.show(canvas)
        }
    }

    fun clearShapes() {
        shapes.clear()
    }

    fun getShapesArray(): ArrayList<Shape> {
        val arr = arrayListOf<Shape>()
        arr.addAll(shapes)
        return (arr)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteShape(id: Int) {
        shapes.removeIf { it.id == id }
        context?.invalidate()
    }

    private fun getFingerPos(event: MotionEvent): Array<Float> {
        return arrayOf(event.x, event.y)
    }

    private fun getTempInst(shape: Shape, id: Int?): Shape? {
        var returnShape: Shape? = null
        when(shape){
            is PointShape -> returnShape = PointShape(id)
            is LineShape -> returnShape = LineShape(id)
            is RectShape -> returnShape = RectShape(id)
            is EllipseShape -> returnShape = EllipseShape(id)
            is EllipseLineShape -> returnShape = EllipseLineShape(id)
            is CubeShape -> returnShape = CubeShape(id)
        }
        return returnShape
    }

    fun createShapeFromString(string: String, id: Int?): Shape? {
        var returnShape: Shape? = null
        when(string){
            "PointShape" -> returnShape = PointShape(id)
            "LineShape" -> returnShape = LineShape(id)
            "RectShape" -> returnShape = RectShape(id)
            "EllipseShape" -> returnShape = EllipseShape(id)
            "EllipseLineShape" -> returnShape = EllipseLineShape(id)
            "CubeShape" -> returnShape = CubeShape(id)
        }
        return returnShape
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SdCardPath")
    fun parseData() {
        addShapes(TableReader.readFile())
        context?.invalidate()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun update(event: String, data: Int) {
        deleteShape(data)
    }

}