package com.aladin.menu.data.rest

import com.aladin.menu.data.model.Meals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MealAPI {

    @GET("filter.php?a=American")
    suspend fun getMealList(): Meals


    companion object {

        private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

        private var retrofitService: MealAPI? = null

        fun getInstance(): MealAPI {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(MealAPI::class.java)

            }
            return retrofitService!!
        }

    }
}