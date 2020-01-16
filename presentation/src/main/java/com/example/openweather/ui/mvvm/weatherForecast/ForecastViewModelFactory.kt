package com.example.openweather.ui.mvvm.weatherForecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repository.ForecastWeatherInfoRepository
import javax.inject.Inject

class ForecastViewModelFactory @Inject constructor(private val forecastWeatherInfoRepository: ForecastWeatherInfoRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForecastViewModel(
            forecastWeatherInfoRepository
        ) as T
    }
}