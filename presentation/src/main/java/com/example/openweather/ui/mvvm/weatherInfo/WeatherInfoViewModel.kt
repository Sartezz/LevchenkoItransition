package com.example.openweather.ui.mvvm.weatherInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.weatherInfo.WeatherInfo
import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.BuildConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherInfoViewModel(private val weatherInfoRepository: WeatherInfoRepository) : ViewModel() {
    private val disposableList = CompositeDisposable()
    var weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()
    var isVisible: MutableLiveData<Boolean> = MutableLiveData()
    var isRefreshing: MutableLiveData<Boolean> = MutableLiveData()

    fun getWeatherInfo(onSuccess: () -> Unit, onError: () -> Unit) {
        isVisible.value = false
        isRefreshing.value = true
        disposableList.add(
            weatherInfoRepository.getWeatherInfo(
                "Minsk",
                "metric",
                BuildConfig.API_KEY
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        isVisible.value = true
                        isRefreshing.value = false
                        weatherInfo.value = it
                        onSuccess()
                    }, {
                        isVisible.value = false
                        onError()
                    })
        )
    }

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }
}