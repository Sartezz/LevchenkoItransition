package com.example.openweather.di.component

import com.example.openweather.app.App
import com.example.openweather.di.modules.app.AppModule
import com.example.openweather.di.modules.data.NetworkModule
import com.example.openweather.di.modules.data.RepositoryModule
import com.example.openweather.di.modules.data.DbModule
import com.example.openweather.ui.WeatherInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DbModule::class]
)
interface AppComponent {
    fun inject(blankFragment: WeatherInfoFragment)
    fun inject(app: App)
}