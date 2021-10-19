package com.sample.openweather.data.repository

import com.sample.openweather.domain.WeatherData

interface WeatherRepository {
    suspend fun getWeather(location: String): List<WeatherData>
}
