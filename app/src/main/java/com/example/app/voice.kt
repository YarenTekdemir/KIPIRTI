package com.example.app
import kotlin.math.min

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

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
            if (checkPermission()) {
                startSpeechRecognition()
            } else {
                requestPermission()
            }
        }
    }

    private fun startSpeechRecognition() {
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
            override fun onError(error: Int) {
                val errorMessage: String = when (error) {
                    SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                    SpeechRecognizer.ERROR_CLIENT -> "Client side error"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                    SpeechRecognizer.ERROR_NETWORK -> "Network error"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                    SpeechRecognizer.ERROR_NO_MATCH -> "No match found"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
                    SpeechRecognizer.ERROR_SERVER -> "Error from server"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
                    else -> "Unknown error"
                }
                // Handle the error appropriately, e.g., display a message to the user
                Toast.makeText(this@voice, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
            // Function to calculate Levenshtein distance between two strings
            fun levenshteinDistance(s1: String, s2: String): Int {
                val dp = Array(s1.length + 1) { IntArray(s2.length + 1) { 0 } }

                for (i in 0..s1.length) {
                    for (j in 0..s2.length) {
                        when {
                            i == 0 -> dp[i][j] = j
                            j == 0 -> dp[i][j] = i
                            s1[i - 1] == s2[j - 1] -> dp[i][j] = dp[i - 1][j - 1]
                            else -> dp[i][j] = 1 + min(min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1])
                        }
                    }
                }

                return dp[s1.length][s2.length]
            }

            // Update onResults function with Levenshtein distance calculation
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                matches?.let {
                    val spokenText = it[0].toLowerCase().replace(".", "").trim() // Extract the spoken text from the list
                    val displayedSentence = sentenceTextView.text.toString().toLowerCase().replace(".", "").trim()

                    // Calculate Levenshtein distance
                    val distance = levenshteinDistance(spokenText, displayedSentence)
                    val threshold = 3 // Adjust threshold as needed

                    // Check if the distance is below the threshold
                    val isCorrect = distance <= threshold

                    Log.d("SpeechRecognition", "Spoken Text: $spokenText")
                    Log.d("SpeechRecognition", "Displayed Sentence: $displayedSentence")
                    Log.d("SpeechRecognition", "Levenshtein Distance: $distance")
                    Log.d("SpeechRecognition", "Is Correct: $isCorrect")

                    if (isCorrect) {
                        correctSound.start() // Play correct sound
                        showConfirmationDialog()
                    } else {
                        incorrectSound.start() // Play incorrect sound
                    }
                }
            }


            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        speechRecognizer.startListening(recognizerIntent)
    }

    private fun showNextSentence() {
        Log.d("showNextSentence", "Current Index: $currentIndex")
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

    private fun checkPermission(): Boolean {
        return checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO_PERMISSION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startSpeechRecognition()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()

        // Release media player resources if initialized
        correctSound.release()
        incorrectSound.release()
    }

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 101
    }
}
