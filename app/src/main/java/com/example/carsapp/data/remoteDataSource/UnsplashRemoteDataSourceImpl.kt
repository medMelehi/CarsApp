package com.example.carsapp.data.remoteDataSource

import com.example.carsapp.network.UnsplashApiService
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject


class UnsplashRemoteDataSourceImpl @Inject constructor(private val unsplashApiService: UnsplashApiService) :
    UnsplashRemoteDataSource {

    override suspend fun getCarPhotoUrl(carModel: String): String? {
        return try {
            val query = URLEncoder.encode("Ford $carModel", StandardCharsets.UTF_8.toString())
            unsplashApiService.searchPhotos(query = query).results.first().urls.small
        } catch (_: Exception) {
            null
        }
    }

}