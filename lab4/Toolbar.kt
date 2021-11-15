package com.example.lab3oop

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.view.children

class Toolbar(
        layout: LinearLayout?,
        private val canvas: CanvasView,
        private val context: Context
    ) {

    private val buttons: ArrayList<ShapeButton> = arrayListOf()
    private var children: Sequence<View>? = layout?.children
    var clickedButtonId: Int = 0


    private fun getButtonById(id: Int): ShapeButton? {
        return buttons.find { it.buttonId == id}
    }

    private fun initButtons() {
        children?.forEach {
            buttons.add(ShapeButton(it.contentDescription.toString(), it.id, it as ImageButton))
        }
    }


    private fun setOnClickHandlers() {
        children?.forEach { it ->
            it.setOnClickListener {
                clickedButtonId = getButtonById(it.id)?.onClick(canvas, context)!!
            }
        }
    }

    private fun setOnTouchHandlers() {
        children?.forEach { it ->
            it.setOnTouchListener { view, event ->
                getButtonById(it.id)?.onTouchEvent(event)
                view.performClick()
            }
        }
    }

    init {
        initButtons()
        setOnClickHandlers()
        setOnTouchHandlers()
    }

}