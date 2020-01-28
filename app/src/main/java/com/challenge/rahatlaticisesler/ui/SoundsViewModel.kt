package com.challenge.rahatlaticisesler.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.rahatlaticisesler.base.BaseViewModel
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository
import com.challenge.rahatlaticisesler.utils.mediaplayer.MediaPlayerManager
import com.challenge.rahatlaticisesler.utils.setVolumeBothSide

open class SoundsViewModel : BaseViewModel() {
    private val mediaPlayerHelper = MediaPlayerManager()

    private val _favorites = MutableLiveData<List<Sound>>()
    val favorites: LiveData<List<Sound>>
        get() = _favorites

    fun playSound(sound: Sound) {
        mediaPlayerHelper.playSound(sound.soundUrl, (sound.volume / 100.toFloat()))
    }

    fun volumeStateChanged(volume: Int, sound: Sound) {
        sound.volume = volume
        mediaPlayerHelper.getPlayerByUrl(sound.soundUrl)
            ?.setVolumeBothSide((sound.volume / (100F).toFloat()))
    }


    override fun onCleared() {
        super.onCleared()
        mediaPlayerHelper.destroyAllPlayers()
    }
}