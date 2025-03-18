// Task 3: Custom Application Class

package com.example.intprogtask

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MyApp : Application() {
    // Private properties with encapsulation
    private var isLoggedIn: Boolean = false
    private var favoriteDog: String = ""
    private var adoptionCount: Int = 0
    private lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        preferences = getSharedPreferences("dog_adopt_prefs", Context.MODE_PRIVATE)
        // Clear login, erase this line if u want to stay logged in on refresh
        //preferences.edit().clear().apply()


        // Load saved values
        isLoggedIn = preferences.getBoolean("isLoggedIn", false)
        favoriteDog = preferences.getString("favoriteDog", "") ?: ""
        adoptionCount = preferences.getInt("adoptionCount", 0)
    }

    // Getters and setters
    fun getIsLoggedIn(): Boolean = isLoggedIn

    fun setIsLoggedIn(loggedIn: Boolean) {
        isLoggedIn = loggedIn
        preferences.edit().putBoolean("isLoggedIn", loggedIn).apply()
    }

    fun getFavoriteDog(): String = favoriteDog

    fun setFavoriteDog(dog: String) {
        favoriteDog = dog
        preferences.edit().putString("favoriteDog", dog).apply()
    }

    fun getAdoptionCount(): Int = adoptionCount

    fun setAdoptionCount(count: Int) {
        adoptionCount = count
        preferences.edit().putInt("adoptionCount", count).apply()
    }

}