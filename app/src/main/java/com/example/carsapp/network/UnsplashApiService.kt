package com.example.carsapp.network

import com.example.carsapp.data.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.unsplash.com/

interface UnsplashApiService {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int = 1,
        @Query("client_id") accessKey: String = "eVE2DG14l1Bor6f2eltfAPCIrMDrQUtjeP5RTJR9uiA"
    ): UnsplashResponse
}