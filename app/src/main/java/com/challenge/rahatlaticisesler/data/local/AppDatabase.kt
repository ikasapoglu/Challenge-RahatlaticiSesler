package com.challenge.rahatlaticisesler.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.challenge.rahatlaticisesler.data.local.db.SoundDao
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.utils.Constants.Companion.DATABASE_NAME

@Database(
    entities = [Sound::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): SoundDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDb(context)
        }

        private fun createDb(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}