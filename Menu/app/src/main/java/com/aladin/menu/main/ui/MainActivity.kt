package com.aladin.menu.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aladin.menu.R
import com.aladin.menu.adapters.MainAdapter
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
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = MainAdapter()
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(MealRepositoryImplementation(mealApi))
        )[MainViewModel::class.java]

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.mealList.observe(this){ meals->
            adapter.setMealList(meals)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllMeals()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}