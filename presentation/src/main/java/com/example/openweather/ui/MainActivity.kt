package com.example.openweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.openweather.R
import com.example.openweather.ui.mvvm.weatherForecast.ForecastFragment
import com.example.openweather.ui.mvvm.weatherInfo.WeatherInfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

const val WEATHER_INFO_FRAGMENT_TAG = "weatherInfoFragment"
const val WEATHER_FORECAST_FRAGMENT_TAG = "weatherForecastFragment"

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
            supportFragmentManager.findFragmentByTag(WEATHER_INFO_FRAGMENT_TAG)?.let {
                it as WeatherInfoFragment
            } ?: WeatherInfoFragment.newInstance(resources.getString(R.string.minsk))

        forecastFragment =
            supportFragmentManager.findFragmentByTag(WEATHER_FORECAST_FRAGMENT_TAG)?.let {
                it as ForecastFragment
            } ?: ForecastFragment.newInstance()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, weatherInfoFragment, WEATHER_INFO_FRAGMENT_TAG)
                .commit()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, forecastFragment, WEATHER_FORECAST_FRAGMENT_TAG)
                .hide(forecastFragment)
                .commit()
        }

        bottom_navigation.setOnNavigationItemSelectedListener(onNavItemSelectedListener)
    }
}

