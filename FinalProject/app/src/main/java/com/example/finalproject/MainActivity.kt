package com.example.finalproject

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rg = findViewById<RadioGroup>(R.id.rgAuthSwitch)
        val rbLogin = findViewById<RadioButton>(R.id.rbLogin)

        // 一開始預設選登入
        rbLogin.isChecked = true
        switchFragment(LoginFragment())

        rg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbLogin -> switchFragment(LoginFragment())
                R.id.rbRegister -> switchFragment(RegisterFragment())
            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, fragment)
            .commit()
    }
}