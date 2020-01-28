package com.challenge.rahatlaticisesler.data.repo

import com.challenge.rahatlaticisesler.data.local.AppDatabase
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.data.remote.IApiService
import com.challenge.rahatlaticisesler.utils.CoroutineHelper

class RelaxingSoundsRepository(private val api: IApiService, private val db: AppDatabase) {

    //Since we are getting the results as a html formatted file, need to get and convert the actual body of it.
    suspend fun getCategories() = api.getCategories().body()

    suspend fun getCategoryDetails(categoryId: Int) = api.getCategoryDetails(categoryId).body()


    suspend fun insertToFavorites(sound: Sound) {
        CoroutineHelper.doAsyncWork(
            { db.favoritesDao().insert(sound) })
    }

    suspend fun deleteFromFavorites(sound: Sound) {
        db.favoritesDao().delete(sound)
    }

    suspend fun getFavorites(): List<Sound> {
        return db.favoritesDao().getFavoriteSounds()
    }
}