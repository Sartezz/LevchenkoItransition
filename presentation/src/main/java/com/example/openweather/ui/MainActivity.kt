package com.example.openweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.openweather.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = WeatherInfoFragment.newInstance(resources.getString(R.string.Minsk))
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, fragment)
                .commit()
        }
    }
}

