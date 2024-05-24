package com.example.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.AccountBinding

class account : AppCompatActivity() {
    private lateinit var binding: AccountBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Accessing views from the layout
        val saveButton = binding.saveButton
        val nicknameEditText = binding.editText4
        val usernameEditText = binding.editText3
        val passwordEditText1 = binding.editTextTextPassword1
        val passwordEditText2 = binding.editTextTextPassword2
        val securityQuestionSpinner = binding.securityQuestionSpinner
        val securityAnswerEditText = binding.securityanswer

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Initialize the security question spinner
        val securityQuestions = arrayOf(
            "What was the name of your first pet?",
            "In which city were you born?",
            "What is your favorite movie/book/TV show?"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, securityQuestions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        securityQuestionSpinner.adapter = adapter

        // Check if the user has already registered
        val isRegistered = sharedPreferences.getBoolean("isRegistered", false)
        if (isRegistered) {
            // If user is already registered, retrieve and display their profile information
            val nickname = sharedPreferences.getString("Nickname", "")
            val username = sharedPreferences.getString("Username", "")
            val password1 = sharedPreferences.getString("Password1", "")
            val password2 = sharedPreferences.getString("Password2", "")
            val securityQuestion = sharedPreferences.getString("SecurityQuestion", "")
            val securityAnswer = sharedPreferences.getString("SecurityAnswer", "")

            // Set retrieved profile information to the respective views
            nicknameEditText.setText(nickname ?: "")
            usernameEditText.setText(username ?: "")
            passwordEditText1.setText(password1 ?: "")
            passwordEditText2.setText(password2 ?: "")

            // Set the selected security question in the spinner
            val selectedSecurityQuestionPosition = securityQuestions.indexOf(securityQuestion)
            if (selectedSecurityQuestionPosition >= 0) {
                securityQuestionSpinner.setSelection(selectedSecurityQuestionPosition)
            }

            // Set security answer
            securityAnswerEditText.setText(securityAnswer ?: "")
        }

        // Setting click listener for the save button
        saveButton.setOnClickListener {
            val nickname = nicknameEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password1 = passwordEditText1.text.toString()
            val password2 = passwordEditText2.text.toString()
            val securityQuestion = securityQuestionSpinner.selectedItem.toString()
            val securityAnswer = securityAnswerEditText.text.toString()

            // Validate inputs
            if (nickname.isEmpty() || username.isEmpty() || password1.isEmpty() || password2.isEmpty() || securityAnswer.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Saving data to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("Nickname", nickname)
            editor.putString("Username", username)
            editor.putString("Password1", password1)
            editor.putString("Password2", password2)
            editor.putString("SecurityQuestion", securityQuestion)
            editor.putString("SecurityAnswer", securityAnswer)
            editor.putBoolean("isRegistered", true) // Flag indicating user is registered
            editor.apply()

            // Optionally, you can clear the EditText fields after saving
            nicknameEditText.text.clear()
            usernameEditText.text.clear()
            passwordEditText1.text.clear()
            passwordEditText2.text.clear()
            securityAnswerEditText.text.clear()

            // Display a toast to indicate successful registration
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
        }

        // Setting click listener for the "Do you have an account already" button
        binding.button.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
