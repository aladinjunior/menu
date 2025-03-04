package com.aladin.menu.main.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.aladin.menu.databinding.ActivityMainBinding
import com.aladin.menu.detailed.ui.MealDetailedFragment
import com.aladin.menu.util.startFragment

class MainActivity : AppCompatActivity(), MainFragment.DetailedListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var phone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

//        phone = intent?.extras?.getString(PHONE) ?: "empty phone"
//        startFragment(MainFragment())

    }

    override fun goToDetailedScreen(id: Int) {
        val fragment = MealDetailedFragment()
//        fragment.arguments = bundleOf(MEAL_ID to id, PHONE to phone)
        startFragment(fragment)
    }

    companion object {
        const val MEAL_ID = "meal_id"
    }
}