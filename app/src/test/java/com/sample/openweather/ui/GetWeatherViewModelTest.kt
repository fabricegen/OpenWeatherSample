package com.sample.openweather.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sample.openweather.CoroutineTestRule
import com.sample.openweather.TestDispatcherProvider
import com.sample.openweather.data.repository.WeatherRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetWeatherViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var uiStateObserver: Observer<GetWeatherViewModel.UiState>

    @Mock
    private lateinit var uiEventObserver: Observer<GetWeatherViewModel.UiEvent>

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    private lateinit var viewModel: GetWeatherViewModel

    @Before
    fun setup() {
        viewModel = GetWeatherViewModel(weatherRepository, TestDispatcherProvider())
        viewModel.uiState.observeForever(uiStateObserver)
        viewModel.uiEvent.observeForever(uiEventObserver)
    }

    @After
    fun onEnd() {
        viewModel.uiState.removeObserver(uiStateObserver)
        viewModel.uiEvent.removeObserver(uiEventObserver)
    }

    @Test
    fun `get weather when result is success`() =
        coroutineTestRule.runBlocking {
            whenever(weatherRepository.getWeather(any())).doReturn(emptyList())

            viewModel.getWeather()

            verify(
                uiStateObserver,
                atLeastOnce()
            ).onChanged(argThat { this is GetWeatherViewModel.UiState.Data })
        }

    @Test
    fun `get weather when result is failed`() =
        coroutineTestRule.runBlocking {
            whenever(weatherRepository.getWeather(any())).doThrow(IllegalStateException("ERROR"))

            viewModel.getWeather()

            verify(
                uiStateObserver,
                never()
            ).onChanged(argThat { this is GetWeatherViewModel.UiState.Data })
            verify(uiEventObserver).onChanged(argThat { this is GetWeatherViewModel.UiEvent.Error })
        }
}
