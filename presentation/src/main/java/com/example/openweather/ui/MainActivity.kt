package com.example.openweather.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.openweather.R
import com.example.openweather.ui.mvvm.weatherForecast.ForecastFragment
import com.example.openweather.ui.mvvm.weatherInfo.WeatherInfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var weatherInfoFragment: WeatherInfoFragment
    private lateinit var forecastFragment: ForecastFragment

    private val onNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_current_weather -> {
                supportFragmentManager
                    .beginTransaction()
                    .hide(forecastFragment)
                    .show(weatherInfoFragment)
                    .commit()
            }

            R.id.nav_forecast -> {
                supportFragmentManager
                    .beginTransaction()
                    .hide(weatherInfoFragment)
                    .show(forecastFragment)
                    .commit()
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherInfoFragment =
            when (supportFragmentManager.findFragmentByTag("weatherInfoFragment") != null) {
                true -> supportFragmentManager.findFragmentByTag("weatherInfoFragment") as WeatherInfoFragment
                false -> WeatherInfoFragment.newInstance(resources.getString(R.string.Minsk))
            }

        forecastFragment =
            when (supportFragmentManager.findFragmentByTag("weatherForecastFragment") != null) {
                true -> supportFragmentManager.findFragmentByTag("weatherForecastFragment") as ForecastFragment
                false -> ForecastFragment.newInstance()
            }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, weatherInfoFragment, "weatherInfoFragment")
                .commit()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, forecastFragment, "weatherForecastFragment")
                .hide(forecastFragment)
                .commit()
        }

        bottom_navigation.setOnNavigationItemSelectedListener(onNavItemSelectedListener)
    }
}

