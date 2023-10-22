package com.example.iom

import org.json.JSONArray

class Meal (val id: String, val name: String, val ImageUrl: String) {
    companion object {
        fun createMealList (mealArray: JSONArray): ArrayList<Meal> {
            val mealList = ArrayList<Meal>()
            for (i in 0 until mealArray.length()){
                mealList.add(
                    Meal(
                    mealArray.getJSONObject(i).getString("idMeal"),
                    mealArray.getJSONObject(i).getString("strMeal"),
                    mealArray.getJSONObject(i).getString("strMealThumb")))
            }
            return mealList
        }
    }
}