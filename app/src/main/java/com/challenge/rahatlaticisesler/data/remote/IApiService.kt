package com.challenge.rahatlaticisesler.data.remote

import com.challenge.rahatlaticisesler.data.models.Category
import com.challenge.rahatlaticisesler.data.models.Sound
import com.challenge.rahatlaticisesler.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiService {

    /**
     * @return Sound categories
     */
    @GET(ApiEndpoint.ENDPOINT_CATEGORIES)
    suspend fun getCategories(): Response<List<Category>>

    /**
     * @param categoryId Category id
     * @return List of sounds that represents given category.
     */
    @GET(ApiEndpoint.ENDPOINT_CATEGORY_DETAILS)
    suspend fun getCategoryDetails(@Path("categoryId") categoryId : Int): Response<List<Sound>>


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : IApiService{

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IApiService::class.java)

        }
    }
}