package com.example.openweather.ui.mvvm.weatherForecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openweather.R
import com.example.openweather.app.App
import com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter.ForecastViewModelAdapter
import com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter.ForecastWeatherDiffUtilCallback
import kotlinx.android.synthetic.main.forecast_fragment.*
import javax.inject.Inject

class ForecastFragment : Fragment() {
    private val adapter: ForecastViewModelAdapter = ForecastViewModelAdapter()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            getWeatherInfo()
        }

        swipe_to_refresh.setOnRefreshListener {
            refreshWeatherInfo()
        }

        forecast_list_recyclerview.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = adapter
        }

        viewModel.weatherForecastInfo.observe(viewLifecycleOwner, Observer { forecastList ->
            adapter.setForecastList(forecastList)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        forecast_list_recyclerview.adapter = null
    }

    private fun getWeatherInfo() {
        swipe_to_refresh.isRefreshing = true

        viewModel.getForecastWeatherInfo(
            {
                swipe_to_refresh.isRefreshing = false
            },
            {
                swipe_to_refresh.isRefreshing = false
                Toast.makeText(context, R.string.error_text, Toast.LENGTH_LONG).show()
            })
    }

    private fun refreshWeatherInfo() {
        viewModel.getForecastWeatherInfo({
            swipe_to_refresh.isRefreshing = false
        }, {
            Toast.makeText(activity, R.string.error_text, Toast.LENGTH_LONG).show()
            swipe_to_refresh.isRefreshing = false
        })
    }

    companion object {
        fun newInstance(): ForecastFragment {
            return ForecastFragment()
        }
    }
}
