package com.example.lab3oop

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""
        val canvasView: CanvasView = findViewById(R.id.paintField)
        val clearButton: Button = findViewById(R.id.clearButton)
        Toolbar(findViewById(R.id.toolbar_layout), canvasView, applicationContext)
        canvasView.selectEditor(0)
        clearButton.setOnClickListener {
            canvasView.clearCanvas()
        }
    }



}

