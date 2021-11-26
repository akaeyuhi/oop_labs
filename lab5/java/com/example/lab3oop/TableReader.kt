package com.example.lab3oop

import android.annotation.SuppressLint
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object TableReader {

    private const val fileName: String = "table.txt"

    @SuppressLint("SdCardPath")

    fun textToFile(stringRows: ArrayList<String>) {
        val text = stringRows.joinToString('\n'.toString())
        var fos: FileOutputStream? = null
        try {
            fos = File("/data/data/com.example.lab3oop/files/$fileName").outputStream()
            fos.write(text.toByteArray())

        } catch (ex: IOException) {
            ex.message?.let { Log.e("ERROR", it) }
        } finally {
            try {
                fos?.close()
            } catch (ex: IOException) {
                ex.message?.let { Log.e("ERROR", it) }
            }
        }
    }

    @SuppressLint("SdCardPath")
    fun readFile(): ArrayList<Shape> {
        val shapes:  ArrayList<Shape> = arrayListOf()
        File("/data/data/com.example.lab3oop/files/$fileName").forEachLine { it ->
            val names: List<String> = it.split(":")
            if("id" in names) return@forEachLine
            val newShape: Shape? = MyEditor.createShapeFromString(names[1], names[0].toInt())
            val coords: List<Float> = names.drop(2).map { it.toFloat() }
            newShape?.set(coords[0], coords[1], coords[2], coords[3], false)
            if (newShape != null) {
                shapes.add(newShape)
            }
        }
        return shapes

    }
}
