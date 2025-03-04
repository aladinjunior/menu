package com.aladin.menu.detailed.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aladin.menu.R
import com.aladin.menu.data.model.Meal
import com.aladin.menu.data.repository.MealDetailedRepositoryImpl
import com.aladin.menu.data.rest.MealAPI
import com.aladin.menu.databinding.DetailedMealFragmentBinding
import com.aladin.menu.detailed.viewmodel.DetailedViewModelFactory
import com.aladin.menu.detailed.viewmodel.MealDetailedViewModel
//import com.aladin.menu.login.ui.LoginActivity.Companion.PHONE
import com.aladin.menu.main.ui.MainActivity.Companion.MEAL_ID
import com.squareup.picasso.Picasso
import java.lang.IllegalStateException

class MealDetailedFragment : Fragment(R.layout.detailed_meal_fragment) {

    private var binding: DetailedMealFragmentBinding? = null
    private lateinit var viewModel: MealDetailedViewModel
    private lateinit var phone: String


    private var id: Int? = null
    private val mealAPI = MealAPI.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        id = arguments?.getInt(MEAL_ID)
//        phone = arguments?.getString(PHONE) ?: "empty phone"

        binding = DetailedMealFragmentBinding.bind(view)
        viewModel = ViewModelProvider(
            this,
            DetailedViewModelFactory(MealDetailedRepositoryImpl(mealAPI), id ?: throw IllegalStateException("id can't be null") )
        )[MealDetailedViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onStart() {

        super.onStart()
        viewModel.detailedMealList.observe(this){ mealList ->
            mealList.meals.map {meal ->
                loadMealInfo(meal)
                val formattedMealName = meal.strMeal.replace(" ", "%20")
                val link = "$BASE_URL$phone?text=$formattedMealName"
                binding?.rate?.visibility = View.VISIBLE
                binding?.order?.setOnClickListener {
                    openWhatsapp(link)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMealData()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun loadMealInfo(meal: Meal){
        with(binding!!){
            detailedFoodName.text = meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(detailedFoodThumb)
            detailedId.text = getString(R.string.id, meal.idMeal)
            detailedArea.text = getString(R.string.area, meal.strArea)
            detailedCategory.text = getString(R.string.category, meal.strCategory)
            detailedTags.text = getString(R.string.tags, meal.strTags)
        }

    }

    private fun openWhatsapp(link: String){
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(i)

    }

    companion object {
        const val BASE_URL = "https://wa.me/55"
    }


}