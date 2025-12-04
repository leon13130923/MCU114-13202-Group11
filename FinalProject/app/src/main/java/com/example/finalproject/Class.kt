package com.example.finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
//import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar

import com.google.android.material.appbar.MaterialToolbar

class Class : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_class)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. 找到 Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // 2. 設定為 ActionBar
        setSupportActionBar(toolbar)

        // 3. 啟動 Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, TimetableFragment())
            .commit()

//        // 4. 設定 Toolbar menu 事件
//        toolbar.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//
//                // ➤ 新增課程
//                R.id.action_add_course -> {
//                    startActivity(Intent(this, AddActivity::class.java))
//                    true
//                }
//
//                // ➤ 好友
//                R.id.action_friends -> {
//                    startActivity(Intent(this, FriendsActivity::class.java))
//                    true
//                }
//
//                // ➤ 設定
//                R.id.action_setting -> {
//                    startActivity(Intent(this, SettingActivity::class.java))
//                    true
//                }
//
//                else -> false
//            }
//        }
//    }


//    private fun setSupportActionBar(toolbar: Toolbar) {}
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.timetable_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_course -> startActivity(Intent(this, AddActivity::class.java))
            R.id.action_friends -> startActivity(Intent(this, FriendsActivity::class.java))
            R.id.action_setting -> startActivity(Intent(this, SettingActivity::class.java))
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}