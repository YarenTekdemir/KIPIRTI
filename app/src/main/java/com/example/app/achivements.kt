package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.AchivementsBinding

class achivements : AppCompatActivity() {
    private lateinit var binding: AchivementsBinding // Declare binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AchivementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}