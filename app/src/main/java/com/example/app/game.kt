package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.GamepageBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class game  : AppCompatActivity() {
     lateinit var binding: GamepageBinding // Declare binding variable
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GamepageBinding.inflate(layoutInflater)
        setContentView(binding.root);
        initUI()

    }
     fun initUI() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            position = Position.Relative(0.5, 0.3),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
        )


        // Set up OnRatingBarChangeListener for each RatingBar
        binding.ratingBar1.setOnRatingBarChangeListener { _, rating, _ ->
            showToast("Rating for game 1: $rating")
        }

        binding.ratingBar2.setOnRatingBarChangeListener { _, rating, _ ->
            showToast("Rating for game 2: $rating")
        }

        binding.ratingBar3.setOnRatingBarChangeListener { _, rating, _ ->
            showToast("Rating for game 3: $rating")
        }

        binding.ratingBar4.setOnRatingBarChangeListener { _, rating, _ ->
            showToast("Rating for game 4: $rating")
        }

        binding.daily.setOnClickListener {
            intent = Intent(applicationContext, daily_task::class.java)
            startActivity(intent)
        }
        binding.imv1.setOnClickListener {
            binding.konfettiView.start(party)
        }
         binding.imv2.setOnClickListener {
            binding.konfettiView.start(party)
        }
         binding.imv3.setOnClickListener {
            binding.konfettiView.start(party)
        }

         binding.imv4.setOnClickListener {
            binding.konfettiView.start(party)
        }

         binding.okbtn1.setOnClickListener{
             intent = Intent(applicationContext, profile_page::class.java )
             startActivity(intent)
         }
     }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}