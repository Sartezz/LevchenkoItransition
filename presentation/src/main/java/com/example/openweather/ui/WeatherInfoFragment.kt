package com.example.openweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.R
import com.example.openweather.app.App
import com.example.openweather.databinding.FragmentBlankBinding
import com.example.openweather.ui.MVVM.WeatherInfoViewModel
import com.example.openweather.ui.MVVM.WeatherInfoViewModelFactory
import kotlinx.android.synthetic.main.fragment_blank.*
import java.text.DateFormat
import javax.inject.Inject

class WeatherInfoFragment : Fragment() {

    @Inject
    lateinit var weatherInfoInfoRepositoryImpl: WeatherInfoRepository
    @Inject
    lateinit var weatherInfoViewModelFactory: WeatherInfoViewModelFactory

    private lateinit var binding: FragmentBlankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
        binding.lifecycleOwner = this
       return binding.root
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, weatherInfoViewModelFactory)
            .get(WeatherInfoViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.weatherInfo.observe(viewLifecycleOwner, Observer {

//            city_name.text = it.name
            weather_type.text = it.main
            temperature.text = getString(R.string.temperature, it.temp)
            temp_max.text = getString(R.string.temperature, it.tempMax)
            temp_min.text = getString(R.string.temperature, it.tempMin)
            feels_like_text.text = getString(R.string.temperature, it.feelsLike)
//            current_time.text = DateFormat.getDateInstance().format(it.dt * 1000)
            humidity.text = getString(R.string.humidity_info, it.humidity)
            sunrise_info.text =
                DateFormat.getTimeInstance(DateFormat.SHORT).format(it.sunrise)
            sunrise_info.text =
                DateFormat.getTimeInstance(DateFormat.SHORT).format(it.sunset)
            wind_speed.text = getString(R.string.wind_speed, it.windSpeed)
            wind_deg.text = getString(R.string.wind_deg, it.windDeg)
        })
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