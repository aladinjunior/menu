package com.aladin.menu.main.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aladin.menu.R
import com.aladin.menu.adapters.MainAdapter
import com.aladin.menu.data.repository.MealRepositoryImplementation
import com.aladin.menu.data.rest.MealAPI
import com.aladin.menu.databinding.FragmentMainBinding
import com.aladin.menu.main.viewmodel.MainViewModel
import com.aladin.menu.main.viewmodel.MainViewModelFactory

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null
    private val mealApi = MealAPI.getInstance()
    private var detailedListener: DetailedListener? = null


    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DetailedListener)
            detailedListener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        adapter = MainAdapter{ id ->
            detailedListener?.goToDetailedScreen(id)

        }

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(MealRepositoryImplementation(mealApi))
        )[MainViewModel::class.java]

        binding?.recyclerview?.adapter = adapter
        binding?.recyclerview?.layoutManager = LinearLayoutManager(requireContext())

        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    interface DetailedListener{
        fun goToDetailedScreen(id: Int)
    }
}