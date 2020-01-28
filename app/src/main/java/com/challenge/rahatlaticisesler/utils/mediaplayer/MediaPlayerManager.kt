package com.challenge.rahatlaticisesler.utils.mediaplayer

import android.media.AudioManager
import android.media.MediaPlayer
import com.challenge.rahatlaticisesler.utils.setVolumeBothSide

/**
 * Main purpose of this class; managing the [MediaPlayer]s in whole app.
 * [MediaPlayer] creation processes are large operations, so this class creates a pool and be responsible for creating and handling [MediaPlayer]s' lifecycle.
 *
 * When getting an instance from this class just call [playSound] method in anywhere. It will manage all states automatically.
 * Don't worry about initial pool size,
 * it will automatically increasing when more need players and returns back to initial pool size with removing surpluses players when they are completed.
 *
 * IMPORTANT: Don't forget to call [destroyAllPlayers] method when done with it.
 *
 * @param poolSize Initialize pool size. Give an amount to estimated sound size that plays concurrent. Default value is 10.
 */
class MediaPlayerManager(val poolSize: Int = 10) {

    private val playersInUse = mutableListOf<AppMediaPlayer>()

    private val mediaPlayerPool = mutableListOf<AppMediaPlayer>().also {
        for (i in 0..poolSize) {
            it.add(buildPlayer())
        }
    }

    /**
     * This method builds the players. And play it whenever prepared.
     */
    private fun buildPlayer() = AppMediaPlayer().apply {
        setOnPreparedListener { start() }
        setOnCompletionListener { reUsePlayer(it as AppMediaPlayer) }
    }

    /**
     * Returns a [MediaPlayer] if one is available, if there is nothing build a new one
     */
    private fun requestPlayer(): AppMediaPlayer {
        return if (mediaPlayerPool.isNotEmpty()) {
            mediaPlayerPool.removeAt(0).also {
                playersInUse.add(it)
            }
        } else buildPlayer()
    }

    /**
     * If size of the mediaplayerpool objects larger than poolsize then remove mediplayer item from pool for keeping the pool size initial value.
     */
    private fun reUsePlayer(mediaPlayer: AppMediaPlayer) {
        mediaPlayer.reset()
        playersInUse.remove(mediaPlayer)
        if (mediaPlayerPool.size < poolSize) {
            mediaPlayerPool.add(mediaPlayer)
        } else {
            mediaPlayerPool.remove(mediaPlayer)
        }
    }

    /**
     * Automatically plays a sound in given url when is preaped.
     * @param url Url of the sound
     * @param volume An initial volume for sound
     */
    fun playSound(url: String, volume: Float): AppMediaPlayer {
        val mediaPlayer = requestPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setVolumeBothSide(volume)
        mediaPlayer.run {
            setDataSource(url)
            prepareAsync()
        }
        return mediaPlayer
    }

    fun getPlayerByUrl(url: String): AppMediaPlayer? = playersInUse.firstOrNull { t -> t.url == url }

    /**
     * Kills all the players in the pool. Call it when you done with playing sounds.
     */
    fun destroyAllPlayers() {
        playersInUse.forEach {
            if (it.isPlaying)
                it.stop()
            it.release()
        }
        mediaPlayerPool.forEach {
            if (it.isPlaying)
                it.stop()
            it.release()
        }
        playersInUse.clear()
        mediaPlayerPool.clear()
    }
}

