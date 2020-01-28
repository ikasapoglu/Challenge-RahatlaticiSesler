package com.challenge.rahatlaticisesler.utils.callbacks

import com.challenge.rahatlaticisesler.data.models.Sound

interface IItemVolumeStateChangeListener {
    fun onVolumeStateChange(volume: Int, item: Sound)
}