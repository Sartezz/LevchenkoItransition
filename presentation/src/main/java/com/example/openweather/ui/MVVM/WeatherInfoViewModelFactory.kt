package com.example.openweather.ui.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repository.WeatherInfoRepository
import javax.inject.Inject

class WeatherInfoViewModelFactory @Inject constructor(private val weatherInfoRepository: WeatherInfoRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherInfoViewModel(
            weatherInfoRepository
        ) as T
    }
}