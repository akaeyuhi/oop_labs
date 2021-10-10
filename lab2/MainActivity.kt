package com.example.lab2oop

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val canvasView: CanvasView = findViewById(R.id.paintField)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val clearButton: Button = findViewById(R.id.clearButton)
        setMode(canvasView, radioGroup)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            setMode(canvasView, group)
        }
        clearButton.setOnClickListener {
            canvasView.clearCanvas()
        }
    }

    private fun setBarText(text: String) {
        supportActionBar?.title = text
    }

    private fun setMode(canvas: CanvasView, radioGroup: RadioGroup) {
        setBarText(findViewById<RadioButton>(
            canvas.selectEditor(radioGroup))
            .text.toString()
        )
    }

}

