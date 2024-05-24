package com.example.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ResetpswdBinding

class reset : AppCompatActivity() {
    private lateinit var binding: ResetpswdBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var securityQuestionSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResetpswdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.okbtn1.setOnClickListener {
            intent = Intent(applicationContext, profile_page::class.java)
            startActivity(intent)
        }
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Accessing views from the layout
        val newPasswordEditText = binding.editTextTextPassword1
        val confirmPasswordEditText = binding.editTextTextPassword2
        val securityAnswerEditText = binding.editTextTextPassword3
        val resetButton = binding.buttonResetPassword
        securityQuestionSpinner = binding.securityQuestionSpinner

        // Set up the security questions spinner
        val securityQuestions = arrayOf(
            "What was the name of your first pet?",
            "In which city were you born?",
            "What is your favorite movie/book/TV show?"
        )
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            securityQuestions
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        securityQuestionSpinner.adapter = adapter

        // Set click listener for the reset button
        resetButton.setOnClickListener {
            val newPassword = newPasswordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            val securityQuestion = securityQuestionSpinner.selectedItem.toString()
            val securityAnswer = securityAnswerEditText.text.toString()

            // Perform validation
            if (newPassword.isEmpty() || confirmPassword.isEmpty() || securityAnswer.isEmpty()) {
                showToast("Please fill in all fields.")
            } else if (newPassword != confirmPassword) {
                showToast("Passwords do not match.")
            } else if (!isSecurityAnswerCorrect(securityAnswer)) {
                showToast("Incorrect security answer.")
            } else {
                // Save new password and security answer to SharedPreferences
                savePasswordAndAnswer(newPassword, securityAnswer)
                // Provide appropriate feedback to the user
                showToast("Password Reset Successfully.")
                // Optionally, you can clear the EditText fields after resetting
                newPasswordEditText.text.clear()
                confirmPasswordEditText.text.clear()
                securityAnswerEditText.text.clear()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun savePasswordAndAnswer(password: String, answer: String) {
        val editor = sharedPreferences.edit()
        editor.putString("Password", password)
        editor.putString("SecurityAnswer", answer)
        editor.apply()
    }

    private fun isSecurityAnswerCorrect(answer: String): Boolean {
        val savedAnswer = sharedPreferences.getString("SecurityAnswer", "")
        return answer == savedAnswer
    }
}
