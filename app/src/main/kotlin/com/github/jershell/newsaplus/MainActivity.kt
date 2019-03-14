package com.github.jershell.newsaplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.jetbrains.anko.toast


@Serializable
data class Data(val a: Int, @Optional val b: String = "42")

class MainActivity : AppCompatActivity() {

    suspend fun runWhile() {
        for (idx in 1..10) {
            label.text = "now ${idx}"
            delay(1000L)
        }
    }

    fun onPressButton(view : View) {
        toast("onPressButton")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // serializing objects
        val jsonData = Json.stringify(Data.serializer(), Data(42))
        // serializing lists
        val jsonList = Json.stringify(Data.serializer().list, listOf(Data(42)))
        println(jsonData) // {"a": 42, "b": "42"}
        println(jsonList) // [{"a": 42, "b": "42"}]

        // parsing data back
        val obj = Json.parse(Data.serializer(), """{"a":42}""")
        println(obj) // Data(a=42, b="42")

        label.text = "default"


        GlobalScope.launch(Dispatchers.Main) {
            runWhile()
            toast("Hi there!")
        }
    }
}
