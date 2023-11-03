package com.aladin.menu.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aladin.menu.R
import com.aladin.menu.adapters.MainAdapter
import com.aladin.menu.data.repository.MealRepositoryImplementation
import com.aladin.menu.data.rest.MealAPI
import com.aladin.menu.databinding.ActivityMainBinding
import com.aladin.menu.detailed.ui.MealDetailedFragment
import com.aladin.menu.main.viewmodel.MainViewModel
import com.aladin.menu.main.viewmodel.MainViewModelFactory
import com.aladin.menu.util.startFragment

class MainActivity : AppCompatActivity(), MainFragment.DetailedListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        startFragment(MainFragment())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun goToDetailedScreen(id: Int) {
        val fragment = MealDetailedFragment()
        fragment.arguments = bundleOf(MEAL_ID to id)
        startFragment(fragment)
    }

    companion object {
        const val MEAL_ID = "meal_id"
    }
}