package com.sample.openweather.data.repository.json

data class JsonWeather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
