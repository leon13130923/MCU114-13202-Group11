package com.example.myandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class SettingsActivity : AppCompatActivity() {

    // 儲存所有輸入框的引用
    private lateinit var editName: TextInputEditText
    private lateinit var editSchool: TextInputEditText
    private lateinit var editGender: TextInputEditText
    private lateinit var editEmail: TextInputEditText
    private lateinit var editFriendId: TextInputEditText
    private lateinit var btnSave: Button

    private fun generateFriendId(): String {
        val min = 1_000_000_000L
        val max = 9_999_999_999L
        return Random.nextLong(min, max + 1).toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "個人資訊設定"

        // 1. 找到所有元件
        editName = findViewById(R.id.edit_name)
        editSchool = findViewById(R.id.edit_school)
        editGender = findViewById(R.id.edit_gender)
        editEmail = findViewById(R.id.edit_email)
        editFriendId = findViewById(R.id.edit_friend_id)
        btnSave = findViewById(R.id.btn_save)

        // 2. 載入上次儲存的資料
        loadUserData()

        // 3. 設定儲存按鈕的點擊事件
        btnSave.setOnClickListener {
            saveUserData()
        }
    }

    // 讀取 SharedPreferences 裡的資料，並填入輸入框
    private fun loadUserData() {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // 只有第一次進入時才生成 Friend ID
        var friendId = sharedPref.getString("user_friend_id", "")
        if (friendId.isNullOrEmpty()) {
            friendId = generateFriendId()
        }

        editName.setText(sharedPref.getString("user_name", ""))
        editSchool.setText(sharedPref.getString("user_school", ""))
        editGender.setText(sharedPref.getString("user_gender", ""))
        editEmail.setText(sharedPref.getString("user_email", ""))
        editFriendId.setText(friendId) // 顯示 ID
    }

    // 儲存所有輸入框的資料
    private fun saveUserData() {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // 檢查電子郵件等必填欄位
        if (editName.text.isNullOrBlank() || editEmail.text.isNullOrBlank()) {
            Toast.makeText(this, "請填寫姓名和電子郵件", Toast.LENGTH_SHORT).show()
            return
        }

        sharedPref.edit().apply {
            putString("user_name", editName.text.toString())
            putString("user_school", editSchool.text.toString())
            putString("user_gender", editGender.text.toString())
            putString("user_email", editEmail.text.toString())
            // 儲存 Friend ID
            putString("user_friend_id", editFriendId.text.toString())

            // 設定已設定標記
            putBoolean("is_profile_set", true)
            apply() // 立即寫入
        }

        Toast.makeText(this, "個人資訊儲存成功！", Toast.LENGTH_SHORT).show()

        // ⭐ 關鍵修改：註冊成功後，導航回 MainActivity (並清空 Activity 堆棧)
        val intent = Intent(this, MainActivity::class.java).apply {
            // 這是為了確保回到主頁後，如果按返回鍵不會回到註冊頁
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        // 儲存後返回課程分享頁面 (MainActivity)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}