package com.example.ordermeal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainMealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_meal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val radgroupM = findViewById<RadioGroup>(R.id.radioGroupM)
        //radgroupM.setOnCheckedChangeListener()

        //radgroupM.checkedRadioButtonId

        val btnDone = findViewById<Button>(R.id.btnDoneM)
        btnDone.setOnClickListener {

            // 點擊 btnDoneM 之後 , 確認使用者選的 RadioGroupM 裡面點的 radiobutton
            val selected =when(radgroupM.checkedRadioButtonId){
                R.id.btnBurger -> "Bugger"
                R.id.btnFriedChicken -> "Fried Chicken"
                R.id.btnChickenNug -> "Chicken Nuggets"
                else -> "一"
            }

//            val i = intent.putExtra("Key",x)
            val b = Bundle()
            b.putString("Type","main")
            b.putString("Key",selected)
            val i = Intent()
//            i.putExtra("Key",selected)  回傳一個資料
            i.putExtras(b)   // 回傳多個
            setResult(RESULT_OK,i)
            finish()
        }
    }
}


//            setResult(Activity.RESULT_OK, Intent().apply {
//                putExtra("key", "some data")
//            })
//            finish()