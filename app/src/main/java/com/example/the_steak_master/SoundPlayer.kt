package com.example.the_steak_master

import android.content.Context
import android.media.MediaPlayer

object SoundPlayer {
    private var cookSound: MediaPlayer? = null
    private var burnSound: MediaPlayer? = null
    private var winSound: MediaPlayer? = null

    fun init(context: Context) {
        cookSound = MediaPlayer.create(context, R.raw.cook)
        burnSound = MediaPlayer.create(context, R.raw.burn)
        winSound = MediaPlayer.create(context, R.raw.win)
    }

    fun playCook() {
        cookSound?.seekTo(0)
        cookSound?.start()
    }

    fun playBurn() {
        burnSound?.seekTo(0)
        burnSound?.start()
    }

    fun playWin() {
        winSound?.seekTo(0)
        winSound?.start()
    }

    fun release() {
        cookSound?.release()
        burnSound?.release()
        winSound?.release()

        cookSound = null
        burnSound = null
        winSound = null
    }
}