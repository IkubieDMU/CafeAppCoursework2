package com.example.cafeappcoursework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeappcoursework.model.FavoriteItem

class Favourites : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        // Receive selected products from the intent
        val selectedProducts = intent.getParcelableArrayListExtra<FavoriteItem>("SELECTED_PRODUCTS")
        Log.d("FavouritesActivity", "Selected products size: ${selectedProducts?.size}")

        // Check if the list is not null and update the RecyclerView
        if (selectedProducts != null && selectedProducts.isNotEmpty()) {
            val favoritesRV: RecyclerView = findViewById(R.id.favouritesRecyclerView)
            val favoritesAdapter = FavoritesAdapter(applicationContext, selectedProducts)
            favoritesRV.adapter = favoritesAdapter
            favoritesRV.layoutManager = LinearLayoutManager(applicationContext)
        } else {
            // Provide feedback that there are no selected products
            Log.e("FavouritesActivity", "No selected products to display.")
        }
    }
}
