package com.example.openweather.di.modules.data

import android.content.Context
import androidx.room.Room
import com.example.data.db.AppDataBase
import com.example.data.db.dao.ForecastDataDao
import com.example.data.db.dao.WeatherInfoDao
import com.example.utils.WEATHER_INFO_DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideRoom(context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, WEATHER_INFO_DATABASE)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideWeatherInfoDao(dataBase: AppDataBase): WeatherInfoDao = dataBase.weatherInfoDao()

    @Singleton
    @Provides
    fun provideForecastDataDao(dataBase: AppDataBase): ForecastDataDao = dataBase.forecastDataDao()
}