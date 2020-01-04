package com.example.openweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repository.WeatherInfoRepository
import com.example.openweather.R
import com.example.openweather.app.App
import com.example.openweather.databinding.FragmentBlankBinding
import com.example.openweather.ui.mvvm.WeatherInfoViewModel
import com.example.openweather.ui.mvvm.WeatherInfoViewModelFactory
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
        R.string.app_name
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