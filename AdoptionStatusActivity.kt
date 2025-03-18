//Task 1

package com.example.intprogtask

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.content.Intent

class AdoptionStatusActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adoption_status)

        // Retrieve all passed data
        val name = intent.getStringExtra("name") ?: ""
        val age = intent.getIntExtra("age", 0)
        val breed = intent.getStringExtra("breed") ?: ""
        val isAdopted = intent.getBooleanExtra("isAdopted", false)
        val adoptionDate = intent.getLongExtra("adoptionDate", System.currentTimeMillis())

        // Format the date
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(Date(adoptionDate))

        // Display the adoption status
        val tvAdoptionStatus = findViewById<TextView>(R.id.tvAdoptionStatus)
        val tvAdoptionDetails = findViewById<TextView>(R.id.tvAdoptionDetails)
        val ivDogImage = findViewById<ImageView>(R.id.ivAdoptedDogImage)
        val btnHome = findViewById<Button>(R.id.btnHome)

        // Use extension functions
        tvAdoptionStatus.text = if (isAdopted)
            "Congratulations!"
        else
            "Not Adopted"

        tvAdoptionDetails.text = "You've adopted ${name.capitalizeWords()}, a $age year old $breed on $formattedDate"

        // Set image and apply circular crop
        ivDogImage.setBreedImage(breed)
        ivDogImage.makeCircular()

        // Access app state
        val app = application as MyApp
        findViewById<TextView>(R.id.tvTotalAdoptions).text = "Total adoptions: ${app.getAdoptionCount()}"

        // Return to home screen
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}