package com.aladin.menu.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aladin.menu.data.model.Meal
import com.aladin.menu.data.repository.MealRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val mealRepository: MealRepository): ViewModel() {

    val mealList = MutableLiveData<List<Meal>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMeals() : Job{
        return viewModelScope.launch {

            val mealResponse = mealRepository.getAllMeals()
            val meals = mealResponse.meals
            if (meals.isEmpty()){
                errorMessage.postValue("List is empty!")
            } else {
                mealList.postValue(meals)
            }







        }
    }


}