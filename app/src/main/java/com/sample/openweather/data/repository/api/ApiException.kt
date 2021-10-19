package com.sample.openweather.data.repository.api

class ApiException(
    var code: Int,
    message: String?
) : Throwable(message)
