package com.example.carsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.carsapp.ui.compose_components.CarScreenContent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CarListFragment : Fragment() {

    private val viewModel: CarsListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val uiState by viewModel.uiState.collectAsState()
                MaterialTheme {
                    CarScreenContent(
                        uiState = uiState,
                        onRefresh = ::refreshCarList
                    )
                }
            }
        }

    }

    private fun refreshCarList() {
        viewModel.getFordCars()
    }

}