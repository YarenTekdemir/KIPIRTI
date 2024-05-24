package com.example.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ProfilePageBinding

class profile_page : AppCompatActivity() {
    lateinit var binding: ProfilePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.okbtn1.setOnClickListener {
            intent = Intent(applicationContext, edit_profile::class.java)
            startActivity(intent)

        }

        binding.okbtn2.setOnClickListener{
            intent = Intent(applicationContext, reset::class.java )
            startActivity(intent)
        }
      binding.dailytaskbtn.setOnClickListener{
            intent = Intent(applicationContext, daily_task::class.java )
            startActivity(intent)
        }


        binding.achivementsbtn.setOnClickListener{
           intent = Intent(applicationContext, achivements::class.java )
            startActivity(intent)
      }
        binding.settingsbutton.setOnClickListener{
           intent = Intent(applicationContext, profile_page_2::class.java )
            startActivity(intent)
      }





    }

}
