package com.example.androidtest.models


data class WeatherResponse(val main : Main, val weather: List<WeatherInfo>, val dt_txt: String)

//data class Main(val temp: Double, val temp_min: Double, val temp_max: Double)

//data class WeatherInfo(val id: Int, val main: String, val description: String)

//data class WeatherInformationUIModel(val date: String, val minTemp: String, val maxTemp: String, val weatherDescription: String)