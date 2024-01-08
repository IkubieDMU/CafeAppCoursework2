package com.example.cafeappcoursework

import android.content.Intent
import android.database.SQLException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cafeappcoursework.model.DataBaseHelper

class Login : AppCompatActivity() {
    private lateinit var dbHelper: DataBaseHelper
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        dbHelper = DataBaseHelper(this)


        usernameET = findViewById<EditText>(R.id.editTextUsername)
        passwordET = findViewById<EditText>(R.id.editTextPassword)

        val loginButton: Button = findViewById(R.id.btnLogin)
        loginButton.setOnClickListener {
            try {
                val a = usernameET.text.toString().trim()
                val b = passwordET.text.toString()

                val validated = dbHelper.checkUser(a,b)

                if (validated) {
                    DisplayMesssage("Login Successful")
                    redirectToProfile(a)
                } else {
                    DisplayMesssage("Login not Successful")
                }


            } catch (e: SQLException) {
                // Handle the exception, e.g., log or display an error message
                e.printStackTrace()
                DisplayMesssage("Error Occurred during login")
            }
        }

    }
    private fun DisplayMesssage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun redirectToSignUp() {
        val i = Intent(this@Login, SignUp::class.java)
        startActivity(i)
    }

    private fun redirectToProfile(username: String) {
        val intent = Intent(this@Login, Profile::class.java)
        intent.putExtra("USERNAME", username)
        startActivity(intent)
    }

}