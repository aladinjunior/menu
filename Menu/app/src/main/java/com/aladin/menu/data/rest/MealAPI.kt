package com.aladin.menu.data.rest

import com.aladin.menu.data.model.Meals
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

    @GET("filter.php?a=American")
    suspend fun getMealList(): Meals

    @GET("lookup.php")
    suspend fun getMealDesc(@Query("i") i: Int) : Meals


    companion object {

        private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

        private var retrofitService: MealAPI? = null


        fun getInstance(): MealAPI {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(MealAPI::class.java)

            }
            return retrofitService!!
        }

    }
}