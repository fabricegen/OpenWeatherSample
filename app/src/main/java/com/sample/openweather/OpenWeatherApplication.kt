package com.sample.openweather

import android.app.Application
import com.sample.openweather.di.ApplicationComponent
import com.sample.openweather.di.ApplicationModule
import com.sample.openweather.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class OpenWeatherApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    protected open fun initDaggerComponent() {
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}
