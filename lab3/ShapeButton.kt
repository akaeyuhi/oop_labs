package com.example.lab3oop

import android.content.Context
import android.graphics.Color
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.TooltipCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat

class ShapeButton(private val tooltipText: String, val buttonId: Int, private val buttonLayout: ImageButton)  {

    init {
        TooltipCompat.setTooltipText(buttonLayout, tooltipText)
    }

    private fun showToast(appContext: Context) {
        Toast.makeText(appContext, "Режим: ${tooltipText}", Toast.LENGTH_SHORT).show()
    }

    fun onClick(canvas: CanvasView, context: Context): Int {
        canvas.selectEditor(buttonId)
        showToast(context)
        return buttonId
    }

    fun onTouchEvent (event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                buttonLayout.background.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.GRAY,
                        BlendModeCompat.SRC_ATOP
                    )
                buttonLayout.invalidate()
            }
            MotionEvent.ACTION_UP -> {
                buttonLayout.background.clearColorFilter()
                buttonLayout.invalidate()
            }
        }
        return false
    }

}