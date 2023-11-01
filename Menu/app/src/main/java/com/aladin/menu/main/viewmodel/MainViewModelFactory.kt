package com.aladin.menu.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aladin.menu.data.repository.MealRepository


class MainViewModelFactory(private val mealRepository: MealRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
           MainViewModel(mealRepository) as T
       }
        else {
            throw IllegalArgumentException("ViewModel not found")
       }
    }
}