package com.aladin.menu.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aladin.menu.data.model.Meal
import com.aladin.menu.data.repository.MealRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(private val mealRepository: MealRepository) : ViewModel() {

    val mealList = MutableLiveData<List<Meal>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMeals(): Job {

        return viewModelScope.launch {
            try {
                val mealResponse = mealRepository.getAllMeals()
                val meals = mealResponse.meals

                if (meals.isEmpty()) {
                    errorMessage.postValue("List is empty!")
                } else {
                    mealList.postValue(meals)

                    meals.map { meal ->
                        val id = meal.idMeal
                        viewModelScope.async {
                            try {
                                val mealsWithDesc = mealRepository.getMealDesc(id)
                                mealsWithDesc.meals.forEach { apiMeal ->
                                    mealList.value?.forEach { meal ->
                                        if (meal.idMeal == apiMeal.idMeal) {
                                            meal.strInstructions = apiMeal.strInstructions
                                        }
                                    }
                                }

                            } catch (e: Exception) {
                                errorMessage.postValue("Error fetching meal description: ${e.message}")
                                null
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
            }
        }
    }


}
