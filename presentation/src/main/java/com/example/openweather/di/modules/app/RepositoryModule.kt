package com.example.openweather.di.modules.app

import com.example.data.repository.WeatherInfoRepositoryImpl
import com.example.data.rest.api.CurrentWeatherApi
import com.example.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(weatherApi: CurrentWeatherApi): WeatherRepository =
        WeatherInfoRepositoryImpl(weatherApi)
}