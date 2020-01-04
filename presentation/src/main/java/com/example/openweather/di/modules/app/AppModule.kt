package com.example.openweather.di.modules.app

import android.content.Context
import com.example.openweather.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    fun provideContext(): Context = app
}