package com.example.mealordering

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_confirm)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val main = intent.getStringExtra("Main")
        val side = intent.getStringExtra("Side")
        val drink = intent.getStringExtra("Drink")

        val result = findViewById<TextView>(R.id.tvSummaryResult)
        result.text="${main}\n${side}\n${drink}"

        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        btnConfirm.setOnClickListener {
            if(isComplete()){
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Submit Order")
                alert.setMessage(main+"\n"+side+"\n"+"${drink}\n\nSubmit this order?")
                alert.setPositiveButton("Submit"){dialog, which ->
                    // 按鈕點擊後
                    Toast.makeText(this,"Order submitted!",Toast.LENGTH_SHORT).show()

                    val i = Intent()
                    i.putExtra("Result","已送出訂單\n"+result.text.toString())
                    setResult(RESULT_OK,i)
                    finish()
                }
                alert.setNegativeButton("Cancel",null)
                alert.show()
            }else{
                Toast.makeText(this,"請選擇主餐、附餐、飲料，各一項",Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
    private fun isComplete(): Boolean{
        val main = intent.getStringExtra("Main")
        val side = intent.getStringExtra("Side")
        val drink = intent.getStringExtra("Drink")
        if(main == "Main:一"){
            return false
        }
        if(side == "Sides:一"){
            return false
        }
        if(drink == "Drink:一"){
            return false
        }
        return true
    }
}