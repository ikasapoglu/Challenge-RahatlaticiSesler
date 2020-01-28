package com.challenge.rahatlaticisesler.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.rahatlaticisesler.base.BaseViewModel
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.data.repo.RelaxingSoundsRepository
import com.challenge.rahatlaticisesler.utils.CoroutineHelper
import com.challenge.rahatlaticisesler.utils.mediaplayer.MediaPlayerManager
import com.challenge.rahatlaticisesler.utils.setVolumeBothSide

open class SoundsViewModel(private val repository: RelaxingSoundsRepository) : BaseViewModel() {
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

    fun favoriteStatusChanged(state: Boolean, sound: Sound) {
        if (state) {
            sound.isFavorited = true
            addFavorites(sound)
        } else {
            sound.isFavorited = false
            removeFavorites(sound)
        }
    }

    //Favorites
    fun getFavorites() {
        CoroutineHelper.doAsyncWork(
            { repository.getFavorites() },
            { favs ->
                _favorites.value = favs
            })
    }

    private fun addFavorites(sound: Sound) {
        CoroutineHelper.doAsyncWork(
            { repository.insertToFavorites(sound) })
    }

    private fun removeFavorites(sound: Sound) {
        CoroutineHelper.doAsyncWork(
            { repository.deleteFromFavorites(sound) })
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayerHelper.destroyAllPlayers()
    }
}