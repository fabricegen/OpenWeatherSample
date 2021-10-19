package com.sample.openweather.data.repository.json

data class JsonWeatherData(
    val dt: Long,
    val weather: List<JsonWeather>,
    val main: JsonMain,
    val dtTxt: String? = null
)
