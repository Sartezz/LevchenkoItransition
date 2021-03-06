package com.example.openweather.di.component

import com.example.openweather.app.App
import com.example.openweather.di.modules.app.AppModule
import com.example.openweather.di.modules.app.NetworkModule
import com.example.openweather.di.modules.app.RepositoryModule
import com.example.openweather.ui.BlankFragment
import com.example.openweather.ui.MainScreen
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class]
)
interface AppComponent {
    fun inject(mainScreen: MainScreen)
    fun inject(blankFragment: BlankFragment)
    fun inject(app: App)
}