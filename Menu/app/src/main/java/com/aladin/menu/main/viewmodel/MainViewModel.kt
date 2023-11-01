package com.aladin.menu.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aladin.menu.data.repository.MealRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val mealRepository: MealRepository): ViewModel() {


    fun getAllMeals() : Job{
        return viewModelScope.launch {

            val mealResponse = mealRepository.getAllMeals()
            val meals = mealResponse.meals

            for (meal in meals){
                Log.i("meal", meal.strMeal)
            }

        }
    }


}