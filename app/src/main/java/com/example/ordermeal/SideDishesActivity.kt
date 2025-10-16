package com.example.ordermeal

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

        val radgroupS = findViewById<RadioGroup>(R.id.radioGroupS)

        var selected = ""

        val btnDone = findViewById<Button>(R.id.btnDoneS)
        btnDone.setOnClickListener {
            selected=when(radgroupS.checkedRadioButtonId){
                R.id.btnFries -> "Fries"
                R.id.btnSalad -> "Salad"
                R.id.btnCornCup -> "Corn Cup"
                else -> "一"
            }

            // 接收 MainActivity 傳來的資料 => intent.putExtra("Type","side")  => "side"
            val type= intent.getStringExtra("Type")

            val b = Bundle()

// b.putString("Type","side")  沒接收，這裡寫新的傳入  ， putString 要傳2個參數("Key"(對照值) , value)
            b.putString("Type",type)

            b.putString("Key",selected)
            val i = Intent()
            i.putExtras(b)
            setResult(RESULT_OK,i)
            finish()
        }
    }
}