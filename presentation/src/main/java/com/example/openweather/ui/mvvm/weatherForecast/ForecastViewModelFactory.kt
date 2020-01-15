package com.example.openweather.ui.mvvm.weatherForecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ForecastViewModelFactory @Inject constructor() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForecastViewModel() as T
    }
}