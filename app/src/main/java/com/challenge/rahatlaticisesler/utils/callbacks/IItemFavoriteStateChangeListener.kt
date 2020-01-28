package com.challenge.rahatlaticisesler.utils.callbacks

import com.challenge.rahatlaticisesler.data.models.Sound

interface IItemFavoriteStateChangeListener {
    fun OnFavStateChange(boolean: Boolean, item: Sound)
}