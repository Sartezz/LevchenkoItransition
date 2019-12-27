package com.example.openweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.repository.WeatherRepository
import com.example.openweather.R
import javax.inject.Inject

class MainScreen : AppCompatActivity() {

    @Inject
    lateinit var weatherInfoRepositoryImpl: WeatherRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = BlankFragment.newInstance("Minsk")

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_holder, fragment)
            .commit()
    }
}
