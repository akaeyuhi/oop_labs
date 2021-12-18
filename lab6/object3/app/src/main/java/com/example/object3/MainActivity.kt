package com.example.object3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class MainActivity : AppCompatActivity() {
    private lateinit var array: Array<Double>
    private var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Object 3 Lab6"
        text = intent.getStringExtra("text0").toString()
        getArray()
        initGraph()
    }

    private fun getArray() {
        val text = ArrayList<String>(text.split(","))
        text.removeLast()
        array = Array(text.size) { 1.1 }
        for(i in 0 until text.size - 1) {
            array[i] = text[i].toDouble()
        }
        array.sort()
    }

    private fun initGraph() {
        val dataArray: Array<DataPoint> = Array(array.size) { DataPoint(0.0, 0.0) }
        for(i in array.indices) {
            dataArray[i] = DataPoint(i.toDouble(), array[i])
        }
        val graph = findViewById<View>(R.id.labGraph) as GraphView
        val series: LineGraphSeries<DataPoint> = LineGraphSeries(dataArray)
        graph.addSeries(series)
    }

}