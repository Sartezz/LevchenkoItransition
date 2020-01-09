package com.example.openweather.di.modules

import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.ui.mvvm.WeatherInfoViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(weatherInfoRepository: WeatherInfoRepository): WeatherInfoViewModelFactory =
        WeatherInfoViewModelFactory(
            weatherInfoRepository
        )
}