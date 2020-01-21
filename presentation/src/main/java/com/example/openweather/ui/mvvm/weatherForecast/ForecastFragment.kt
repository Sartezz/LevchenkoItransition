package com.example.openweather.ui.mvvm.weatherForecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.openweather.R
import com.example.openweather.app.App
import com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter.ForecastViewModelAdapter
import kotlinx.android.synthetic.main.forecast_fragment.*
import javax.inject.Inject

class ForecastFragment : Fragment() {
    private val adapter: ForecastViewModelAdapter = ForecastViewModelAdapter()
    @Inject
    lateinit var forecastViewModelFactory: ForecastViewModelFactory
    private lateinit var swipeToRefresh: SwipeRefreshLayout
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
        swipeToRefresh = swipe_to_refresh

        if (savedInstanceState == null) {
            getWeatherInfo()
        }

        swipeToRefresh.setOnRefreshListener {
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
        swipeToRefresh.isRefreshing = true

        viewModel.getForecastWeatherInfo(
            {
                swipeToRefresh.isRefreshing = false
            },
            {
                swipeToRefresh.isRefreshing = false
                Toast.makeText(context, R.string.error_text, Toast.LENGTH_LONG).show()
            })
    }

    private fun refreshWeatherInfo() {
        viewModel.getForecastWeatherInfo({
            swipeToRefresh.isRefreshing = false
        }, {
            Toast.makeText(activity, R.string.error_text, Toast.LENGTH_LONG).show()
            swipeToRefresh.isRefreshing = false
        })
    }

    companion object {
        fun newInstance(): ForecastFragment {
            return ForecastFragment()
        }
    }
}
