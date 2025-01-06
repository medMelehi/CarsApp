package com.example.carsapp.di

import com.example.carsapp.data.remoteDataSource.CarsRemoteDataSource
import com.example.carsapp.data.remoteDataSource.CarsRemoteDataSourceImpl
import com.example.carsapp.data.remoteDataSource.UnsplashRemoteDataSource
import com.example.carsapp.data.remoteDataSource.UnsplashRemoteDataSourceImpl
import com.example.carsapp.data.repository.CarRepository
import com.example.carsapp.data.repository.CarRepositoryImpl
import com.example.carsapp.data.repository.UnsplashRepository
import com.example.carsapp.data.repository.UnsplashRepositoryImpl
import com.example.carsapp.network.CarApiService
import com.example.carsapp.network.UnsplashApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun provideCarRemoteDataSource(carApiService: CarApiService): CarsRemoteDataSource =
        CarsRemoteDataSourceImpl(carApiService)


    @Provides
    fun provideCarRepository(carsRemoteDataSource: CarsRemoteDataSource): CarRepository =
        CarRepositoryImpl(carsRemoteDataSource)


    @Provides
    fun provideUnsplashRemoteDataSource(unsplashApiService: UnsplashApiService): UnsplashRemoteDataSource =
        UnsplashRemoteDataSourceImpl(unsplashApiService)

    @Provides
    fun provideUnsplashRepository(unsplashRemoteDataSource: UnsplashRemoteDataSource): UnsplashRepository =
        UnsplashRepositoryImpl(unsplashRemoteDataSource)



}


