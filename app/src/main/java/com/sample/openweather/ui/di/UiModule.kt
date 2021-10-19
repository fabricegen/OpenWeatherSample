package com.sample.openweather.ui.di

import androidx.lifecycle.ViewModel
import com.sample.openweather.common.ViewModelKey
import com.sample.openweather.ui.GetWeatherActivity
import com.sample.openweather.ui.GetWeatherFragment
import com.sample.openweather.ui.GetWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    internal abstract fun contributeGetWeatherActivity(): GetWeatherActivity

    @ContributesAndroidInjector
    internal abstract fun contributesGetWeatherFragment(): GetWeatherFragment

    @Binds
    @IntoMap
    @ViewModelKey(GetWeatherViewModel::class)
    internal abstract fun bindGetWeatherViewModel(viewModel: GetWeatherViewModel): ViewModel
}
