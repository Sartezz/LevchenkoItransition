package com.example.openweather.di.modules.data

import android.content.Context
import com.example.data.db.AppDataBase
import com.example.data.db.dao.WeatherInfoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideRoom(context: Context): AppDataBase = AppDataBase.getInstance(context)

    @Singleton
    @Provides
    fun provideWeatherInfoDao(dataBase: AppDataBase): WeatherInfoDao = dataBase.weatherInfoDao()
}