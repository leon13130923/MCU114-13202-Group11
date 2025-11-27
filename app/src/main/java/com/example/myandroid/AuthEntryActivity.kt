// AuthEntryActivity.kt

package com.example.myandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AuthEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_entry)

        // 隱藏標題欄，因為這是全螢幕/獨立的登入註冊入口
        supportActionBar?.hide()

        val btnRegister: Button = findViewById(R.id.btn_register)
        val btnLogin: Button = findViewById(R.id.btn_login)
        val btnRecover: Button = findViewById(R.id.btn_recover)

        // 點擊「註冊帳號」 -> 跳轉到 SettingsActivity (原來的設定頁，現在作為註冊)
        btnRegister.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish() // 註冊/設定是單向流程，完成後應該回到主頁
        }

        // 點擊「登入帳號」 -> 跳轉到 LoginActivity
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // 點擊「找回帳號」 -> 跳轉到 RecoverActivity
        btnRecover.setOnClickListener {
            val intent = Intent(this, RecoverActivity::class.java)
            startActivity(intent)
        }
    }
}