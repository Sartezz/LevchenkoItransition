package com.example.openweather.di.modules

import com.example.data.db.dao.WeatherInfoDao
import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.ui.mvvm.WeatherInfoViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(
        weatherInfoRepository: WeatherInfoRepository,
        weatherInfoDao: WeatherInfoDao
    ): WeatherInfoViewModelFactory =
        WeatherInfoViewModelFactory(
            weatherInfoRepository,
            weatherInfoDao
        )
}