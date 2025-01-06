package com.example.carsapp.data.remoteDataSource


interface UnsplashRemoteDataSource{

    suspend fun getCarPhotoUrl(carModel : String): String?

}