package com.example.openweather.di.modules.data

import com.example.data.repository.WeatherInfoRepositoryImpl
import com.example.domain.repository.WeatherInfoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(weatherApi: WeatherInfoRepositoryImpl): WeatherInfoRepository
}