package com.example.finalproject

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rbAddCourse = findViewById<RadioButton>(R.id.rbAddCourse)
        val rbAddTask = findViewById<RadioButton>(R.id.rbAddTask)
        val radioGroup = findViewById<RadioGroup>(R.id.radioAddGroup)

        // Activity 第一次啟動 → 預設載入新增課程畫面
        if (savedInstanceState == null) {
            replaceFragment(AddCourseFragment())
        }

        // 監聽 RadioGroup 切換 Fragment
        radioGroup.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.rbAddCourse -> {
                    replaceFragment(AddCourseFragment())
                }
                R.id.rbAddTask -> {
                    replaceFragment(AddTaskFragment())
                }
            }
        }
    }

    // Fragment 切換方法
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.addFragmentContainer, fragment)
            .commit()
    }
}