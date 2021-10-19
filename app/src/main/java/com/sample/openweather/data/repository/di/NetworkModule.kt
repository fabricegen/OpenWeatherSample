package com.sample.openweather.data.repository.di

import com.google.gson.GsonBuilder
import com.sample.openweather.data.repository.api.APIConstants
import com.sample.openweather.data.repository.api.ApiExceptionCallAdapterFactory
import com.sample.openweather.data.repository.api.OpenWeatherApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun okHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideOpenWeatherApiService(
        okHttpClient: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): OpenWeatherApiService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClient.addInterceptor(logging)

        retrofitBuilder.addCallAdapterFactory(ApiExceptionCallAdapterFactory(GsonBuilder().create()))

        val retrofit = retrofitBuilder
            .baseUrl(APIConstants.BASE_URL)
            .client(okHttpClient.build())
            .build()

        return retrofit.create(OpenWeatherApiService::class.java)
    }
}
