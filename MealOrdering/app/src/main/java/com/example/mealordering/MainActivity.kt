package com.example.mealordering

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {

            // 這裡是「另一個 Activity 結束回來」後的處理
            val data = result.data
            val type = data?.getStringExtra("Type") // 由哪個Activity來的
            val value = data?.getStringExtra("value")

            val tvMain = findViewById<TextView>(R.id.tvMain)
            val tvSide = findViewById<TextView>(R.id.tvSide)
            val tvDrink = findViewById<TextView>(R.id.tvDrink)

            when (type) {
                "main" -> tvMain.text="Main:"+value
                "side" -> tvSide.text="Sides:"+value
                "drink" -> tvDrink.text="Drink:"+value

            }
        }
    }

    private val startForConfirm = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val submittedOrder = findViewById<TextView>(R.id.tvSubmittedOrder)
            val data = result.data
            val resOder = data?.getStringExtra("Result")
            submittedOrder.text = resOder
        }

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

        val ChMainMeal = findViewById<Button>(R.id.btnChMainMeal)
        ChMainMeal.setOnClickListener {
            val intent = Intent(this, MainMealActivity::class.java)
            intent.putExtra("Type","main")
            startForResult.launch(intent)
        }

        val ChSide = findViewById<Button>(R.id.btnChSideDishes)
        ChSide.setOnClickListener {
            val intent = Intent(this, SideDishesActivity::class.java)
            intent.putExtra("Type","side")
            startForResult.launch(intent)
        }

        val ChDrink = findViewById<Button>(R.id.btnChDrink)
        ChDrink.setOnClickListener {
            val intent = Intent(this, DrinkActivity::class.java)
            intent.putExtra("Type","drink")
            startForResult.launch(intent)
        }

        val Order = findViewById<Button>(R.id.btnOrder)
        Order.setOnClickListener {
            val tvMain = findViewById<TextView>(R.id.tvMain)
            val tvSide = findViewById<TextView>(R.id.tvSide)
            val tvDrink = findViewById<TextView>(R.id.tvDrink)

            val intent = Intent(this, ConfirmActivity::class.java)
            val b= Bundle()
            b.putString("Main",tvMain.text.toString())
            b.putString("Side",tvSide.text.toString())
            b.putString("Drink",tvDrink.text.toString())
            intent.putExtras(b)

            startForConfirm.launch(intent)
        }
    }
}