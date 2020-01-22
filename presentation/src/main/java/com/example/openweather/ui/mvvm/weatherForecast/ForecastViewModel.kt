package com.example.openweather.ui.mvvm.weatherForecast

import android.text.format.DateFormat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.example.domain.entity.forecastWeatherInfo.ForecastDayInfo
import com.example.domain.entity.forecastWeatherInfo.WeatherForecast
import com.example.domain.repository.ForecastWeatherInfoRepository
import com.example.openweather.BuildConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ForecastViewModel(private val forecastWeatherInfoRepository: ForecastWeatherInfoRepository) :
    ViewModel() {
    private val disposableList = CompositeDisposable()
    var weatherForecastInfo: MutableLiveData<List<ForecastData>> = MutableLiveData()

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
                        if (it.isNotEmpty()) {
                            createForecastDataList(it)
                            onSuccess()
                        } else onError()
                    },
                    {
                        onError()
                    }
                )
        )
    }

    private fun createForecastDataList(list: List<WeatherForecast>) {
        val weatherData: MutableList<ForecastData> = ArrayList()
        weatherData.add(ForecastDayInfo(list[0].dt))
        weatherData.add(list[0])
        for (index in 0 until list.lastIndex) {
            if ((DateFormat.format(
                    "dd",
                    list[index + 1].dt
                ))
                != DateFormat.format(
                    "dd",
                    list[index].dt
                )
            ) weatherData.add(ForecastDayInfo(list[index + 1].dt))

            weatherData.add(list[index + 1])
        }
        weatherForecastInfo.value = weatherData
    }

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }
}

