package com.sample.openweather.data.repository

import com.sample.openweather.data.repository.api.APIConstants
import com.sample.openweather.data.repository.api.OpenWeatherApiService
import com.sample.openweather.data.repository.json.JsonGetWeatherForecast
import com.sample.openweather.domain.WeatherData
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherApiService: OpenWeatherApiService) :
    WeatherRepository {

    override suspend fun getWeather(location: String): List<WeatherData> {
        val response = weatherApiService.getWeatherForecast(APIConstants.FORECAST_API_ID, location)
        return mapToWeatherData(response)
    }

    private fun mapToWeatherData(response: JsonGetWeatherForecast): List<WeatherData> {
        val weatherDataList = mutableListOf<WeatherData>()
        response.list.forEach {
            val description = if (it.weather.isNotEmpty()) { it.weather[0].description } else ""
            weatherDataList.add(WeatherData(it.dt, description , it.main.temp))
        }
        return weatherDataList
    }
}
