package com.example.mealordering

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SideDishesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_side_dishes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnDone = findViewById<Button>(R.id.btnDoneS)
        val radioGroupS = findViewById<RadioGroup>(R.id.radioGroupS)
        var selected=""
        val type = intent.getStringExtra("Type")

        btnDone.setOnClickListener {
            selected=when(radioGroupS.checkedRadioButtonId){
                R.id.radioBtnFries -> "Fries"
                R.id.radioBtnSalad -> "Salad"
                R.id.radioButCornCup -> "Corn Cup"
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