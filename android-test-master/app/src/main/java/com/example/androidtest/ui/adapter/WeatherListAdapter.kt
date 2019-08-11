package com.example.androidtest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.models.WeatherInformationUIModel
import com.example.androidtest.ui.OnWeatherTappedListener

class WeatherListAdapter(listener: OnWeatherTappedListener) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, tempFormat: String) : RecyclerView.ViewHolder(itemView) {
        val txtDate: TextView = itemView.findViewById(R.id.tv_date)
        val txtDescription: TextView = itemView.findViewById(R.id.tv_description)
        val txtTemp: TextView = itemView.findViewById(R.id.tv_temp)
        val format = tempFormat;
    }

    private val weatherInfoList = mutableListOf<WeatherInformationUIModel>()

    override fun getItemCount(): Int = weatherInfoList.size

    fun setWeatherData(list: List<WeatherInformationUIModel>?) {
        list?.let {
            weatherInfoList.clear()
            weatherInfoList.addAll(list)
            notifyDataSetChanged()
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiModel: WeatherInformationUIModel = weatherInfoList[position]
        holder.txtDate.text = uiModel.date
        holder.txtDescription.text = uiModel.weatherDescription
        holder.txtTemp.text = String.format(holder.format, uiModel.minTemp, uiModel.maxTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v, parent.context.getString(R.string.min_max_temp))
    }
}