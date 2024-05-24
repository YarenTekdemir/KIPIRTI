package com.example.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.NavigationBarBinding

class navbar : AppCompatActivity() {

    lateinit var binding: NavigationBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NavigationBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeButton.setOnClickListener {
            intent = Intent(applicationContext, main_page::class.java)
            startActivity(intent)
        }

        binding.profileButton.setOnClickListener {
            intent = Intent(applicationContext, profile_page::class.java)
            startActivity(intent)        }

        binding.gameButton.setOnClickListener {
            intent = Intent(applicationContext, game::class.java)
            startActivity(intent)        }

        binding.addIcon.setOnClickListener {
            intent = Intent(applicationContext, voice::class.java)
            startActivity(intent)         }

    }
}



