package com.example.carsapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.carsapp.MainCoroutineRule
import com.example.carsapp.data.repository.CarRepository
import com.example.carsapp.data.repository.UnsplashRepository
import com.example.carsapp.domain.GetFordCarsUseCase
import com.example.carsapp.domain.model.Car
import com.example.carsapp.ui.CarsListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class CarsListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val getFordCarsUseCase : GetFordCarsUseCase = mockk(relaxed = true)

    private lateinit var viewModel: CarsListViewModel

    @Before
    fun setup() {
        viewModel = CarsListViewModel(
            getFordCarsUseCase = getFordCarsUseCase
        )
    }

    @Test
    fun `fetch cars success`() =
        runTest {
            val carsList = listOf(
                Car("Ford", "Fiesta", "url"),
                Car("Ford", "Focus", "url"),
                Car("Ford", "Kogga", "url")
            )
            coEvery { getFordCarsUseCase.execute() } returns carsList
            viewModel.getFordCars()
            advanceUntilIdle()
            val expectedUiState = CarsListViewModel.CarUiState(carList = carsList, isLoading = false, isError = false)
            assertEquals(
                expectedUiState,
                viewModel.uiState.value,
            )
        }


}