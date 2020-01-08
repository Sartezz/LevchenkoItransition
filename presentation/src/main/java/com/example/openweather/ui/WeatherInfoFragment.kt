package com.example.openweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.openweather.R
import com.example.openweather.app.App
import com.example.openweather.databinding.WeatherInfoFragmentBinding
import com.example.openweather.ui.mvvm.WeatherInfoViewModel
import com.example.openweather.ui.mvvm.WeatherInfoViewModelFactory
import kotlinx.android.synthetic.main.weather_info_fragment.*
import javax.inject.Inject

class WeatherInfoFragment : Fragment() {
    @Inject
    lateinit var weatherInfoViewModelFactory: WeatherInfoViewModelFactory

    private lateinit var binding: WeatherInfoFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.weather_info_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, weatherInfoViewModelFactory)
            .get(WeatherInfoViewModel::class.java)

        binding.viewModel = viewModel

        swipe_to_refresh.setOnRefreshListener {
            viewModel.getWeatherInfo()
            swipe_to_refresh.isRefreshing = false
        }
    }

    companion object {
        fun newInstance(key: String): WeatherInfoFragment {
            val bundle = Bundle()
            bundle.putString("KEY", key)
            val fragment = WeatherInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}