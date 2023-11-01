package com.aladin.menu.data.repository

import com.aladin.menu.data.model.Meals

interface MealRepository {

    suspend fun getAllMeals() : Meals

    suspend fun getMealDesc(id: Int) : Meals

}