package com.example.intprogtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            if (!etEmail.showErrorIfEmpty("Email is required")) {
                return@setOnClickListener
            }

            if (!etPassword.showErrorIfEmpty("Password is required")) {
                return@setOnClickListener
            }

            val email = etEmail.text.toString()
            if (!email.isValidEmail()) {
                etEmail.error = "Please enter a valid email address"
                return@setOnClickListener
            }

            // Simulate login success
            val myApp = application as MyApp
            myApp.setIsLoggedIn(true)

            val loginSuccessToast = Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT)
            loginSuccessToast.show()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}