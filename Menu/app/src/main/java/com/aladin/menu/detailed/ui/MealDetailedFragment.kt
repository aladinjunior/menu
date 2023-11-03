package com.aladin.menu.detailed.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.aladin.menu.R
import com.aladin.menu.databinding.DetailedMealFragmentBinding

class MealDetailedFragment : Fragment(R.layout.detailed_meal_fragment) {

    private var binding: DetailedMealFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}