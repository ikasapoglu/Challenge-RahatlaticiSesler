package com.challenge.rahatlaticisesler.utils.mediaplayer

import android.media.MediaPlayer

/**
 * This class holds its current url. It usable for identify it.
 */
open class AppMediaPlayer : MediaPlayer() {
    var url: String? = null

    override fun setDataSource(path: String?) {
        super.setDataSource(path)
        url = path
    }
}