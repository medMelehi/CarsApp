package com.example.carsapp.data.repository

interface UnsplashRepository {
    suspend fun getCarPhoto(carModel: String): String?
}