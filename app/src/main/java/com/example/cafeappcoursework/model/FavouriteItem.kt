// FavoriteItem.kt
package com.example.cafeappcoursework.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteItem(
    // Define properties of your favorite item
    val productName: String,
    val type: String,
    val price: Double
) : Parcelable

