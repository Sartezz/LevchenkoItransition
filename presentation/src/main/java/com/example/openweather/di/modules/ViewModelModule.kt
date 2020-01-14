package com.example.openweather.di.modules

import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.ui.mvvm.WeatherForecast.ForecastViewModelFactory
import com.example.openweather.ui.mvvm.WeatherInfo.WeatherInfoViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(weatherInfoRepository: WeatherInfoRepository): WeatherInfoViewModelFactory =
        WeatherInfoViewModelFactory(weatherInfoRepository)

    @Provides
    @Singleton
    fun provideForecastViewModelFactory(
    ): ForecastViewModelFactory = ForecastViewModelFactory()
}