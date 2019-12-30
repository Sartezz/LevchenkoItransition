package com.example.openweather.app

import android.app.Application
import com.example.openweather.di.component.AppComponent
import com.example.openweather.di.component.DaggerAppComponent
import com.example.openweather.di.modules.app.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}