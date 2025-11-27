package com.example.myandroid

import android.content.Intent // 1. 新增：引入 Intent
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 設定 Toolbar 為 Activity 的 ActionBar
        // val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        // setSupportActionBar(toolbar) 刪除自訂,恢復使用預設 ActionBar
        supportActionBar?.title = "課表分享"

        // 處理系統欄（Status Bar 和 Navigation Bar）的 Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // 載入選單佈局到 Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // 處理選單按鈕的點擊事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_friends -> {
                Toast.makeText(this, "點擊了好友/人員按鈕", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_add -> {
                Toast.makeText(this, "點擊了新增課堂按鈕", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                // 1. 取得 SharedPreferences
                val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                // 檢查是否設定過 "is_profile_set" 旗標
                val isProfileSet = sharedPref.getBoolean("is_profile_set", false)

                val intent: Intent
                if (isProfileSet) {
                    // 2. 如果已設定，跳轉到 ProfileActivity (顯示資訊的頁面)
                    intent = Intent(this, ProfileActivity::class.java)
                } else {
                    // 3. 如果未設定，跳轉到 AuthEntryActivity (三選一入口)
                    intent = Intent(this, AuthEntryActivity::class.java)
                }
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}