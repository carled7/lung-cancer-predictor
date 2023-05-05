package com.example.lungcancerpredictor

import HttpPostTask
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener { callAPI(this) }
    }

    private fun callAPI(context: Context){
        val age = findViewById<TextInputLayout>(R.id.age).editText?.text.toString()

//        val female = findViewById<RadioButton>(R.id.female).isSelected
//        val male = findViewById<RadioButton>(R.id.male).isSelected

        val sex = 'M'

        val smoking = if(findViewById<SwitchMaterial>(R.id.smoking).isChecked) 1 else 0
        val yellow = if(findViewById<SwitchMaterial>(R.id.yellowFingers).isChecked) 1 else 0
        val anxiety = if(findViewById<SwitchMaterial>(R.id.anxiety).isChecked) 1 else 0
        val peer = if(findViewById<SwitchMaterial>(R.id.peerPressure).isChecked) 1 else 0
        val chronic = if(findViewById<SwitchMaterial>(R.id.chronicDisease).isChecked) 1 else 0
        val fatigue = if(findViewById<SwitchMaterial>(R.id.fatigue).isChecked) 1 else 0
        val allergy = if(findViewById<SwitchMaterial>(R.id.allergy).isChecked) 1 else 0
        val wheezing = if(findViewById<SwitchMaterial>(R.id.wheezing).isChecked) 1 else 0
        val alcohol = if(findViewById<SwitchMaterial>(R.id.alcohol).isChecked) 1 else 0
        val coughing = if(findViewById<SwitchMaterial>(R.id.coughing).isChecked) 1 else 0
        val shortness = if(findViewById<SwitchMaterial>(R.id.shortness).isChecked) 1 else 0
        val swallowing = if(findViewById<SwitchMaterial>(R.id.swallowing).isChecked) 1 else 0
        val chest = if(findViewById<SwitchMaterial>(R.id.chestPain).isChecked) 1 else 0

        val data = "{\"data\": [\"$sex\", $age, $smoking, $yellow, $anxiety, $peer, $chronic, $fatigue, $allergy, $wheezing, $alcohol, $coughing, $shortness, $swallowing, $chest]}"
//        val url = "https://example.com/api/post"
        println(data)
        HttpPostTask(data) { response ->
            // handle response here
            val bundle = Bundle()
            bundle.putString("myParam", response[14].toString())
            println(response[14])
            val i = Intent(this@MainActivity, PredictionResult::class.java)
            i.putExtra("myParam", response[14].toString())
            startActivity(i)
//            Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
        }.execute()

    }
}