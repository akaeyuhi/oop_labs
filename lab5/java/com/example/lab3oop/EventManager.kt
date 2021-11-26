package com.example.lab3oop

import android.os.Build
import androidx.annotation.RequiresApi

object EventManager {
    private val listeners = hashMapOf<String, ShapeTable>()

    fun subscribe(eventType: String, listener: ShapeTable) {
        listeners[eventType] = listener
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun unsubscribe(eventType: String, listener: ShapeTable)  {
        listeners.remove(eventType, listener)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun notify(eventType: String, data: Shape?) {
        listeners.forEach {
            if(it.key === eventType) {
                it.value.update(eventType, data)
            }
        }
    }
}