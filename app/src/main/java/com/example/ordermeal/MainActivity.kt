package com.example.ordermeal

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {



    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->

        if(result.resultCode == Activity.RESULT_OK){
            val tvMain = findViewById<TextView>(/* id = */ R.id.tvMain)
            val tvSide = findViewById<TextView>(R.id.tvSides)
            val tvDrink = findViewById<TextView>(R.id.tvDrink)


            val intent = result.data
            val Type = intent?.getStringExtra("Type")
            val Selection = intent?.getStringExtra("Key")


            when(Type){
                "main" -> tvMain.text="Main:"+Selection
                "side" -> tvSide.text="Sides:"+Selection
                "drink" -> tvDrink.text="Drink:"+Selection
            }
        }
    }

    private val startForConfirm = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result: ActivityResult ->
        val tvConfirmResult = findViewById<TextView>(R.id.tvConfirmResult)
        val intent = result.data
        val result = intent?.getStringExtra("Key")
        tvConfirmResult.text=result
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //============================================================================
        val tvMain = findViewById<TextView>(/* id = */ R.id.tvMain)
        val tvSide = findViewById<TextView>(R.id.tvSides)
        val tvDrink = findViewById<TextView>(R.id.tvDrink)
        //===========================================================================

        val butMainMeal = findViewById<Button>(R.id.btnMainMeal)
        butMainMeal.setOnClickListener {

            // 使用者典籍btnMainMeal按鈕後  意圖開啟 MainMealActivity ，  並會回傳結果
            val intent = Intent(this, MainMealActivity::class.java)
            startForResult.launch(intent)

        }

        val butSideDishes = findViewById<Button>(R.id.btnSideDishes)
        butSideDishes.setOnClickListener {
            val intent = Intent(this, SideDishesActivity::class.java)
            intent.putExtra("Type","side")  // 把資料傳去 SideDishesActivity
            startForResult.launch(intent)
        }

        val butDrink = findViewById<Button>(R.id.btnDrink)
        butDrink.setOnClickListener {
            val intent = Intent(this, DrinkActivity::class.java)
            intent.putExtra("Type","drink")
            startForResult.launch(intent)
        }

        val btnConfirm = findViewById<Button>(R.id.btnOrder)
        btnConfirm.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            val b = Bundle()
            b.putString("Main",tvMain.text.toString())
            b.putString("Side",tvSide.text.toString())
            b.putString("Drink",tvDrink.text.toString())
            intent.putExtras(b)
            startForConfirm.launch(intent)
        }
    }
}