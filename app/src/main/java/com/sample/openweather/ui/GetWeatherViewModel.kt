package com.sample.openweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.openweather.common.DispatcherProvider
import com.sample.openweather.data.repository.WeatherRepository
import com.sample.openweather.domain.WeatherData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    private val _uiEvent = MutableLiveData<UiEvent>()
    val uiEvent: LiveData<UiEvent>
        get() = _uiEvent

    fun getWeather() {
        viewModelScope.launch {
            // could be delegated to a UseCase, this will reduce code and responsabilities in ViewModel
            val result = withContext(dispatcherProvider.io()) {
                try {
                    val data = repository.getWeather("London")
                    Result.Data(data)
                } catch (t: Throwable) {
                    Result.Error(t.message ?: "")
                }
            }
            when (result) {
                is Result.Data -> _uiState.value = UiState.Data(result.weatherList)
                is Result.Error -> _uiEvent.value = UiEvent.Error("Error happened ${result.msg}")
            }
        }
    }

    sealed class UiState {
        data class Data(val weatherList: List<WeatherData>) : UiState()
    }

    sealed class UiEvent {
        data class Error(val msg: String) : UiEvent()
    }

    sealed class Result {
        data class Data(val weatherList: List<WeatherData>) : UiState()
        data class Error(val msg: String) : UiEvent()
    }

}
