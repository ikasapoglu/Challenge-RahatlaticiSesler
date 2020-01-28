package com.challenge.rahatlaticisesler.utils

import android.media.MediaPlayer
import android.view.View
import android.widget.ProgressBar

/**
 * Extension for setting [MediaPlayer] volume on both side.
 */
fun MediaPlayer.setVolumeBothSide(volume: Float) {
    setVolume(volume, volume)
}

fun ProgressBar.Show() {
    visibility = View.VISIBLE
}

fun ProgressBar.Hide() {
    visibility = View.GONE
}
