package com.aladin.menu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aladin.menu.data.model.Meal
import com.aladin.menu.databinding.ListMealItemBinding
import com.squareup.picasso.Picasso

class MainAdapter(private val onClick: (Int) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var meals: MutableList<Meal> = mutableListOf()

    fun setMealList(newMeals: List<Meal>) {
        meals.clear()
        meals.addAll(newMeals)
        notifyItemsChanged(meals.size, newMeals.size)
    }

    private fun notifyItemsChanged(oldSize: Int, newSize: Int){
        if (oldSize == 0){
            notifyDataSetChanged()
        } else {
            notifyItemRangeRemoved(0, oldSize)
            notifyItemRangeInserted(0, newSize)
        }
    }


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

    inner class ViewHolder(private val binding: ListMealItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(meal: Meal){
            with(binding){
                Picasso.get().load(meal.strMealThumb).into(mealThumbnail)
                mealName.text = meal.strMeal
                mealDescription.text = meal.strInstructions
            }

            itemView.setOnClickListener {
                onClick(meal.idMeal)
            }

        }
    }
}