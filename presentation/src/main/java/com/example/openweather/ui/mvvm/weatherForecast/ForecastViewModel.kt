package com.example.openweather.ui.mvvm.weatherForecast

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.domain.repository.ForecastWeatherInfoRepository
import com.example.openweather.BuildConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ForecastViewModel(private val forecastWeatherInfoRepository: ForecastWeatherInfoRepository) :
    ViewModel() {
    private val disposableList = CompositeDisposable()
    var weatherForecastInfo: MutableLiveData<List<WeatherForecast>> = MutableLiveData()

    fun getForecastWeatherInfo(onSuccess: () -> Unit, onError: () -> Unit) {
        disposableList.add(
            forecastWeatherInfoRepository.getWeatherInfo(
                "Minsk",
                "metric",
                BuildConfig.API_KEY
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("aaa", it.toString())
                        weatherForecastInfo.value = it
                        onSuccess()
                    },
                    {
                        onError() }
                )
        )
    }

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }
}
