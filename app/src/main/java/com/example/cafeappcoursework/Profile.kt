package com.example.cafeappcoursework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeappcoursework.model.DataBaseHelper

class Profile : AppCompatActivity() {
    private lateinit var dbHelper: DataBaseHelper
    private lateinit var usernameTextView: TextView
    private lateinit var fullNameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbHelper = DataBaseHelper(this)

        // Retrieve username from intent
        val username = intent.getStringExtra("USERNAME") ?: ""

        // Initialize views
        usernameTextView = findViewById(R.id.textViewUsername)
        fullNameTextView = findViewById(R.id.textViewFullName)
        emailTextView = findViewById(R.id.textViewEmail)
        btnLogout = findViewById(R.id.btnLogout)

        // Fetch user details based on the username
        val user = dbHelper.getUserDetails(username)

        // Display user details
        if (user != null) {
            usernameTextView.text = user.usersUsername
            fullNameTextView.text = user.fullname
            emailTextView.text = user.email
        }

        // Set onClickListener for Logout button
        btnLogout.setOnClickListener {
            redirectToLogin()
        }
    }

    private fun redirectToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish() // Optional: Close the current activity if needed
    }
}
