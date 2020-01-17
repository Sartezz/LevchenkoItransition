package com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast

class ForecastWeatherDiffUtilCallback : DiffUtil.ItemCallback<WeatherForecast>() {
    override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
        return oldItem == newItem
    }
}