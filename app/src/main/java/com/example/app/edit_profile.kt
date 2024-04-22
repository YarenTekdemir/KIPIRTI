package com.example.app

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.EditProfileBinding

class edit_profile : AppCompatActivity() {
        private lateinit var binding: EditProfileBinding // Declare binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Accessing views from the layout
        val editTextNickname = binding.editTextNickname
        val editTextUsername = binding.editTextUsername
        val addbio = binding.addbio
        val addfavword = binding.addfavword
        val buttonUpdate = binding.buttonUpdate
        val cancelbutton =binding.cancelbutton

        // Load user's existing profile information if available
        loadUserProfile()

        // Set click listener for the update button
        buttonUpdate.setOnClickListener {
            updateProfile(
                editTextNickname.text.toString(),
                editTextUsername.text.toString(),
                addbio.text.toString(),
                addfavword.text.toString()
            )
        }
        cancelbutton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Cancel Changes")
                .setMessage("Are you sure you want to cancel your changes?")
                .setPositiveButton("Yes") { _, _ ->
                    // Reset form fields or perform other actions here
                    editTextNickname.text.clear()
                    editTextUsername.text.clear()
                    addbio.text.clear()
                    addfavword.text.clear()
                }
                .setNegativeButton("No", null)
                .show()
        }



    }

    private fun loadUserProfile() {
        // Get reference to SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Retrieve the user's existing profile information from SharedPreferences
        val nickname = sharedPreferences.getString("Nickname", "")
        val username = sharedPreferences.getString("Username", "")
        val bio = sharedPreferences.getString("Addbio", "")
        val favword = sharedPreferences.getString("Addfavword", "")

        // Set the retrieved values to the EditText fields
        binding.editTextNickname.setText(nickname)
        binding.editTextUsername.setText(username)
        binding.addbio.setText(bio)
        binding.addfavword.setText(favword)
    }

    private fun updateProfile(nickname: String, username: String, bio: String, favword: String) {
        // Get reference to SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Update the nickname and username in SharedPreferences
        editor.putString("Nickname", nickname)
        editor.putString("Username", username)
        editor.putString("Addbio", bio)
        editor.putString("Addfavword", favword)

        // Apply changes
        editor.apply()

        // Show a toast message indicating successful update
        Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
    }




}
