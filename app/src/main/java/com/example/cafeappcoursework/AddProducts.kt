package com.example.cafeappcoursework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cafeappcoursework.model.DataBaseHelper
import com.example.cafeappcoursework.model.Products

class AddProducts : AppCompatActivity() {
    private lateinit var dbHelper: DataBaseHelper
    private lateinit var btnAddProd: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_products)

        dbHelper = DataBaseHelper(this)
        btnAddProd = findViewById(R.id.btnAddProduct)

        btnAddProd.setOnClickListener() {
            try {
                addProds()
            } catch (e: Exception) {
                // Handle the exception, e.g., log or display an error message
                e.printStackTrace()
                Toast.makeText(this, "An error occurred while adding Products", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addProds() {
        val prodNameEditText = findViewById<EditText>(R.id.editTextProductName)
        val prodTypeEditText = findViewById<EditText>(R.id.editTextProductType)
        val prodPriceEditText = findViewById<EditText>(R.id.editTextProductPrice)

        val prodName = prodNameEditText.text.toString().trim()
        val prodType = prodTypeEditText.text.toString().trim() // Fix the typo here
        val prodPriceString = prodPriceEditText.text.toString().trim()

        // Convert the string to a double (assuming it's a numerical value)
        val prodPrice: Double = if (prodPriceString.isNotEmpty()) {
            prodPriceString.toDouble()
        } else {
            0.0
        }
        val newProduct = Products(prodName, prodType, prodPrice)
        val newProductID = dbHelper.addProduct(newProduct)

        if(newProductID != -1L) {
            DisplayMesssage("New Product added")
        } else {
            DisplayMesssage("Product Not Added")
        }
    }

    private fun DisplayMesssage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}