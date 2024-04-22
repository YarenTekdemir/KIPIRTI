package com.example.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.DailyTaskBinding

class daily_task : AppCompatActivity() {
    private lateinit var binding: DailyTaskBinding // Declare binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DailyTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Assuming you have daily task data
        val dailyTask = DailyTask("Defeat the dragon", "Defeat the dragon in the Forbidden Forest!", R.drawable.oyun)

        // Set the daily task title and description
        binding.dailyTaskTitle.text = dailyTask.title
        binding.dailyTaskDescription.text = dailyTask.description

        // Set the daily task image
        binding.taskImageView.setImageResource(dailyTask.imageRes)

        // Button click listener to navigate to the game page
        binding.startTaskButton.setOnClickListener {
            // Navigate to the game page DİREKT OYUNA BAĞLA APİYLE
            intent = Intent(applicationContext, game::class.java )
            startActivity(intent)
        }
    }
}

data class DailyTask(val title: String, val description: String, val imageRes: Int)
