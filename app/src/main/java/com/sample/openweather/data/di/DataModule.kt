package com.sample.openweather.data.di

import com.sample.openweather.data.repository.WeatherRepository
import com.sample.openweather.data.repository.WeatherRepositoryImpl
import com.sample.openweather.data.repository.di.NetworkModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
interface DataModule {

    @Binds
    @Singleton
    fun provideWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository
}
