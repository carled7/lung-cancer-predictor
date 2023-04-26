package com.example.lungcancerpredictor

import HttpPostTask
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener { callAPI(this) }
    }

    private fun callAPI(context: Context){
//        val data = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}"
//        val url = "https://example.com/api/post"
        HttpPostTask() { response ->
            // handle response here
            println(response)
            Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
        }.execute()
    }
}