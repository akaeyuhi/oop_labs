package com.example.lab3oop

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi

class CanvasView @JvmOverloads constructor(context: Context,
                                           attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    //val myEditor: MyEditor? = MyEditor.getInstance()


    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        MyEditor.onPaint(canvas)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                MyEditor.onLBdown(event)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                MyEditor.onMouseMove(event)
                return true
            }
            MotionEvent.ACTION_UP -> {
                MyEditor.onLBup(event)
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
            0, R.id.dot_button -> MyEditor.start(PointShape(null))
            R.id.line_button -> MyEditor.start(LineShape(null))
            R.id.rect_button -> MyEditor.start(RectShape(null))
            R.id.oval_button -> MyEditor.start(EllipseShape(null))
            R.id.oval_line_button -> MyEditor.start(EllipseLineShape(null))
            R.id.cube_button -> MyEditor.start(CubeShape(null))
        }
    }

    fun clearCanvas() {
        MyEditor.clearShapes()
        invalidate()
    }

    init {
        MyEditor.setContext(this)
        selectEditor(0)
    }

}