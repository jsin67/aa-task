package com.example.androidtest.repo

import androidx.lifecycle.MutableLiveData
import com.example.androidtest.models.WeatherInformationUIModel

interface Repo {

    fun getWeatherData(data: MutableLiveData<List<WeatherInformationUIModel>>, placeName: String)

}