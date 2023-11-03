package com.aladin.menu.data.repository

import com.aladin.menu.data.model.Meals
import com.aladin.menu.data.rest.MealAPI

class MealDetailedRepositoryImpl(private val mealAPI: MealAPI) : MealDetailedRepository {

    override suspend fun getMealData(id: Int) : Meals {
        return mealAPI.getMealDesc(id)
    }
}