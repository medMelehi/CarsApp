package com.example.carsapp.data.repository

import com.example.carsapp.data.model.CarModelDto

interface CarRepository {
    suspend fun getFordCars(): List<CarModelDto>?
}