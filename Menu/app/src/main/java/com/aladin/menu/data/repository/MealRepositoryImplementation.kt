package com.aladin.menu.data.repository

import com.aladin.menu.data.model.Meal
import com.aladin.menu.data.model.Meals
import com.aladin.menu.data.rest.MealAPI

class MealRepositoryImplementation(private val mealAPI: MealAPI) : MealRepository {

    override suspend fun getAllMeals(): Meals {
        return mealAPI.getMealList()
    }
}