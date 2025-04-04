package com.example.app

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.app.databinding.ResetpswdBinding
import com.example.app.databinding.VoiceBinding
import java.util.*
import kotlin.math.min

class voice : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: VoiceBinding
    private lateinit var sentenceTextView: TextView
    private lateinit var checkButton: Button
    private lateinit var volumeIcon: ImageView
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var correctSound: MediaPlayer
    private lateinit var incorrectSound: MediaPlayer
    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var lottieAnimationView2: LottieAnimationView
    private lateinit var lottieAnimationView3: LottieAnimationView
    private lateinit var lottieAnimationView4: LottieAnimationView
    private lateinit var lottieAnimationView5: LottieAnimationView
    private var isAnime1Playing = true

    private val sentences = listOf(
        "I love oranges.",
        "The sky is blue.",
        "I love cats.",
        "I love life.",
        "I am happy.",
        "My sister plays the piano very well.",
        "We live in a small town.",
        "He always wakes up early.",
        "They have a dog named Max.",
        "I need to buy some milk.",
        "Do you like watching movies?",
        "The train arrives at 9 o'clock.",
        "She wears glasses to read.",
        "We eat dinner together every evening.",
        "He rides his bike to work.",
        "I forgot to bring my umbrella.",
        "The flowers bloom in spring.",
        "My favorite season is autumn.",
        "They visit their grandparents every Sunday.",
        "She works as a teacher.",
        "I play basketball with my friends.",
        "He takes the bus to school.",
        "We go hiking in the mountains.",
        "Can you help me with my homework?",
        "Today is a beautiful day."

    )
    private var currentIndex = 0

    private var isSpeaking: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.homeButton.setOnClickListener {
            intent = Intent(applicationContext, main_page::class.java)
            startActivity(intent)
        }

        binding.profileButton.setOnClickListener {
            intent = Intent(applicationContext, profile_page_2::class.java)
            startActivity(intent)        }

        binding.gameButton.setOnClickListener {
            intent = Intent(applicationContext, game::class.java)
            startActivity(intent)        }

        binding.hearButton.setOnClickListener {
            intent = Intent(applicationContext, voice::class.java)
            startActivity(intent)        }





        // Initialize LottieAnimationViews
        lottieAnimationView = findViewById(R.id.lottieAnimationView)
        lottieAnimationView2 = findViewById(R.id.lottieAnimationView2)
        lottieAnimationView3 = findViewById(R.id.lottieAnimationView3)
        lottieAnimationView4 = findViewById(R.id.lottieAnimationView4)
        lottieAnimationView5 = findViewById(R.id.lottieAnimationView5)

        sentenceTextView = findViewById(R.id.sentenceTextView)
        checkButton = findViewById(R.id.checkButton)
        volumeIcon = findViewById(R.id.volume_icon)
        correctSound = MediaPlayer.create(this, R.raw.correct_sound)
        incorrectSound = MediaPlayer.create(this, R.raw.incorrect_sound)

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        lottieAnimationView2.visibility = View.GONE
        lottieAnimationView3.visibility = View.GONE
        lottieAnimationView4.visibility = View.GONE
        lottieAnimationView5.visibility = View.GONE


        textToSpeech = TextToSpeech(this, this)

        showNextSentence()

        checkButton.setOnClickListener {
            if (checkPermission()) {
                startSpeechRecognition()
            } else {
                requestPermission()
            }
        }

        volumeIcon.setOnClickListener {
            if (isSpeaking) {
                stopSpeaking()
            } else {
                startSpeaking()

            }
        }
    }
    private fun switchAnimations() {
        when {
            isAnime1Playing -> {
                lottieAnimationView.visibility = View.GONE
                lottieAnimationView2.visibility = View.VISIBLE
                isAnime1Playing = false
            }
            !isAnime1Playing && lottieAnimationView2.visibility == View.VISIBLE -> {
                lottieAnimationView2.visibility = View.GONE
                lottieAnimationView3.visibility = View.VISIBLE
            }
            !isAnime1Playing && lottieAnimationView3.visibility == View.VISIBLE -> {
                lottieAnimationView3.visibility = View.GONE
                lottieAnimationView4.visibility = View.VISIBLE
            }
            !isAnime1Playing && lottieAnimationView4.visibility == View.VISIBLE -> {
                lottieAnimationView4.visibility = View.GONE
                lottieAnimationView5.visibility = View.VISIBLE
            }
            !isAnime1Playing && lottieAnimationView5.visibility == View.VISIBLE -> {
                lottieAnimationView5.visibility = View.GONE
                lottieAnimationView.visibility = View.VISIBLE
                isAnime1Playing = true
            }
        }
    }

    private fun playNextAnimation() {
        switchAnimations()
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
                        playNextAnimation()

                    } else {
                        incorrectSound.start() // Play incorrect sound
                        tryagain()
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
            val intent = Intent(this@voice, main_page::class.java)
            startActivity(intent)
        }
        builder.show()
    }

    private fun tryagain() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Try Again?")
        builder.setMessage("Do you want to try again so you can become better?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            currentIndex
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(this@voice, main_page::class.java)
            startActivity(intent)
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

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val langResult = textToSpeech.isLanguageAvailable(Locale.ENGLISH)
            if (langResult == TextToSpeech.LANG_AVAILABLE || langResult == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                textToSpeech.language = Locale.ENGLISH
            } else {
                Toast.makeText(this, "English language not supported.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Initialization failed.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun startSpeaking() {
        val text = sentenceTextView.text.toString()
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        isSpeaking = true
        volumeIcon.setImageResource(R.drawable.hear) // Set the drawable for speaking state
    }

    private fun stopSpeaking() {
        textToSpeech.stop()
        isSpeaking = false
        volumeIcon.setImageResource(R.drawable.hear) // Set the drawable for not speaking state
    }


    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
        textToSpeech.stop()
        textToSpeech.shutdown()

        // Release media player resources if initialized
        correctSound.release()
        incorrectSound.release()
    }

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 101
    }
}
