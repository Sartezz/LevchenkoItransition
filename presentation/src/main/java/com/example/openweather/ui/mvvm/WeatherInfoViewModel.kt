package com.example.openweather.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.db.dao.WeatherInfoDao
import com.example.domain.entity.WeatherInfo
import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.BuildConfig
import com.example.utils.transformToWeatherInfo
import com.example.utils.transformToWeatherInfoDb
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherInfoViewModel(
    private val weatherInfoRepository: WeatherInfoRepository,
    private val weatherInfoDao: WeatherInfoDao
) :
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
                .onErrorResumeNext(weatherInfoDao.getWeatherInfo().map {
                    it.transformToWeatherInfo()
                }.toSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        weatherInfo.value = it
                        disposableList.add(
                             Completable.fromCallable { weatherInfoDao.deleteWeatherInfo() }
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                        )
                        disposableList.add(
                            weatherInfoDao.addWeatherInfo(it.transformToWeatherInfoDb())
                                .subscribeOn(Schedulers.io())
                                .subscribe()
                        )
                        onSuccess()
                    },
                    {
                        onError()
                    })
        )
    }

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }
}