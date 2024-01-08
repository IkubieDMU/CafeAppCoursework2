package com.example.cafeappcoursework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeappcoursework.model.DataBaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DataBaseHelper

    private lateinit var pAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)


        dbHelper = DataBaseHelper(this)

        val productList = dbHelper.getAllProducts()
        pAdapter = ProductAdapter(applicationContext, productList)

        val productRV: RecyclerView = findViewById(R.id.popular_Items)

        // Set the adapter first
        productRV.adapter = pAdapter

        // Then set the layout manager
        productRV.layoutManager = LinearLayoutManager(applicationContext)

    }
}