package com.example.carsapp.network

import com.example.carsapp.data.model.CarApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

// https://www.carqueryapi.com/

interface CarApiService {
    @GET("api/0.3/")
    suspend fun getCarModels(
        @Query("cmd") cmd: String = "getModels",
        @Query("make") make: String = "ford"
    ): CarApiResponse
}