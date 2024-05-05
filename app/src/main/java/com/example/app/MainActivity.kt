package com.example.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.MainactivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainactivityBinding // Declare binding variable
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Accessing views from the layout
        val loginButton = binding.loginbtn
        val usernameEditText = binding.editTextText2
        val passwordEditText = binding.editTextTextPassword1

        // Setting click listener for the login button
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Retrieve saved username and password from SharedPreferences (for demo purposes)
            val savedUsername = sharedPreferences.getString("Username", "")
            val savedPassword = sharedPreferences.getString("Password1", "")

            // Check if the entered credentials match the saved credentials
            if (username == savedUsername && password == savedPassword) {
                // Successful login, navigate to the profile page
                Toast.makeText(this@MainActivity, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, main_page::class.java)
                startActivity(intent)
            } else {
                // Invalid credentials, show a toast message
                Toast.makeText(this@MainActivity, "Invalid username or password check again pls", Toast.LENGTH_SHORT).show()
            }
        }

        // Setting click listener for the "Forgot Password" button
        binding.forgetbtn.setOnClickListener {
            val intent = Intent(applicationContext, reset::class.java)
            startActivity(intent)
        }

        // Setting click listener for the "Create Account" button
        binding.cratebtn.setOnClickListener {
            val intent = Intent(applicationContext, account::class.java)
            startActivity(intent)
        }




    }
}
