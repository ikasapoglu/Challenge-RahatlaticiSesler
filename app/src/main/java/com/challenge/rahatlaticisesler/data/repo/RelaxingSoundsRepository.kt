package com.challenge.rahatlaticisesler.data.repo

import com.challenge.rahatlaticisesler.data.remote.IApiService

class RelaxingSoundsRepository(private val api: IApiService) {

    //Since we are getting the results as a html formatted file, need to get and convert the actual body of it.
    suspend fun getCategories() = api.getCategories().body()

    suspend fun getCategoryDetails(categoryId: Int) = api.getCategoryDetails(categoryId).body()
}