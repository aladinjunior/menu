package com.aladin.menu.data.repository

import com.aladin.menu.data.model.Meals

interface MealDetailedRepository {

    suspend fun getMealData(id: Int) : Meals
}