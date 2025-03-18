// Task 1

package com.example.intprogtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Check if user is logged in
        val myApp = application as MyApp

        if (!myApp.getIsLoggedIn()) {
            // Go to login screen
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            setContentView(R.layout.main)
            setupMainUI()


            // Favorite dog
            if (myApp.getFavoriteDog().isNotEmpty()) {
                val tvFavoriteDog = findViewById<TextView>(R.id.tvFavoriteDog)
                tvFavoriteDog.text = "Your favorite breed: ${myApp.getFavoriteDog()}"
                tvFavoriteDog.show()
            }
        }
    }


    private fun setupMainUI() {
        val etDogName = findViewById<EditText>(R.id.etDogName)
        val etDogAge = findViewById<EditText>(R.id.etDogAge)
        val etDogBreed = findViewById<EditText>(R.id.etDogBreed)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnNext.setOnClickListener {

            if (!etDogName.showErrorIfEmpty("Dog name is required")) {
                return@setOnClickListener
            }

            if (!etDogAge.showErrorIfEmpty("Age is required")) {
                return@setOnClickListener
            }

            if (!etDogBreed.showErrorIfEmpty("Breed is required")) {
                return@setOnClickListener
            }

            val name = etDogName.text.toString()
            val age = etDogAge.text.toString().toIntOrNull() ?: 0
            val breed = etDogBreed.text.toString().removeSpecialChars()

            val intent = Intent(this, DogDetailsActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("age", age)
            intent.putExtra("breed", breed)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            // Clear user login status
            val myApp = application as MyApp
            myApp.setIsLoggedIn(false)

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
    }
}
