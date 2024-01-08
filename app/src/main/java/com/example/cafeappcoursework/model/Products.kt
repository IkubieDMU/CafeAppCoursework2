package com.example.cafeappcoursework.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Products(
    val productName: String,
    val type: String,
    val price: Double
)
