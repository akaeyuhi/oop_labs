package com.example.object2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode
import kotlin.random.Random.Default.nextDouble

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
        initTable()
        Toast.makeText(this, "${array.indices}", Toast.LENGTH_LONG).show()
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
        var text = ""
        for (i in array) {
            text += "$i "
        }
        textOut = text
    }

    @SuppressLint("SetTextI18n")
    private fun initTable() {
        val table = findViewById<TableLayout>(R.id.table)
        val firstRow = TableRow(this)
        val text = TextView(this)
        text.text = "Index\tValue"
        firstRow.addView(text, TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f)
        )
        table.addView(firstRow)
        array.forEachIndexed { i, it ->
            val row = TableRow(this)
            val textInsert = "$i\t$it"
            val view = TextView(this)
            view.text = textInsert
            row.addView(view, TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f)
            )
            table.addView(row)
        }
    }

    private fun onClick(view: View) {
        val launchIntent = applicationContext.packageManager
            .getLaunchIntentForPackage("com.example.object3")


        if (launchIntent != null) {
            val resultIntent = Intent()
            resultIntent.putExtra("text0", textOut)
            setResult(1, resultIntent);
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