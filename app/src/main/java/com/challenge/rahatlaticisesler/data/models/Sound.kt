package com.challenge.rahatlaticisesler.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorites")
data class Sound(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Long,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("sound_url")
    val soundUrl: String,

    var volume: Int = 100,
    var isFavorited : Boolean = false
)