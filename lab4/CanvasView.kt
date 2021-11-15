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
    private val shapeObjectsEditor: MyEditor = MyEditor(this)


    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shapeObjectsEditor.onPaint(canvas)

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
        when(id) {
            0, R.id.dot_button -> shapeObjectsEditor.start(PointShape())
            R.id.line_button -> shapeObjectsEditor.start(LineShape())
            R.id.rect_button -> shapeObjectsEditor.start(RectShape())
            R.id.oval_button -> shapeObjectsEditor.start(EllipseShape())
            R.id.oval_line_button -> shapeObjectsEditor.start(EllipseLineShape())
            R.id.cube_button -> shapeObjectsEditor.start(CubeShape())
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