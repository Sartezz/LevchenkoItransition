package com.example.openweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.openweather.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var weatherInfoFragment: WeatherInfoFragment
    lateinit var forecastFragment: ForecastFragment

    private val onNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_current_weather -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_holder, weatherInfoFragment)
                    .commit()
            }
            R.id.nav_forecast -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_holder, forecastFragment)
                    .commit()
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val weatherInfoFragment =
                WeatherInfoFragment.newInstance(resources.getString(R.string.Minsk))
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, weatherInfoFragment)
                .commit()
        }
    }
}

