package com.example.ordermeal

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
        val main=intent.getStringExtra("Main")
        val side=intent.getStringExtra("Side")
        val drink=intent.getStringExtra("Drink")
        val order = findViewById<TextView>(R.id.tvResult)
        order.text=main+"\n"+side+"\n"+drink

        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        btnConfirm.setOnClickListener {
            if (isComplete()){



                val alert = AlertDialog.Builder(this)
                alert.setTitle("提交訂單")
                alert.setMessage(main+"\n"+side+"\n"+drink+"\n\n"+"確認提交訂單?")


                alert.setPositiveButton("Submit"){dialog, which ->
                    Toast.makeText(this,"Order submitted", Toast.LENGTH_SHORT).show()

                    val i = Intent()
                    i.putExtra("Key","您的訂單已送出\n"+main+"\n"+side+"\n"+drink)
                    setResult(RESULT_OK,i)
                    finish()
                }
                alert.show()
                // 都有選到 => 跳出 AlertDialog
            }else{
                // 任何一項沒選到 => 跳出 Toast
                Toast.makeText(this,"請選擇至少一份主餐、附餐、飲料",Toast.LENGTH_LONG).show()

                val i = Intent()
                i.putExtra("Key","")
                setResult(RESULT_OK,i)
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