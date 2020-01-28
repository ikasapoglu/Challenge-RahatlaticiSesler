package com.challenge.rahatlaticisesler.data.models

import com.google.gson.annotations.SerializedName

data class Sound(
    @SerializedName("id")
    val id: Long,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("sound_url")
    val soundUrl: String,

    var volume: Int,
    var isFavorited : Boolean = false
)