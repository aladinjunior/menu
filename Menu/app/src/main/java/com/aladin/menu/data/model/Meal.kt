package com.aladin.menu.data.model

data class Meal(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: Int,
    var strInstructions: String? = null,
    var strCategory: String? = null,
    var strArea: String? = null,
    var strTags: String? = null
)
