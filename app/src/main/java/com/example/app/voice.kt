package com.example.app

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class voice : AppCompatActivity() {

    private lateinit var sentenceTextView: TextView
    private lateinit var checkButton: Button
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var correctSound: MediaPlayer
    private lateinit var incorrectSound: MediaPlayer

    private val sentences = listOf(
        "I love oranges.",
        "The sky is blue.",
        "Today is a beautiful day."
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voice)

        sentenceTextView = findViewById(R.id.sentenceTextView)
        checkButton = findViewById(R.id.checkButton)
        correctSound = MediaPlayer.create(this, R.raw.correct_sound)
        incorrectSound = MediaPlayer.create(this, R.raw.incorrect_sound)

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        showNextSentence()

        checkButton.setOnClickListener {
            val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            recognizerIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)

            speechRecognizer.setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {}
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray?) {}
                override fun onEndOfSpeech() {}
                override fun onError(error: Int) {}
                override fun onResults(results: Bundle?) {
                    val matches =
                        results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    matches?.let {
                        val spokenText = it[0]
                        val correctSentence = sentences[currentIndex]
                        if (spokenText.equals(correctSentence, ignoreCase = true)) {
                            // Spoken text matches the sentence
                            correctSound.start() // Play correct sound
                            showConfirmationDialog()
                        } else {
                            // Spoken text does not match the sentence
                            incorrectSound.start() // Play incorrect sound
                        }
                    }
                }

                override fun onPartialResults(partialResults: Bundle?) {}
                override fun onEvent(eventType: Int, params: Bundle?) {}
            })

            speechRecognizer.startListening(recognizerIntent)
        }
    }

    private fun showNextSentence() {
        if (currentIndex < sentences.size) {
            sentenceTextView.text = sentences[currentIndex]
            currentIndex++
        } else {
            currentIndex = 0 // Start again from the beginning
        }
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Continue?")
        builder.setMessage("Do you want to continue with more sentences?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            showNextSentence()
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
            // Handle what to do when the user chooses not to continue
        }
        builder.show()
    }
    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
        correctSound.release() // Release media player resources
        incorrectSound.release() // Release media player resources
    }
}
