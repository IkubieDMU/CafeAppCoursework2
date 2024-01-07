package com.example.cafeappcoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cafeappcoursework.model.DataBaseHelper
import com.example.cafeappcoursework.model.User

class SignUp : AppCompatActivity() {

    // Declaring variable for the DataBaseHelper and the sign up button
    private lateinit var dbHelper: DataBaseHelper
    private lateinit var btnSignUp: Button
    private lateinit var btnGoToLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //Sign-Up Functionality
        dbHelper = DataBaseHelper(this)
        val db = dbHelper.readableDatabase
//        dbHelper.importDatabase()

        btnSignUp = findViewById<Button>(R.id.buttonSignUp) //Button variable
        btnGoToLogin = findViewById(R.id.btnSampleGoToLogin)

        btnSignUp.setOnClickListener {
            try {
                signUp()
//                   dbHelper.close()
            } catch (e: Exception) {
                // Handle the exception, e.g., log or display an error message
                e.printStackTrace()
                Toast.makeText(this, "An error occurred during sign up", Toast.LENGTH_SHORT).show()
            }

        }

        btnGoToLogin.setOnClickListener {
            redirectToLogin()
        }

    }

    private fun signUp() {
        // Get user input
        val fullNameEditText = findViewById<EditText>(R.id.editTextFullName)
        val phoneNoEditText = findViewById<EditText>(R.id.editTextPhoneNo)
        val usernameEditText = findViewById<EditText>(R.id.editTextPUsername)
        val emailEditText = findViewById<EditText>(R.id.editTextEmailSignUp)
        val passwordEditText = findViewById<EditText>(R.id.editTextPasswordSignUp)

        val fullname = fullNameEditText.text.toString().trim()
        val phoneNo = phoneNoEditText.text.toString().trim()
        val username = usernameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validate input
        if (fullname.isEmpty()|| username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            DisplayMesssage("Please don't leave them empty")
        }
        val newUser = User(fullname,phoneNo,email,username,password)
        val newUserId = dbHelper.addUser(newUser)

        if (newUserId != -1L) {
            DisplayMesssage("User successfully added!")
//            redirectToLogin()
        } else {
            DisplayMesssage("Sign Up Failed")
        }
    }

    private fun DisplayMesssage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun redirectToLogin() {
        val i = Intent(this@SignUp,Login::class.java)
        startActivity(i)
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}