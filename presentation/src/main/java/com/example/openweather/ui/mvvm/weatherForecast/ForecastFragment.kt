package com.example.openweather.ui.mvvm.weatherForecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.openweather.R
import com.example.openweather.app.App
import javax.inject.Inject

class ForecastFragment : Fragment() {
    @Inject
    lateinit var forecastViewModelFactory: ForecastViewModelFactory
    private lateinit var viewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProvider(this, forecastViewModelFactory).get(ForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_fragment, container, false)
    }

    companion object {
        fun newInstance(): ForecastFragment {
            return ForecastFragment()
        }
    }
}
