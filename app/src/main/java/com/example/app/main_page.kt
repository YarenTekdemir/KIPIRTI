package com.example.app

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.MainPageBinding

class main_page : AppCompatActivity() {
    private lateinit var binding: MainPageBinding
    private val posts = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thoughtInput = binding.thoughtInput
        val postButton = binding.postButton

        // Get user's name from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("Nickname", "User Name") ?: "User Name"

        // Set the retrieved user's name to the TextView using ViewBinding

        // Inside postButton.setOnClickListener
        postButton.setOnClickListener {
            val thought = thoughtInput.text.toString().trim()
            if (thought.isNotEmpty()) {
                // Call a function to display the thought
                displayPost(userName, thought)
                // Clear the input field
                thoughtInput.text.clear()
            } else {
                Toast.makeText(this, "Please enter your thought", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to display the post dynamically
    private fun displayPost(userName: String, thought: String) {
        // Inflate the layout for the post
        val postLayout = layoutInflater.inflate(R.layout.post_layout, null)

        // Get references to the views in the post layout
        val userImageView = postLayout.findViewById<ImageView>(R.id.userImageView)
        val userNameTextView = postLayout.findViewById<TextView>(R.id.userNameTextView)
        val postContentTextView = postLayout.findViewById<TextView>(R.id.postContentTextView)
        val likeButton1 = postLayout.findViewById<ImageView>(R.id.likeButton1)
        val likeButton2 = postLayout.findViewById<ImageView>(R.id.likeButton2)
        val postsContainer = findViewById<LinearLayout>(R.id.postsContainer)

        // Set user name and post content
        userNameTextView.text = userName
        postContentTextView.text = thought

        // Add the post layout to the postsContainer
        postsContainer.addView(postLayout)
    }

}
