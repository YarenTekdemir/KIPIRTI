package com.example.app

import android.content.Intent
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.HallofBinding
import com.example.app.databinding.ProfilePageBinding
import kotlin.collections.ArrayList

class hallof : AppCompatActivity() {
    lateinit var binding: HallofBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HallofBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.halloffamebutton.setOnClickListener{
            intent = Intent(applicationContext, daily_task::class.java )
            startActivity(intent)
        }

        // Simulate player data
        val playerData = ArrayList<Player>()
        playerData.add(Player("Player 1", 100))
        playerData.add(Player("Player 2", 80))
        playerData.add(Player("Player 3", 120))
        playerData.add(Player("Player 4", 90))

        // Sort player data based on scores
        playerData.sortByDescending { it.score }

        // Update UI with sorted player data
        updateUI(playerData)
    }

    private fun updateUI(playerData: List<Player>) {
        // Iterate through player data and update UI
        for (i in 0 until playerData.size) {
            val player = playerData[i]
            val playerNameTextView = findViewById<TextView>(resources.getIdentifier("player_name${i + 1}", "id", packageName))
            val scoreTextView = findViewById<TextView>(resources.getIdentifier("score${i + 1}", "id", packageName))
            val playerImageView = findViewById<ImageView>(resources.getIdentifier("user1ImageView${i + 1}", "id", packageName))

            playerNameTextView.text = player.name
            scoreTextView.text = "Score: ${player.score}"
        }
    }
}

data class Player(val name: String, val score: Int)
