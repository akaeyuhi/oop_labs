package com.example.object2

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION_CODES.N
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode
import kotlin.math.round
import kotlin.random.Random.Default.nextDouble
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    companion object {
        var n = 1
        var min = 0.0
        var max = 1.0

        fun newIntent(context: Context, N: Int, min: Int, max: Int): Intent {
            val detailIntent = Intent(context, MainActivity::class.java)
            detailIntent.putExtra("N", N)
            detailIntent.putExtra("Min", min)
            detailIntent.putExtra("Max", max)
            return detailIntent
        }
    }

    private lateinit var array: Array<Double>

    private var textOut = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Object 2 Lab6"
        n = intent.getIntExtra("N", n)
        min = intent.getDoubleExtra("Min", min)
        max = intent.getDoubleExtra("Max", max)
        array = Array(n) { 0.0 }
        createArray()
        textArray()
        findViewById<Button>(R.id.submitButton).setOnClickListener {
            onClick(it)
        }
    }

    private fun createArray() {
        for (i in 0 until n) {
            array[i] = nextDouble(min, max).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        }
        array.sort()

    }

    private fun textArray() {
        val textView: TextView = findViewById(R.id.vectorView)
        var text = ""
        for (i in array) {
            text += "$i, "
        }
        textView.text = text
        textOut = text
    }

    private fun onClick(view: View) {
        val launchIntent = applicationContext.packageManager
            .getLaunchIntentForPackage("com.example.object3")

        if (launchIntent != null) {
            launchIntent.putExtra("text0", textOut)
            startActivity(launchIntent)
            finish()
        } else {
            Toast.makeText(
                applicationContext,
                " launch Intent not available",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}