package com.example.lab6oop

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Lab 6 OOP"
        findViewById<Button>(R.id.submitButton).setOnClickListener {
            onClick(it)
        }
    }

    private fun onClick(view: View) {
        val launchIntent = applicationContext.packageManager
            .getLaunchIntentForPackage("com.example.object2")
        if (launchIntent != null) {
            val editTextN =
                this.findViewById<EditText>(R.id.editN).text.toString().toInt()
            val editTextMin =
                this.findViewById<EditText>(R.id.editMin).text.toString().toDouble()
            val editTextMax =
                this.findViewById<EditText>(R.id.editMax).text.toString().toDouble()
            if (editTextMin <= editTextMax) {
                launchIntent.putExtra("N", editTextN)
                launchIntent.putExtra("Min", editTextMin)
                launchIntent.putExtra("Max", editTextMax)
                startActivity(launchIntent)
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Wrong input",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                applicationContext,

                " launch Intent not available",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
