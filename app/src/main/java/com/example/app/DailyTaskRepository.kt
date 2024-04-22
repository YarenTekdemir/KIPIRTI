package com.example.app

import java.util.Calendar


object DailyTaskRepository {
    private val dailyTasks = listOf(
        DailyTask("Defeat the dragon", "Defeat the dragon in the Forbidden Forest!", R.drawable.oyun),
        DailyTask("Find the hidden treasure", "Search for the hidden treasure in the Pirate's Cove!", R.drawable.game1),
        DailyTask("Find the hidden ", " for the hidden treasure in the anan's Cove!", R.drawable.game2),
        DailyTask("Complete the magic spell", "Learn and complete the magic spell in the Wizard's Tower!", R.drawable.trophy_image1)
        // Add more daily tasks as needed
    )

    fun getDailyTask(): DailyTask {
        // Get the current day of the week (0 = Sunday, 1 = Monday, ..., 6 = Saturday)
        val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        // Use the day of the week to select a daily task (for demonstration purposes, just modulo the dayOfWeek)
        val index = dayOfWeek % dailyTasks.size

        // Return the daily task corresponding to the selected index
        return dailyTasks[index]
    }
}
