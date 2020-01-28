package com.example.openweather.ui.mvvm.weatherForecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.openweather.R
import com.example.openweather.app.App
import com.example.openweather.databinding.ForecastFragmentBinding
import com.example.openweather.ui.mvvm.weatherForecast.forecastAdapter.ForecastViewModelAdapter
import kotlinx.android.synthetic.main.forecast_fragment.*
import javax.inject.Inject

const val SAVED_EXPANDED_LIST = "savedExpandedList"

class ForecastFragment : Fragment() {
    private val adapter: ForecastViewModelAdapter = ForecastViewModelAdapter()
    @Inject
    lateinit var forecastViewModelFactory: ForecastViewModelFactory
    private lateinit var swipeToRefresh: SwipeRefreshLayout
    private lateinit var viewModel: ForecastViewModel
    private lateinit var binding: ForecastFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            adapter.restorePreviousData(savedInstanceState, SAVED_EXPANDED_LIST)
        }

        viewModel =
            ViewModelProvider(this, forecastViewModelFactory).get(ForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.forecast_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeToRefresh = swipe_to_refresh

        forecast_list_recyclerview.also {
            it.setHasFixedSize(true)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        if (savedInstanceState == null) getWeatherInfo()

        swipeToRefresh.setOnRefreshListener {
            refreshWeatherInfo()
        }

        binding.viewModel = viewModel
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        adapter.saveRecyclerData(outState, SAVED_EXPANDED_LIST)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        forecast_list_recyclerview.adapter = null
    }

    private fun getWeatherInfo() {
        viewModel.getForecastWeatherInfo {
            Toast.makeText(context, R.string.error_text, Toast.LENGTH_LONG).show() }
    }

    private fun refreshWeatherInfo() {
        viewModel.getForecastWeatherInfo {
            Toast.makeText(activity, R.string.error_text, Toast.LENGTH_LONG).show() }
    }

    companion object {
        fun newInstance(): ForecastFragment {
            return ForecastFragment()
        }
    }
}