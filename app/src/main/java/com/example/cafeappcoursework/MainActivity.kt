package com.example.cafeappcoursework

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeappcoursework.model.DataBaseHelper
import com.example.cafeappcoursework.model.Products

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DataBaseHelper
    private lateinit var pAdapter: ProductAdapter
    private val selectedProducts = ArrayList<Products>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        dbHelper = DataBaseHelper(this)

        val productList = dbHelper.getAllProducts()
        Log.d("MainActivity", "Product list size: ${productList.size}")
        pAdapter = ProductAdapter(applicationContext, productList) { selectedProduct ->
            // Handle the click event (add to favorites, for example)
            if (!selectedProducts.contains(selectedProduct)) {
                selectedProducts.add(selectedProduct)
            }
        }

        val productRV: RecyclerView = findViewById(R.id.popular_Items)

        // Set the adapter first
        productRV.adapter = pAdapter

        // Then set the layout manager
        productRV.layoutManager = LinearLayoutManager(applicationContext)

        // Handle the "Favorites" button click
        val favoritesButton: Button = findViewById(R.id.fvtBtn)
        favoritesButton.setOnClickListener {
            if (selectedProducts.isNotEmpty()) {
                val intent = Intent(this, Favourites::class.java)
                intent.putParcelableArrayListExtra("SELECTED_PRODUCTS", selectedProducts as ArrayList<out Parcelable>)
                startActivity(intent)
            } else {
                // Provide feedback to the user that no products are selected
                Log.e("MainActivity", "No products selected to add to favorites.")
            }
        }

        val btnFaves = findViewById<Button>(R.id.fvtBtn)
        btnFaves.setOnClickListener() {
            redirectToFaves()
        }
    }

    private fun redirectToFaves() {
        val i = Intent(this, Favourites::class.java)
        startActivity(i)
    }
}
