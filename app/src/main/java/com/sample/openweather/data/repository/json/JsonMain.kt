package com.sample.openweather.data.repository.json

data class JsonMain(
    var temp: Double,
    var tempMin: Double,
    var tempMax: Double,
    var pressure: Double,
    var seaLevel: Double,
    var grndLevel: Double,
    var humidity: Int,
    var tempKf: Double
)
