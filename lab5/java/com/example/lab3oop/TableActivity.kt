package com.example.lab3oop

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class TableActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""
        val canvasView: CanvasView = findViewById(R.id.paintField)
        val table: ShapeTable = findViewById(R.id.shapeTable)
        TableManager.subscribe("shape_update", table)
        TableManager.subscribe("shape_delete-all", table)
        MyEditorManager.subscribe("table_delete", MyEditor)

        Toolbar(findViewById(R.id.toolbar_layout), canvasView, applicationContext)
        findViewById<Button>(R.id.to_table_button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.clearButton).setOnClickListener {
            canvasView.clearCanvas()
            TableManager.notify("shape_delete-all", null)
        }
        findViewById<Button>(R.id.loadButton).setOnClickListener {
            MyEditor.parseData()
            Toast.makeText(this, "Завантежено з файлу", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.save).setOnClickListener {
            table.saveText()
            Toast.makeText(this, "Збережено", Toast.LENGTH_SHORT).show()
        }
    }
}