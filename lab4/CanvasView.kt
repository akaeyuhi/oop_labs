package com.example.lab3oop

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView @JvmOverloads constructor(context: Context,
                                           attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()
    private val shapeObjectsEditor: MyEditor = MyEditor()


    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shapeObjectsEditor.onPaint(canvas, paint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                shapeObjectsEditor.onLBdown(event)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                shapeObjectsEditor.onMouseMove(event)
                return true
            }
            MotionEvent.ACTION_UP -> {
                shapeObjectsEditor.onLBup(event)
                performClick()
            }

        }
        invalidate()
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    fun selectEditor(id: Int) {
        var editor: ShapeEditor? = null
        val shapes = shapeObjectsEditor.getShapes()
        when(id) {
            0, R.id.dot_button -> editor = PointEditor(shapes, this)
            R.id.line_button -> editor = LineEditor(shapes, this)
            R.id.rect_button -> editor = RectEditor(shapes, this)
            R.id.oval_button -> editor = EllipseEditor(shapes, this)
            R.id.oval_line_button -> editor = EllipseLineEditor(shapes, this)
            R.id.cube_button -> editor = CubeEditor(shapes, this)
        }
        if (editor != null) {
            shapeObjectsEditor.start(editor)
        }
    }

    fun clearCanvas() {
        shapeObjectsEditor.clearShapes()
        invalidate()
    }

    init {
        selectEditor(0)
    }

}