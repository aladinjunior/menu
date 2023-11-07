package com.aladin.menu.main.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aladin.menu.R
import com.aladin.menu.adapters.MainAdapter
import com.aladin.menu.data.model.Meal
import com.aladin.menu.data.repository.MealRepositoryImplementation
import com.aladin.menu.data.rest.MealAPI
import com.aladin.menu.databinding.FragmentMainBinding
import com.aladin.menu.main.viewmodel.MainViewModel
import com.aladin.menu.main.viewmodel.MainViewModelFactory
import java.util.Locale

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null
    private val mealApi = MealAPI.getInstance()
    private var detailedListener: DetailedListener? = null
    private var listOfMeal: List<Meal>? = null


    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DetailedListener)
            detailedListener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        adapter = MainAdapter { id ->
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onStart() {
        super.onStart()
        viewModel.mealList.observe(this) { meals ->
            listOfMeal = meals
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        with(searchView) {
            apply {
                setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText?.isNotEmpty() == true) {
//                            viewModel.fetchMeals(listOfMeal!!, newText)
//                            showSearchedMeal(newText)
                            viewModel.fetchMeals(listOfMeal!!, newText, adapter)
                            return true
                        }
                        return false
                    }
                })
            }
        }
    }


    interface DetailedListener {
        fun goToDetailedScreen(id: Int)
    }
}