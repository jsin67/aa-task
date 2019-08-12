package com.example.androidtest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.di.Injectable
import com.example.androidtest.models.WeatherResponse
import com.example.androidtest.ui.adapter.WeatherListAdapter
import com.example.androidtest.utils.verifyAvailableNetwork
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable, OnWeatherTappedListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private val adapter = WeatherListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)
        setupView()


        viewModel.weatherLiveData.observe(this, Observer { weatherInfoList ->
            if (weatherInfoList != null) {
                adapter.setWeatherData(weatherInfoList)
            }else {
                Toast.makeText(this, getString(R.string.data_error), Toast.LENGTH_LONG).show()
            }
            dismissProgressing()
        })

        if(verifyAvailableNetwork(this)) {
            showProgressing()
            viewModel.getWeatherData("london,uk")
        }else {
            Toast.makeText(this, getString(R.string.internet_warning), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Sets up views
     */
    private fun setupView() {
        recycler_view.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(it.context, RecyclerView.VERTICAL))
        }
    }

    /**
     * Calls view model data for the update.
     */
    private fun fetchData() {
        if (viewModel.weatherLiveData.value.isNullOrEmpty()) {
            showProgressing()
            viewModel.getWeatherData("london,uk")
        }
    }

    /**
     * Show progress
     */
    private fun showProgressing() {
        LoadingDialogFragment().show(supportFragmentManager, LoadingDialogFragment.TAG)
    }

    /**
     * Dismiss progress
     */
    private fun dismissProgressing() {
        supportFragmentManager.findFragmentByTag(LoadingDialogFragment.TAG)
            ?.takeIf { it is DialogFragment }
            ?.run {
                (this as DialogFragment).dismiss()
            }
    }

    /**
     * Callback when weatherResponse is selected
     * @param weatherResponse: Selected Item
     */
    override fun onItemTapped(weatherResponse: WeatherResponse) {

    }
}
