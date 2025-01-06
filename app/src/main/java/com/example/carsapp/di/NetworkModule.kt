package com.example.carsapp.di

import com.example.carsapp.network.CarApiService
import com.example.carsapp.network.UnsplashApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val unsplashBaseUrl = "https://api.unsplash.com/"
    private val carApiBaseUrl = "https://www.carqueryapi.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(unsplashBaseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideUnsplashApiService(retrofit: Retrofit): UnsplashApiService {
        return retrofit.newBuilder()
            .baseUrl(unsplashBaseUrl)
            .build()
            .create(UnsplashApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCarApiService(retrofit: Retrofit): CarApiService {
        return retrofit.newBuilder()
            .baseUrl(carApiBaseUrl)
            .build()
            .create(CarApiService::class.java)
    }
}
