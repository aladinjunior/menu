package com.aladin.menu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aladin.menu.data.model.Meal
import com.aladin.menu.databinding.ListMealItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var meals = mutableListOf<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListMealItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    inner class ViewHolder(binding: ListMealItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(meal: Meal){

        }
    }
}