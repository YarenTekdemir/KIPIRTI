package com.example.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class navigation_bar : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        val homeButton: Button = findViewById(R.id.homeButton)
        val profileButton: Button = findViewById(R.id.profileButton)
        val gameButton: Button = findViewById(R.id.gameButton)

        homeButton.setOnClickListener {
            // Navigate to HomeActivity
            val intent = Intent(this, main_page::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            // Navigate to ProfileActivity
            val intent = Intent(this, profile_page::class.java)
            startActivity(intent)
        }

        gameButton.setOnClickListener {
            // For example, navigate to the login screen
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optionally, finish the current activity
        }
    }
}
