// Task 1
package com.example.intprogtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DogDetailsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_details)

        val name = intent.getStringExtra("name") ?: ""
        val age = intent.getIntExtra("age", 0)
        val breed = intent.getStringExtra("breed") ?: ""

        val tvDogName = findViewById<TextView>(R.id.tvDogName)
        val tvDogAge = findViewById<TextView>(R.id.tvDogAge)
        val tvDogBreed = findViewById<TextView>(R.id.tvDogBreed)
        val btnAdopt = findViewById<Button>(R.id.btnAdopt)


        tvDogName.text = "Name: $name"
        tvDogAge.text = "Age: $age years"
        tvDogBreed.text = "Breed: $breed"

        // Set image based on breed using our extension function
        val ivDogImage = findViewById<ImageView>(R.id.ivDogImage)
        ivDogImage.setBreedImage(breed)
        ivDogImage.makeCircular()

        btnAdopt.setOnClickListener {
            val intent = Intent(this, AdoptionStatusActivity::class.java)
            // Pass all previous data plus new data
            intent.putExtra("name", name)
            intent.putExtra("age", age)
            intent.putExtra("breed", breed)
            intent.putExtra("isAdopted", true)
            intent.putExtra("adoptionDate", System.currentTimeMillis())
            startActivity(intent)

            // Update the application state
            val myApp = application as MyApp
            myApp.setFavoriteDog(breed)
            myApp.setAdoptionCount(myApp.getAdoptionCount() + 1)

            Log.d("AdoptButton", "Favorite dog set to: ${myApp.getFavoriteDog()}")
            Log.d("AdoptButton", "Total adoptions: ${myApp.getAdoptionCount()}")
        }
    }
}