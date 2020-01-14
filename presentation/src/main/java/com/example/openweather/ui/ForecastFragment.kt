package com.example.openweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.openweather.R
import com.example.openweather.ui.mvvm.WeatherForecast.ForecastViewModel
import com.example.openweather.ui.mvvm.WeatherForecast.ForecastViewModelFactory
import javax.inject.Inject

class ForecastFragment : Fragment() {
    @Inject
    lateinit var forecastViewModelFactory: ForecastViewModelFactory

    companion object {
        fun newInstance(): ForecastFragment {
            return ForecastFragment()
        }
    }

    private lateinit var viewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, forecastViewModelFactory).get(ForecastViewModel::class.java)
    }
}
