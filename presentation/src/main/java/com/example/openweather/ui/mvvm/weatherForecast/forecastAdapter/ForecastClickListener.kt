package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import com.example.domain.entity.forecastWeatherInfo.ForecastDayInfo

interface ForecastClickListener {
    fun onForecastClicked(data: ForecastDayInfo, position: Int)
}