package com.example.openweather.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.db.dao.WeatherInfoDao
import com.example.domain.repository.WeatherInfoRepository
import javax.inject.Inject

class WeatherInfoViewModelFactory @Inject constructor(private val weatherInfoRepository: WeatherInfoRepository, private val weatherInfoDao: WeatherInfoDao) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherInfoViewModel(
            weatherInfoRepository,
            weatherInfoDao
        ) as T
    }
}