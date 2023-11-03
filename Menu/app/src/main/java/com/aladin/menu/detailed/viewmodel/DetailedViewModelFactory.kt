package com.aladin.menu.detailed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aladin.menu.data.repository.MealDetailedRepository

class DetailedViewModelFactory(
    private val mealDetailedRepository: MealDetailedRepository,
    private val id: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealDetailedViewModel::class.java)) {
            return MealDetailedViewModel(mealDetailedRepository, id) as T
        }else {
            throw IllegalArgumentException("ViewModel not found")
        }

    }
}