package com.example.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ProfilePage2Binding

class profile_page_2 : AppCompatActivity() {
    private lateinit var binding: ProfilePage2Binding // Declare binding variable
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfilePage2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Retrieve the username, bio, and favorite word from SharedPreferences
        val nickname = sharedPreferences.getString("Nickname", null)
        val addbio = sharedPreferences.getString("Addbio", null)
        val addfavword = sharedPreferences.getString("Addfavword", null)

        // Set the retrieved values to the appropriate TextViews
        binding.usernameTextView.text = nickname
        binding.biographyTextView.text = addbio
        binding.usersfavwords.text = addfavword

        binding.usernameTextView.setOnClickListener {
            intent = Intent(applicationContext, edit_profile::class.java)
            startActivity(intent)

        }
        binding.biographyTextView.setOnClickListener {
            intent = Intent(applicationContext, edit_profile::class.java)
            startActivity(intent)

        }
    }
}