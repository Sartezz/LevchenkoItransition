package com.example.openweather.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.WeatherInfo
import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.BuildConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherInfoViewModel(private val weatherInfoRepository: WeatherInfoRepository) :
    ViewModel() {
    private val disposableList = CompositeDisposable()
    var weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()

    fun getWeatherInfo(onSuccess: () -> Unit, onError: () -> Unit) {
        disposableList.add(
            weatherInfoRepository.getWeatherInfo(
                "Minsk",
                "metric",
                BuildConfig.API_KEY
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weatherInfo.value = it
                    onSuccess()
                }, {
                    onError()
                })
        )
    }

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }
}