package com.example.ordermeal

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


        var selected=""
        val radgroupS = findViewById<RadioGroup>(R.id.radioGroupD)

        val btnDone = findViewById<Button>(R.id.btnDoneD)
        btnDone.setOnClickListener {
            selected=when(radgroupS.checkedRadioButtonId){
                R.id.btnCola -> "Cola"
                R.id.btnIcedTea -> "Iced Tea"
                R.id.btnWater -> "Water"
                else -> "一"
            }
            val type = intent.getStringExtra("Type")
            val b = Bundle()
            b.putString("Type",type)
            b.putString("Key",selected)

            val i = Intent()
            i.putExtras(b)
            setResult(RESULT_OK,i)
            finish()
        }
    }
}