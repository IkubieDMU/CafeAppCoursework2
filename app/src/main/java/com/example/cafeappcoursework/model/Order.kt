package com.example.cafeappcoursework.model

data class Order(
    val customerName: String,
    val custPhoneNo: String,
    val orderDesc: String,
    val tableNo: String,
    val price: Int
)
