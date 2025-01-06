package com.example.carsapp.domain

import com.example.carsapp.data.repository.CarRepository
import com.example.carsapp.data.repository.UnsplashRepository
import com.example.carsapp.domain.model.Car
import javax.inject.Inject


class GetFordCarsUseCase
@Inject constructor(
    private val carRepository: CarRepository,
    private val unsplashRepository: UnsplashRepository
) {

    suspend fun execute(): List<Car> {
        val fordCars = carRepository.getFordCars()
        val carList : MutableList<Car> = mutableListOf()
        fordCars?.forEach { fordCar ->
            val imageUrl = unsplashRepository.getCarPhoto(fordCar.model_name)
            imageUrl?.let {
                carList.add(Car(name = fordCar.model_make_id, model = fordCar.model_name, imageUrl = imageUrl))
            }
        }
        return carList
    }

}