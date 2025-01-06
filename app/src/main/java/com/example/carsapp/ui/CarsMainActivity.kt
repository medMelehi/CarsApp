package com.example.carsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carsapp.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CarsMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}