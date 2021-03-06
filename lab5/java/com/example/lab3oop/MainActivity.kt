package com.example.lab3oop

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""
        val canvasView: CanvasView = findViewById(R.id.paintField)
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.popup, null, false)
        val popup =
            PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true)
        val color = ColorDrawable(Color.WHITE)
        color.alpha = 120
        popupView.background = color
        val table: ShapeTable = popupView.findViewById(R.id.shapeTable)
        TableManager.subscribe("shape_update", table)
        TableManager.subscribe("shape_delete-all", table)
        MyEditorManager.subscribe("table_delete", MyEditor)

        Toolbar(findViewById(R.id.toolbar_layout), canvasView, applicationContext)
        findViewById<Button>(R.id.clearButton).setOnClickListener {
            canvasView.clearCanvas()
            TableManager.notify("shape_delete-all", null)
        }
        findViewById<Button>(R.id.to_table_button).setOnClickListener {
            popup.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0)
        }
        findViewById<Button>(R.id.loadButton).setOnClickListener {
            MyEditor.parseData()
            Toast.makeText(this, "?????????????????????? ?? ??????????", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.toFullTable).setOnClickListener {
            val intent = Intent(this, TableActivity::class.java)
            startActivity(intent)
        }
        popupView.findViewById<Button>(R.id.save).setOnClickListener {
            table.saveText()
            Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show()
        }
    }
}

