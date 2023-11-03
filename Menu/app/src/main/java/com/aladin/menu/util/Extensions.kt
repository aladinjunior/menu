package com.aladin.menu.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aladin.menu.R
import com.aladin.menu.main.ui.MainFragment

fun AppCompatActivity.startFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction()
        .replace(R.id.main_fragment, fragment)
        .commit()

}