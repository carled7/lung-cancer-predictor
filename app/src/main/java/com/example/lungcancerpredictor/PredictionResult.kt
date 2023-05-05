package com.example.lungcancerpredictor

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PredictionResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prediction_result)
        println("EXTRAS: ")
        println(intent.extras)
        val param = intent.getStringExtra("myParam")
        println("PARAM: $param")
        if (param != null) {
            println(param.toInt() == 1)
        }
        if (param != null) {
            val outText = findViewById<TextView>(R.id.result)
                if(param.toInt() == 1){
                    outText.text = "According to this model, it is likely this user is going to be develop lung cancer in the future"
                }else{
                    outText.text = "According to this model, it is unlikely this user is going to be develop lung cancer in the future"
                }

        }
    }
}

