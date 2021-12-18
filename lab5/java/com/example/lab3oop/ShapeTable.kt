package com.example.lab3oop

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.N)
class ShapeTable @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TableLayout(context, attrs) {
    private var shapes: ArrayList<Shape> = arrayListOf()
    private var rows: ArrayList<TableRow> = arrayListOf()
    private var stringRows: ArrayList<String> = arrayListOf()


    private fun newRow(shape: Shape): TableRow? {
        var row: TableRow? = null
        if (shape.id != null) {
            row = generateRow(shape.toString().split(' '))
            rows.add(row)
            addView(row)
        }

        return row
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun generateRow(strings: List<String>): TableRow {
        stringRows.add(strings.joinToString(':'.toString()))
        val tableRow = TableRow(context)
        strings.forEach {
            val text = TextView(context)
            text.text = it
            tableRow.addView(
                text, TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f
                )
            )
        }
        return tableRow

    }

    private fun generateButtons() {
        rows.forEach {
            genButton(it)
        }
    }

    private fun genButton(row: TableRow) {
        val button = Button(context)
        button.setOnClickListener {
            clickHandler(row)
        }
        button.text = "x"
        row.addView(
            button, TableRow.LayoutParams(
                50, TableRow.LayoutParams.WRAP_CONTENT, 0.5f
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickHandler(tableRow: TableRow) {
        val id: String = (tableRow.getChildAt(0) as TextView).text as String
        MyEditorManager.notify("table_delete", id.toInt())
        removeView(tableRow)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun render() {
        removeAllViews()
        addView(generateRow(listOf("id", "TypeOf", "x1", "y1", "x2", "y2", "Delete")))
        shapes.forEach {
            newRow(it)
        }
        generateButtons()
    }

    fun saveText() {
        TableReader.textToFile(stringRows)
    }

    fun update(event: String, data: Shape?) {

        when (event) {
            "shape_update" -> {
                if (data != null) {
                    shapes.add(data)
                    newRow(data)?.let { genButton(it) }
                }

            }
            "shape_delete-all" -> {
                shapes.clear()
                stringRows.clear()
                render()
            }
        }


    }

    init {
        render()
    }
}