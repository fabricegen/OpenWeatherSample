package com.sample.openweather.di

import com.sample.openweather.OpenWeatherApplication
import com.sample.openweather.common.DefaultDispatcherProvider
import com.sample.openweather.common.DispatcherProvider
import com.sample.openweather.data.di.DataModule
import com.sample.openweather.ui.di.UiModule
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        UiModule::class,
        DataModule::class
    ]
)
class ApplicationModule(private val application: OpenWeatherApplication) {

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider() as DispatcherProvider
    }
}
