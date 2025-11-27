package com.example.myandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 暫時使用一個簡單的佈局來顯示資料
        setContentView(R.layout.activity_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "個人資訊"

        loadAndDisplayProfile()
        setupListeners()
    }

    // 讓左上角的返回箭頭能夠返回到前一個 Activity
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadAndDisplayProfile() {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val container = findViewById<LinearLayout>(R.id.profile_container)

        // 定義要顯示的欄位
        val fields = mapOf(
            "姓名" to "user_name",
            "學校" to "user_school",
            "性別" to "user_gender",
            "電子郵件" to "user_email",
            "好友ID" to "user_friend_id"
        )

        container.removeAllViews() // 清空舊的 View

        fields.forEach { (label, key) ->
            val value = sharedPref.getString(key, "未設定") // 讀取資料

            // 創建一個顯示單一資訊列的 View (TextView + Button)
            val row = layoutInflater.inflate(R.layout.profile_info_row, container, false)
            val labelView: TextView = row.findViewById(R.id.info_label)
            val valueView: TextView = row.findViewById(R.id.info_value)
            val updateButton: Button = row.findViewById(R.id.btn_update)

            labelView.text = "$label："
            valueView.text = value

            // 設置更新按鈕點擊事件：跳轉回 SettingsActivity
            updateButton.setOnClickListener {
                // 將要更新的欄位名稱傳遞給設定頁面 (可選，用於聚焦)
                val intent = Intent(this, SettingsActivity::class.java).apply {
                    putExtra("UPDATE_FIELD", key)
                }
                startActivity(intent)
                // 為了簡單，這裡直接跳轉。在實際應用中，通常使用 startActivityForResult
            }

            container.addView(row)
        }
    }

    private fun setupListeners() {
        // 處理登出按鈕
        val logoutButton: Button = findViewById(R.id.btn_logout)
        logoutButton.setOnClickListener {
            // 清除設定狀態，並清除所有儲存的個人資訊
            getSharedPreferences("user_prefs", Context.MODE_PRIVATE).edit().apply {
                putBoolean("is_profile_set", false)
                // 這裡可以加入 clear() 清除所有資料，視需求而定
                apply()
            }
            Toast.makeText(this, "已登出，下次需重新設定", Toast.LENGTH_LONG).show()
            // 導航回 MainActivity
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }
    }

    // 從 SettingsActivity 返回時，需要重新載入資料
    override fun onResume() {
        super.onResume()
        loadAndDisplayProfile()
    }
}