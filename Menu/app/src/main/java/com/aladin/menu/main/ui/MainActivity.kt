package com.aladin.menu.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aladin.menu.data.repository.MealRepositoryImplementation
import com.aladin.menu.data.rest.MealAPI
import com.aladin.menu.databinding.ActivityMainBinding
import com.aladin.menu.main.viewmodel.MainViewModel
import com.aladin.menu.main.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mealApi = MealAPI.getInstance()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(MealRepositoryImplementation(mealApi))
        )[MainViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllMeals()
    }
}