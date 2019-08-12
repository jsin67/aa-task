package com.example.androidtest.models


data class WeatherResponse(val main : Main, val weather: List<WeatherInfo>, val dt_txt: String)