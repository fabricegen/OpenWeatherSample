package com.sample.openweather.di

import com.sample.openweather.OpenWeatherApplication
import com.sample.openweather.data.repository.di.NetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class
    ]
)

interface ApplicationComponent {
    fun inject(application: OpenWeatherApplication)
}
