package com.example.carsapp.data.repository

import com.example.carsapp.data.model.CarModelDto
import com.example.carsapp.data.remoteDataSource.CarsRemoteDataSource
import javax.inject.Inject


class CarRepositoryImpl @Inject constructor(private val carsRemoteDataSource: CarsRemoteDataSource) :
    CarRepository {

    override suspend fun getFordCars(): List<CarModelDto>? {
        return carsRemoteDataSource.getCarList()
    }

}