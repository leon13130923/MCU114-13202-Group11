package com.example.myandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RecoverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)
        supportActionBar?.title = "找回帳號 (Email 驗證)"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val editEmail = findViewById<TextInputEditText>(R.id.edit_recover_email)
        val btnRecover = findViewById<Button>(R.id.btn_perform_recover)

        // 找回帳號邏輯
        btnRecover.setOnClickListener {
            val inputEmail = editEmail.text.toString().trim() // 移除前後空格

            if (inputEmail.isBlank()) {
                Toast.makeText(this, "請輸入電子郵件", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 1. 讀取儲存的資料
            val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val storedEmail = sharedPref.getString("user_email", "")

            // 2. 進行 Email 比對
            if (inputEmail.equals(storedEmail, ignoreCase = true) && !storedEmail.isNullOrEmpty()) {
                // Email 相符，視為找回/登入成功
                sharedPref.edit().putBoolean("is_profile_set", true).apply()
                Toast.makeText(this, "Email 驗證成功，已登入！", Toast.LENGTH_LONG).show()

                // 跳轉回 MainActivity
                val intent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "此 Email 找不到註冊記錄", Toast.LENGTH_LONG).show()
            }
        }
    }

    // 讓左上角的返回箭頭能夠返回到前一個 Activity
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}