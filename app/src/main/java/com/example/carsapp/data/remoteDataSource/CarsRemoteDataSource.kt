package com.example.carsapp.data.remoteDataSource

import com.example.carsapp.data.model.CarModelDto


interface CarsRemoteDataSource{

    suspend fun getCarList(): List<CarModelDto>?

}