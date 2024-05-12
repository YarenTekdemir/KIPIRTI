package com.example.app

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
            Log.d("Navbar", "Home button clicked") // Add this line for debugging
            showToast("Home Button Clicked")
        }

        binding.profileButton.setOnClickListener {
            showToast("Profile Button Clicked")
        }

        binding.gameButton.setOnClickListener {
            showToast("Game Button Clicked")
        }

        binding.addIcon.setOnClickListener {
            showToast("Add Button Clicked")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
