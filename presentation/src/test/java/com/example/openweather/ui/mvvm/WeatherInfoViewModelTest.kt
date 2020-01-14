package com.example.openweather.ui.mvvm

import io.reactivex.Observable
import org.junit.Test

class WeatherInfoViewModelTest {


    @Test
    fun test() {
//        val network = Observable.just("network")
        val network = Observable.error<String>(RuntimeException("error"))
        val db = Observable.just("db")

        network
            .onErrorResumeNext(db)
            .subscribe({
                println(it)
            }, {
                println(it)
            }, {
                println("onComplete")
            })
    }
}