package com.example.androidtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtest.models.WeatherInformationUIModel
import com.example.androidtest.repo.Repo
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    var weatherLiveData: MutableLiveData<List<WeatherInformationUIModel>> = MutableLiveData()

    /**
     * Calls for user data.
     */
    fun getWeatherData(siteName: String) {
        repo.getWeatherData(weatherLiveData, siteName)
    }
}