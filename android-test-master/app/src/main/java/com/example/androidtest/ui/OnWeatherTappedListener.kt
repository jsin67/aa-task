package com.example.androidtest.ui

import com.example.androidtest.models.WeatherResponse

interface OnWeatherTappedListener {
    fun onItemTapped(weatherResponse: WeatherResponse)
}