package com.example.the_steak_master

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private var score = 0

    private lateinit var scoreView: TextView
    private lateinit var cookButton: Button
    private lateinit var burnButton: Button
    private lateinit var resetButton: Button
    private lateinit var steakImage: ImageView

    private val SCORE_KEY = "score_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        SoundPlayer.init(this)

        scoreView = findViewById(R.id.tv_score)
        cookButton = findViewById(R.id.score)
        burnButton = findViewById(R.id.steal)
        resetButton = findViewById(R.id.reset)

        steakImage = findViewById(R.id.steakImage)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(SCORE_KEY)
        }

        updateScore()

        cookButton.setOnClickListener {
            score += 1
            SoundPlayer.playCook()
            updateScore()
        }

        burnButton.setOnClickListener {
            if (score > 0) {
                score -= 1
            }
            SoundPlayer.playBurn()
            updateScore()
        }

        resetButton.setOnClickListener {
            score = 0

            cookButton.isEnabled = true
            burnButton.isEnabled = true

            steakImage.setImageResource(R.drawable.steak_raw)

            updateScore()
        }
    }

    private fun updateScore() {
        if (score == 15) {
            SoundPlayer.playWin()

            cookButton.isEnabled = false
            burnButton.isEnabled = false
            resetButton.isEnabled = true

            steakImage.setImageResource(R.drawable.steak_cooked)
        }

        scoreView.text = score.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCORE_KEY, score)
    }
}