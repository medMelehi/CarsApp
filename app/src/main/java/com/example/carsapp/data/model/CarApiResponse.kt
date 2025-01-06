package com.example.carsapp.data.model



data class CarModelDto(
    val model_name: String,
    val model_make_id: String
)

data class CarApiResponse(
    val Models: List<CarModelDto>
)