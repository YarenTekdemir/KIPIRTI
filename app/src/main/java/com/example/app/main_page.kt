package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import com.example.app.R
import com.example.app.databinding.MainPageBinding

class main_page : AppCompatActivity() {
    private lateinit var binding: MainPageBinding
    private val posts = mutableListOf<String>()

    // Track the state of the like and dislike buttons
    private var likeButtonActive = false
    private var dislikeButtonActive = false
    private var likeCount = 0
    private var dislikeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeButton.setOnClickListener {
            intent = Intent(applicationContext, main_page::class.java)
            startActivity(intent)
        }

        binding.profileButton.setOnClickListener {
            intent = Intent(applicationContext, profile_page_2::class.java)
            startActivity(intent)        }

        binding.gameButton.setOnClickListener {
            intent = Intent(applicationContext, game::class.java)
            startActivity(intent)        }

        binding.hearButton.setOnClickListener {
            intent = Intent(applicationContext, voice::class.java)
            startActivity(intent)        }

        val thoughtInput = binding.thoughtInput
        val postButton = binding.postButton

        // Get user's name from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("Nickname", "User Name") ?: "User Name"

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
        val likeCountTextView = postLayout.findViewById<TextView>(R.id.likeCountTextView)
        val dislikeCountTextView = postLayout.findViewById<TextView>(R.id.dislikeCountTextView)
        val postsContainer = findViewById<LinearLayout>(R.id.postsContainer)

        // Set user name and post content
        userNameTextView.text = userName
        postContentTextView.text = thought

        // Set onClickListeners for like and dislike buttons
        likeButton1.setOnClickListener {
            toggleLikeButtonState(likeButton1, likeCountTextView)
        }

        likeButton2.setOnClickListener {
            toggleDislikeButtonState(likeButton2, dislikeCountTextView)
        }

        // Update the displayed like and dislike counts
        updateLikeCount(likeCountTextView)
        updateDislikeCount(dislikeCountTextView)

        // Add the post layout to the postsContainer at the beginning
        postsContainer.addView(postLayout, 0)
    }


    private fun toggleLikeButtonState(likeButton: ImageView, likeCountTextView: TextView) {
        likeButtonActive = !likeButtonActive
        updateButtonAppearance(likeButton, likeButtonActive)
        if (likeButtonActive) {
            likeCount++
        } else {
            likeCount--
        }
        updateLikeCount(likeCountTextView)
    }

    // Function to toggle the state of the dislike button
    private fun toggleDislikeButtonState(dislikeButton: ImageView, dislikeCountTextView: TextView) {
        dislikeButtonActive = !dislikeButtonActive
        updateButtonAppearance(dislikeButton, dislikeButtonActive)
        if (dislikeButtonActive) {
            dislikeCount++
        } else {
            dislikeCount--
        }
        updateDislikeCount(dislikeCountTextView)
    }

    // Function to update the appearance of the button based on its state
    private fun updateButtonAppearance(button: ImageView, isActive: Boolean) {
        if (isActive) {
            // Change the color to red
            button.setColorFilter(Color.RED)
        } else {
            // Change the color to gray
            button.setColorFilter(Color.BLACK)
        }
    }

    // Function to update the displayed like count
    private fun updateLikeCount(likeCountTextView: TextView) {
        likeCountTextView.text = likeCount.toString()
    }

    // Function to update the displayed dislike count
    private fun updateDislikeCount(dislikeCountTextView: TextView) {
        dislikeCountTextView.text = dislikeCount.toString()
    }
}

