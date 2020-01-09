package com.example.openweather.di.modules.data

import android.content.Context
import androidx.room.Room
import com.example.data.db.AppDataBase
import com.example.data.db.dao.WeatherInfoDao
import com.example.utils.DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideRoom(context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideWeatherInfoDao(dataBase: AppDataBase): WeatherInfoDao = dataBase.weatherInfoDao()
}