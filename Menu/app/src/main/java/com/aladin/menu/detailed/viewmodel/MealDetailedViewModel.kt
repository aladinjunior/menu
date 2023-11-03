package com.aladin.menu.detailed.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aladin.menu.data.model.Meals
import com.aladin.menu.data.repository.MealDetailedRepository
import kotlinx.coroutines.launch

class MealDetailedViewModel(private val mealDetailedRepository: MealDetailedRepository, private val id: Int) : ViewModel() {

    val detailedMealList = MutableLiveData<Meals>()
    val errorMessage = MutableLiveData<String>()

    fun getMealData(){
        viewModelScope.launch {
            try {
                val meals = mealDetailedRepository.getMealData(id)
                detailedMealList.postValue(meals)

            }catch (e: Exception){
                errorMessage.postValue(e.message)
                e.printStackTrace()
            }
        }
    }

}