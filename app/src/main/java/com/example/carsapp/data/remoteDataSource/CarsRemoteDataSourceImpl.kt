package com.example.carsapp.data.remoteDataSource

import com.example.carsapp.data.model.CarModelDto
import com.example.carsapp.network.CarApiService
import javax.inject.Inject


class CarsRemoteDataSourceImpl @Inject constructor(private val carApiService: CarApiService) :
    CarsRemoteDataSource {
    override suspend fun getCarList(): List<CarModelDto>? {
        return try {
             carApiService.getCarModels().Models.take(12)
        }catch (_: Exception){
            null
        }
    }

}