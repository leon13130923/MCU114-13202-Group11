package com.example.finalproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.finalproject.databinding.ActivityTestBinding
import com.google.android.material.tabs.TabLayout

class TestActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityTestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        binding = ActivityTestBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // 預設顯示登入頁面
//        if (savedInstanceState == null) {
//            loadFragment(LoginFragment())  // ← 初始載入登入頁面
//        }
//
//        // 👇 這裡就是 TabLayout 的點擊事件！
//        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                when (tab?.position) {
//                    0 -> loadFragment(LoginFragment())      // ← 點擊「登入」Tab
//                    1 -> loadFragment(RegisterFragment())   // ← 點擊「註冊」Tab
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        })
    }
//    // 👇 切換 Fragment 的方法
//    private fun loadFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(binding.fragmentContainer.id, fragment)
//            .commit()
//    }
//
//    // 提供給 Fragment 呼叫，用於切換 Tab
//    fun switchToTab(position: Int) {
//        binding.tabLayout.getTabAt(position)?.select()
//    }
}
