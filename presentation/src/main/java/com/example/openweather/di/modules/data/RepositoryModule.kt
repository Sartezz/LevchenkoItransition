package com.example.openweather.di.modules.data

import com.example.data.repository.ForecastWeatherInfoRepositoryImpl
import com.example.data.repository.WeatherInfoRepositoryImpl
import com.example.domain.repository.ForecastWeatherInfoRepository
import com.example.domain.repository.WeatherInfoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideWeatherInfoRepository(weatherApi: WeatherInfoRepositoryImpl): WeatherInfoRepository

    @Binds
    @Singleton
    abstract fun provideForecastRepository(weatherApi: ForecastWeatherInfoRepositoryImpl): ForecastWeatherInfoRepository
}