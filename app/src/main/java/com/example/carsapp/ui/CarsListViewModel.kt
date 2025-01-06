package com.example.carsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsapp.domain.GetFordCarsUseCase
import com.example.carsapp.domain.model.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsListViewModel @Inject constructor(
    private val getFordCarsUseCase: GetFordCarsUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(CarUiState())
    val uiState: StateFlow<CarUiState> =
        _uiState


    init {
        getFordCars()
    }


    fun getFordCars() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            val fordCars = getFordCarsUseCase.execute()
            if (fordCars.isEmpty()) {
                updateState { it.copy(isLoading = false, isError = true) }
            } else {
                updateState { it.copy(isLoading = false, isError = false, carList = fordCars) }
            }
        }
    }

    private fun updateState(update: (CarUiState) -> CarUiState) {
        _uiState.value = update(_uiState.value)
    }

    data class CarUiState(
        val carList: List<Car> = listOf(),
        val isLoading: Boolean = false,
        val isError: Boolean = false
    )

}