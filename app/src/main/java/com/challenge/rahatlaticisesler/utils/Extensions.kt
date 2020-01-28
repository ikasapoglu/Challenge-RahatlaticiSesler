package com.challenge.rahatlaticisesler.utils

import android.media.MediaPlayer

/**
 * Extension for setting [MediaPlayer] volume on both side.
 */
fun MediaPlayer.setVolumeBothSide(volume: Float) {
    setVolume(volume, volume)
}