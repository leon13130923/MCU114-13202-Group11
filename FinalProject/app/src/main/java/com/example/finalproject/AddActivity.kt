package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.finalproject.databinding.ActivityAddBinding
import com.example.finalproject.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    private lateinit var imgButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageAddButton.setOnClickListener {
//            startActivity(Intent(this, Class::class.java))
            finish()  // 結束當前 Activity，自動回到上一頁
        }

//        imgButton = findViewById<ImageButton>(R.id.imageAddButton);
//        imgButton.setOnClickListener {
//            startActivity(Intent(this, Class::class.java))
//        }




        // 預設顯示登入頁面
        if (savedInstanceState == null) {
            loadFragment(AddCourseFragment())  // ← 初始載入登入頁面
        }

        // 👇 這裡就是 TabLayout 的點擊事件！
        binding.tabAddLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> loadFragment(AddCourseFragment())      // ← 點擊「登入」Tab
                    1 -> loadFragment(AddTaskFragment())   // ← 點擊「註冊」Tab
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


    }

    // 👇 切換 Fragment 的方法
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.addFragmentContainer.id, fragment)
            .commit()
    }

    // 提供給 Fragment 呼叫，用於切換 Tab
    fun switchToTab(position: Int) {
        binding.tabAddLayout.getTabAt(position)?.select()
    }


}