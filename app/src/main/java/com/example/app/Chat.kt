package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ChatBinding

class Chat : AppCompatActivity() {
    lateinit var binding: ChatBinding // Declare binding variable
    lateinit var sendButton: Button
    lateinit var messageInput: EditText
    lateinit var messageContainer: LinearLayout
    lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.chatsbutton.setOnClickListener {
            intent = Intent(applicationContext, game::class.java)
            startActivity(intent)
        }
        // Initialize views
        sendButton = findViewById(R.id.sendButton)
        messageInput = findViewById(R.id.messageInput)
        messageContainer = findViewById(R.id.messageContainer)
        deleteButton = findViewById(R.id.deleteButton)

        // Set click listener for the send button
        sendButton.setOnClickListener {
            // Retrieve the text from the message input field
            val messageText = messageInput.text.toString().trim()

            // Check if the message is not empty
            if (messageText.isNotEmpty()) {
                // Display the sent message in the chat interface
                displayMessage(messageText)

                // After sending the message, clear the input field
                messageInput.text.clear()
            }
        }

        // Set click listener for the delete button
        deleteButton.setOnClickListener {
            // Clear all messages from the chat interface
            messageContainer.removeAllViews()
        }
    }

    private fun displayMessage(message: String) {
        // Create a TextView to display the message
        val messageTextView = TextView(this)
        messageTextView.text = message
        messageTextView.setBackgroundResource(R.drawable.bubble_background) // Set bubble background

        // Add the message TextView to the message container
        messageContainer.addView(messageTextView)
    }
}
