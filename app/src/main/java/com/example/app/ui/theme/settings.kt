package com.example.app.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.SettingsBinding
import com.example.app.edit_profile
import com.example.app.reset

class settings : AppCompatActivity() {
    lateinit var binding: SettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
        binding.okbtn1.setOnClickListener {
            intent = Intent(applicationContext, edit_profile::class.java)
            startActivity(intent)

        }
        binding.okbtn3.setOnClickListener{
            intent = Intent(applicationContext, reset::class.java )
            startActivity(intent)
        }
}
}