package com.example.mealordering

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DrinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_drink)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnDone = findViewById<Button>(R.id.btnDoneD)
        val radioGroupD = findViewById<RadioGroup>(R.id.radioGroupD)
        var selected=""
        val type = intent.getStringExtra("Type")

        btnDone.setOnClickListener {
            selected=when(radioGroupD.checkedRadioButtonId){
                R.id.radioBtnCola -> "Cola"
                R.id.radioBtnIcedTea -> "Iced Tea"
                R.id.radioButWater -> "Water"
                else -> "一"
            }
            val b = Bundle()
            b.putString("Type",type)
            b.putString("value",selected)
            val i = Intent()
            i.putExtras(b)
            setResult(RESULT_OK,i)
            finish()
        }

    }
}