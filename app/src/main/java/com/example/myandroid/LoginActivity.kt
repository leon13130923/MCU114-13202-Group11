
package com.example.myandroid
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "好友 ID 登入"

        val editFriendId = findViewById<TextInputEditText>(R.id.edit_login_id)
        val btnLogin = findViewById<Button>(R.id.btn_perform_login)

        // 登入邏輯
        btnLogin.setOnClickListener {
            val inputId = editFriendId.text.toString()
            val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val storedId = sharedPref.getString("user_friend_id", "")

            if (inputId == storedId && !storedId.isNullOrEmpty()) {
                // 登入成功
                sharedPref.edit().putBoolean("is_profile_set", true).apply()
                Toast.makeText(this, "登入成功！", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "好友 ID 錯誤或未註冊", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        // 結束當前 Activity，返回到呼叫它的前一個 Activity (即 AuthEntryActivity)
        finish()
        return true // 表示已經處理了這個導航事件
    }
}