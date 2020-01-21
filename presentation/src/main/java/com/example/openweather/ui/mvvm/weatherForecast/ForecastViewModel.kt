package com.example.openweather.ui.mvvm.weatherForecast

import android.text.format.DateFormat
import android.text.format.DateUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.forecastWeatherInfo.ForecastData
import com.example.domain.entity.forecastWeatherInfo.ForecastDayInfo
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
                            val weatherData: MutableList<ForecastData> = ArrayList()
                            weatherData.add(ForecastDayInfo(it[0].dt))
                            weatherData.add(it[0])
                            for (index in 0 until it.lastIndex) {
                                if ((DateFormat.format(
                                        "dd",
                                        it[index + 1].dt * DateUtils.SECOND_IN_MILLIS
                                    ))
                                    == DateFormat.format(
                                        "dd",
                                        it[index].dt * DateUtils.SECOND_IN_MILLIS
                                    )
                                ) {
                                    weatherData.add(it[index + 1])
                                } else {
                                    weatherData.add(ForecastDayInfo(it[index + 1].dt))
                                    weatherData.add(it[index + 1])
                                }
                            }
                            weatherForecastInfo.value = weatherData
                            onSuccess()
                        } else onError()
                    },
                    {
                        onError()
                    }
                )
        )
    }

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }
}

