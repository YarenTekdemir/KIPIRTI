package com.example.app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Oyun : AppCompatActivity() {

    private lateinit var clockTextView: TextView
    private lateinit var hourHand: ImageView
    private lateinit var minuteHand: ImageView
    private lateinit var checkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oyun)

        clockTextView = findViewById(R.id.clockTextView)
        hourHand = findViewById(R.id.hourHand)
        minuteHand = findViewById(R.id.minuteHand)
        checkButton = findViewById(R.id.checkButton)

        // Set initial clock time
        updateClockTime(9)

        // Set onClickListener for check button
        checkButton.setOnClickListener {
            checkTime()
        }
    }

    // Function to update clock time text
    private fun updateClockTime(hour: Int) {
        val timeText = "It's $hour o'clock"
        clockTextView.text = timeText
    }

    // Function to check if hour and minute hands are in correct positions
    private fun checkTime() {
        val hourRotation = hourHand.rotation % 360
        val minuteRotation = minuteHand.rotation % 360

        // Define the correct positions for hour and minute hands
        val correctHourRotation = 30f * 9 // 30 degrees per hour, starting from 12 o'clock
        val correctMinuteRotation = 6f * 0 // 6 degrees per minute, starting from 12 o'clock

        // Check if hour and minute hands are in correct positions
        val isHourCorrect = (hourRotation == correctHourRotation)
        val isMinuteCorrect = (minuteRotation == correctMinuteRotation)

        // Display appropriate message based on correctness
        if (isHourCorrect && isMinuteCorrect) {
            // Both hands are in correct positions
            showToast("Congratulations! The time is correct.")
        } else {
            // Hands are not in correct positions
            showToast("Oops! The time is not correct. Try again.")
        }
    }

    // Function to display toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
