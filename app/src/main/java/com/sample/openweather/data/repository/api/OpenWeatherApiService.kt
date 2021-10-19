package com.sample.openweather.data.repository.api

import com.sample.openweather.data.repository.json.JsonGetWeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {

    @GET("/data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("appid") appId: String,
        @Query("q") location: String
    ): JsonGetWeatherForecast
}
