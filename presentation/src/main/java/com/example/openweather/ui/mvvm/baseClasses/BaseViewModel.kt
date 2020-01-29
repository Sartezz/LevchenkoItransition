package com.example.openweather.ui.mvvm.baseClasses

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposableList = CompositeDisposable()

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }

    fun addDisposable(disposable: Disposable) {
        disposableList.add(disposable)
    }
}