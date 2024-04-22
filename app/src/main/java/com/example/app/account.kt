package com.example.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.AccountBinding
class account : AppCompatActivity() {
    private lateinit var binding: AccountBinding // Declare binding variable
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

        // Setting up the spinner with security questions
        val securityQuestions = arrayOf(
            "What was the name of your first pet?",
            "In which city were you born?",
            "What is your favorite movie/book/TV show?"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, securityQuestions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        securityQuestionSpinner.adapter = adapter

        // Setting click listener for the save button
        saveButton.setOnClickListener {
            val nickname = nicknameEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password1 = passwordEditText1.text.toString()
            val password2 = passwordEditText2.text.toString()
            val securityQuestion = securityQuestionSpinner.selectedItem.toString()
            val securityAnswer = securityAnswerEditText.text.toString()

            // Saving data to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("Nickname", nickname)
            editor.putString("Username", username)
            editor.putString("Password1", password1)
            editor.putString("Password2", password2)
            editor.putString("SecurityQuestion", securityQuestion)
            editor.putString("SecurityAnswer", securityAnswer)
            editor.apply()

            // Optionally, you can clear the EditText fields after saving
            nicknameEditText.text.clear()
            usernameEditText.text.clear()
            passwordEditText1.text.clear()
            passwordEditText2.text.clear()
            securityAnswerEditText.text.clear()
        }

        // Setting click listener for the "Do you have an account already" button
        binding.button.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
