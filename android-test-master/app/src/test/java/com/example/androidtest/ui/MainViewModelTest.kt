package com.example.androidtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidtest.models.Main
import com.example.androidtest.models.WeatherInfo
import com.example.androidtest.models.WeatherResponse
import com.example.androidtest.repo.Repo
import com.nhaarman.mockito_kotlin.mock
import org.junit.*

class MainViewModelTest {

    private lateinit var repo: Repo
    private lateinit var viewModel : MainViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repo = mock()
        viewModel = MainViewModel(repo)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_weather_date(){
        viewModel.processDataIntoInfo(getMockData())

        Assert.assertEquals(viewModel.weatherLiveData.value?.size, 6 )
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(0)?.date, "Mon 12:00 AM")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(1)?.date, "Tue 12:00 AM")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(2)?.date, "Wed 12:00 AM")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(3)?.date, "Thu 12:00 AM")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(4)?.date, "Fri 12:00 AM")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(5)?.date, "Sat 12:00 AM")

    }

    @Test
    fun test_weather_description(){
        viewModel.processDataIntoInfo(getMockData())

        Assert.assertEquals(viewModel.weatherLiveData.value?.size, 6 )
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(0)?.weatherDescription, "very cloudy")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(1)?.weatherDescription, "sunny sunny")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(2)?.weatherDescription, "rains rains")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(3)?.weatherDescription, "clear sky clear sky")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(4)?.weatherDescription, "cloud cloudy")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(5)?.weatherDescription, "storm storm")

    }

    @Test
    fun test_weather_when_api_fails(){
        viewModel.listener?.onFailure("Error")

        Assert.assertEquals(viewModel.weatherLiveData.value?.size, 0 )
    }


    @Test
    fun test_weather_min_temp(){
        viewModel.processDataIntoInfo(getMockData())

        Assert.assertEquals(viewModel.weatherLiveData.value?.size, 6 )
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(0)?.minTemp, "6.85C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(1)?.minTemp, "-93.15C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(2)?.minTemp, "6.85C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(3)?.minTemp, "-193.15C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(4)?.minTemp, "-203.15C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(5)?.minTemp, "-213.15C")

    }


    @Test
    fun test_weather_success(){
        viewModel.listener?.onSuccess(getMockData())

        Assert.assertEquals(viewModel.weatherLiveData.value?.size, 6 )
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(0)?.maxTemp, "66.85C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(1)?.maxTemp, "166.85C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(2)?.maxTemp, "266.85C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(3)?.maxTemp, "366.85C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(4)?.maxTemp, "466.85C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(5)?.maxTemp, "566.85C")

    }

    @Test
    fun test_weather_empty_weather_info(){
        viewModel.listener?.onSuccess(getMockDataWithEmptyWeatherInfo())

        Assert.assertEquals(viewModel.weatherLiveData.value?.size, 6 )
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(0)?.maxTemp, "66.85C")
        Assert.assertEquals(viewModel.weatherLiveData.value?.get(0)?.weatherDescription, "")
    }

    @Test
    fun test_weather_when_error(){
        viewModel.processDataIntoInfo(emptyList())

        Assert.assertEquals(viewModel.weatherLiveData.value?.size, 0 )

    }

    private fun getMockDataWithEmptyWeatherInfo(): List<WeatherResponse>{
        val response = WeatherResponse(Main(300.0, 280.0, 340.0), arrayListOf(), "2019-08-12 12:00:00")
        val response1 = WeatherResponse(Main(250.0, 180.0, 440.0), arrayListOf(), "2019-08-13 12:00:00")
        val response2 = WeatherResponse(Main(200.0, 280.0, 540.0), arrayListOf(), "2019-08-14 12:00:00")
        val response3 = WeatherResponse(Main(150.0, 80.0, 640.0), arrayListOf(), "2019-08-15 12:00:00")
        val response4 = WeatherResponse(Main(100.0, 70.0, 740.0), arrayListOf(), "2019-08-16 12:00:00")
        val response5 = WeatherResponse(Main(50.0, 60.0, 840.0), arrayListOf(), "2019-08-17 12:00:00")
        return  arrayListOf(response, response1, response2, response3, response4, response5)
    }

    private fun getMockData(): List<WeatherResponse>{
        val response = WeatherResponse(Main(300.0, 280.0, 340.0), arrayListOf(WeatherInfo(12, "cloud", "very cloudy")), "2019-08-12 12:00:00")
        val response1 = WeatherResponse(Main(250.0, 180.0, 440.0), arrayListOf(WeatherInfo(13, "sunny", "sunny sunny")), "2019-08-13 12:00:00")
        val response2 = WeatherResponse(Main(200.0, 280.0, 540.0), arrayListOf(WeatherInfo(14, "rains", "rains rains")), "2019-08-14 12:00:00")
        val response3 = WeatherResponse(Main(150.0, 80.0, 640.0), arrayListOf(WeatherInfo(15, "clear sky", "clear sky clear sky")), "2019-08-15 12:00:00")
        val response4 = WeatherResponse(Main(100.0, 70.0, 740.0), arrayListOf(WeatherInfo(16, "cloud", "cloud cloudy")), "2019-08-16 12:00:00")
        val response5 = WeatherResponse(Main(50.0, 60.0, 840.0), arrayListOf(WeatherInfo(17, "storm", "storm storm")), "2019-08-17 12:00:00")
        return  arrayListOf(response, response1, response2, response3, response4, response5)
    }
}