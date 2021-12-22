package com.example.lab6oop

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
        activityResultRegistry
    }

    private fun onClick(view: View) {
        val launchIntent = Intent(Intent.ACTION_MAIN);
        launchIntent.component = ComponentName("com.example.object2", "com.example.object2.MainActivity")
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
            startActivityForResult(launchIntent, 1)
        } else {
            Toast.makeText(
                applicationContext,
                "Wrong input",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == 1) {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                val intent = applicationContext.packageManager
                    .getLaunchIntentForPackage("com.example.object3")?.putExtra(
                        "text0",
                        data?.getStringExtra("text0").toString()
                    )
                    startActivity(intent)
                finish()
            }
        }
    }

}
