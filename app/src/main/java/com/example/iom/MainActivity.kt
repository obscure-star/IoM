package com.example.iom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.iom.databinding.ActivityMainBinding
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mealList: List<Meal>
    private lateinit var rvMeal: RecyclerView
    private val searchIngEditTextView get() = binding.searchFoodEditText
    private val searchIngButton get() = binding.searchButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealList = mutableListOf()
        rvMeal = binding.rvMealView
        setOnClickListeners()

    }
    private fun setOnClickListeners(){
        searchIngButton.setOnClickListener {
            getMealImageURL()
        }
    }

    private fun sanitize(text: Editable?): String {
        return text.toString().lowercase().replace(" ", "_")
    }

    private fun getMealImageURL(){
        val client = AsyncHttpClient()
        val ingredient = sanitize(searchIngEditTextView.text)
        client["https://www.themealdb.com/api/json/v1/1/filter.php?i=${ingredient}", object: JsonHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Headers?,
                json: JSON
            ) {
                Log.d("Meal success", "$json")
                mealList = Meal.createMealList(json.jsonObject.getJSONArray("meals"))
                rvMeal.adapter = MealAdapter(mealList)
                rvMeal.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
                rvMeal .layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String?,
                throwable: Throwable?
            ) {
                Log.d("Meal Error", "$errorResponse")
            }
        }]
    }
}