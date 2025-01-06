package com.example.carsapp.data.repository

import com.example.carsapp.data.remoteDataSource.UnsplashRemoteDataSource
import javax.inject.Inject


class UnsplashRepositoryImpl @Inject constructor(private val unsplashRemoteDataSource: UnsplashRemoteDataSource) :
    UnsplashRepository {

    override suspend fun getCarPhoto(carModel: String): String? {
        return unsplashRemoteDataSource.getCarPhotoUrl(carModel)
    }

}