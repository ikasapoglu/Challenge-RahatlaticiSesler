package com.challenge.rahatlaticisesler.data.local.db

import androidx.room.*
import com.challenge.rahatlaticisesler.data.models.Sound

@Dao
interface SoundDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sound: Sound): Long

    @Query("SELECT * FROM favorites")
    suspend fun getFavoriteSounds(): List<Sound>

    @Delete
    suspend fun delete(sound: Sound)
}