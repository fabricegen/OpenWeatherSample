package com.sample.openweather.data.repository.json

data class JsonGetWeatherForecast(
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<JsonWeatherData>,
    val city: JsonCity
)
