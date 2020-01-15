package com.example.openweather.di.component

import com.example.openweather.app.App
import com.example.openweather.di.modules.app.AppModule
import com.example.openweather.di.modules.data.DbModule
import com.example.openweather.di.modules.data.NetworkModule
import com.example.openweather.di.modules.data.RepositoryModule
import com.example.openweather.ui.mvvm.weatherForecast.ForecastFragment
import com.example.openweather.ui.mvvm.weatherInfo.WeatherInfoFragment
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
    fun inject(weatherInfoFragment: WeatherInfoFragment)
    fun inject(forecastFragment: ForecastFragment)
    fun inject(app: App)
}