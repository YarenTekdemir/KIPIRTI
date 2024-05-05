package com.example.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ProfilePageBinding
import com.example.app.ui.theme.hallof

class profile_page : AppCompatActivity() {
    lateinit var binding: ProfilePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.okbtn2.setOnClickListener {
            intent = Intent(applicationContext, Chat::class.java)
            startActivity(intent)

        }

        binding.okbtn4.setOnClickListener{
            intent = Intent(applicationContext, game::class.java )
            startActivity(intent)
        }
      binding.okbtn5.setOnClickListener{
            intent = Intent(applicationContext, achivements::class.java )
            startActivity(intent)
        }


        binding.mypagebtn.setOnClickListener{
           intent = Intent(applicationContext, profile_page_2::class.java )
            startActivity(intent)
      }
        binding.hallof1.setOnClickListener{
           intent = Intent(applicationContext, hallof::class.java )
            startActivity(intent)
      }




    }

}
