package com.example.openweather.ui

import androidx.lifecycle.ViewModel
import com.example.domain.repository.WeatherInfoRepository

class WeatherInfoViewModel(private val weatherInfoInfoRepository: WeatherInfoRepository) :
    ViewModel() {
    fun getWeatherInfo(cityname: String?, units: String, key: String) =
        weatherInfoInfoRepository.getWeatherInfo(cityname, units, key)
}