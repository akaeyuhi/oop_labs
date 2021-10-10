package com.example.lab2oop

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.RadioGroup

class CanvasView @JvmOverloads constructor(context: Context,
                                           attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()
    private val shapeObjectsEditor: ShapeObjectsEditor = ShapeObjectsEditor(this)


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

    fun selectEditor(radioGroup: RadioGroup): Int {
        when (radioGroup.checkedRadioButtonId) {
            R.id.buttonDot -> shapeObjectsEditor.startPointEditor()
            R.id.buttonLine -> shapeObjectsEditor.startLineEditor()
            R.id.buttonRect -> shapeObjectsEditor.startRectEditor()
            R.id.buttonElipse -> shapeObjectsEditor.startEllipseEditor()
        }
        return radioGroup.checkedRadioButtonId
    }

    fun clearCanvas() {
        shapeObjectsEditor.clearShapes()
        invalidate()
    }

}