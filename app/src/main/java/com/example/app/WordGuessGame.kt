package com.example.app

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.WordGuessGameBinding
import java.util.Arrays
import java.util.Collections
import java.util.Random

class WordGuessGame : AppCompatActivity() {


    internal var Days = arrayOf( "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    lateinit var day: String
    lateinit var random: Random




    lateinit var txtCorrectAnswer: TextView
    lateinit var txtRightAnswer: TextView
    lateinit var txtQuestionContainer:TextView
    lateinit var etUserInput: EditText
    lateinit var btnShow: Button
    lateinit var btnCheck: Button
    lateinit var btnNext: Button

    lateinit var binding: WordGuessGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WordGuessGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtRightAnswer = findViewById(R.id.txtRightAnswer)
        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer)
        txtQuestionContainer = findViewById(R.id.txtQuestionContainer)

        etUserInput = findViewById(R.id.editTextUserInput)

        btnShow = findViewById(R.id.btnShow)
        btnCheck = findViewById(R.id.btnCheck)
        btnNext = findViewById(R.id.btnNext)

        random = Random()

        //logic
        day = Days[random.nextInt(Days.size)]
        txtQuestionContainer.text = mixWords(day)


        btnCheck.setOnClickListener{

            if(etUserInput.text.toString().equals(day,ignoreCase = true)){

                val dialog = Dialog(this@WordGuessGame)
                dialog.setContentView(R.layout.correct_dialog)
                val bthide = dialog.findViewById<Button>(R.id.btnConfirmDialogue)
                dialog.show()

                bthide.setOnClickListener{
                    dialog.dismiss()

                    day = Days[random.nextInt(Days.size)]
                    txtQuestionContainer.text = mixWords(day)

                    etUserInput.setText("")
                    txtRightAnswer.visibility = View.INVISIBLE
                    txtCorrectAnswer.visibility = View.INVISIBLE
                }
            }
            else{
                Toast.makeText(this@WordGuessGame,"You Failed.",Toast.LENGTH_SHORT).show()
            }
        }


        btnNext.setOnClickListener{
            day = Days[random.nextInt(Days.size)]
            txtQuestionContainer.text = mixWords(day)


            etUserInput.setText("")
            txtRightAnswer.visibility = View.INVISIBLE
            txtCorrectAnswer.visibility = View.INVISIBLE
        }

        btnShow.setOnClickListener {

            txtCorrectAnswer.visibility = View.VISIBLE
            txtRightAnswer.visibility = View.VISIBLE

            txtRightAnswer.text = day
        }


    }


    fun mixWords(word:String) : String{
        val word = Arrays.asList<String>(*word.split("".toRegex()).dropLastWhile({it.isEmpty()}).toTypedArray())

        Collections.shuffle(word)
        var mixed = ""

        for (i in word){
            mixed += i

        }
        return mixed

    }















}