package com.example.iom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MealAdapter(private val mealList: List<Meal>) : RecyclerView.Adapter<MealAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val mealImageView: ImageView = view.findViewById(R.id.mealImageView)
        val mealNameTextView: TextView = view.findViewById(R.id.mealNameTextView)
        val mealIdTextView: TextView = view.findViewById(R.id.mealIdTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_item, parent, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList[position]
        holder.mealIdTextView.text = meal.id
        holder.mealNameTextView.text = meal.name
        Glide.with(holder.itemView)
            .load(meal.ImageUrl)
            .centerCrop()
            .into(holder.mealImageView)

        // `holder` can used to reference any View within the RecyclerView item's layout file
        holder.mealImageView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${meal.name} clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = mealList.size
}