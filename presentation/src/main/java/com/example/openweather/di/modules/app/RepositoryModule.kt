package com.example.openweather.di.modules.app

import com.example.data.repository.WeatherInfoRepositoryImpl
import com.example.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(weatherApi: WeatherInfoRepositoryImpl): WeatherRepository
}