package com.sample.openweather.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.openweather.R
import com.sample.openweather.databinding.WeatherDayItemBinding
import com.sample.openweather.domain.WeatherData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Adapter for displaying the list of the weather data
 */
class GetWeatherAdapter @Inject internal constructor(private val context: Context) :
    RecyclerView.Adapter<GetWeatherAdapter.WeatherHolder>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK)

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private val items = mutableListOf<WeatherData>()

    fun setData(data: List<WeatherData>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(WeatherDayItemBinding.inflate(inflater, parent, false))
    }

    private fun getItem(position: Int): WeatherData {
        val positionInList = position % items.size
        return items[positionInList]
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        val weatherData: WeatherData = getItem(position)
        holder.binding.weatherTime.text = dateFormat.format(Date(weatherData.time * 1000))
        holder.binding.weatherTemp.text = context.getString(R.string.temp, weatherData.temp.toString())
        holder.binding.weather.text = context.getString(
            R.string.weather,
            weatherData.description
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    data class WeatherHolder(val binding: WeatherDayItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
