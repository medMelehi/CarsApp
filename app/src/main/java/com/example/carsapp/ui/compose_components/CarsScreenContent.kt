package com.example.carsapp.ui.compose_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.carsapp.R
import com.example.carsapp.domain.model.Car
import com.example.carsapp.ui.CarsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarScreenContent(uiState: CarsListViewModel.CarUiState, onRefresh: () -> Unit) {
    val pullRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = uiState.isLoading,
        onRefresh = onRefresh,
        state = pullRefreshState
    ) {
        CarList(uiState)
    }
}


@Composable
fun CarList(carsUiState: CarsListViewModel.CarUiState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (carsUiState.isError) {
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)) {
                    Text(
                        text = "Failed to load data!!",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        } else {
            items(carsUiState.carList) { car ->
                CarItem(car = car)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CarItem(car: Car) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            GlideImage(
                model = car.imageUrl,
                contentDescription = "Car Image",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(70.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            ) {
                it.placeholder(R.drawable.loading_icon)
                    .error(R.drawable.error_warning_line_icon)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(
                    text = car.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = car.model,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun ScreenContentLoadingPreview() {
    CarScreenContent(CarsListViewModel.CarUiState(isLoading = true), onRefresh = {})
}

@Preview
@Composable
fun ScreenContentErrorPreview() {
    CarScreenContent(CarsListViewModel.CarUiState(isError = true), onRefresh = {})
}

@Preview
@Composable
fun ScreenContentCarListPreview() {
    CarScreenContent(
        CarsListViewModel.CarUiState(
            carList = listOf(
                Car("Ford", "C 12", "url"),
                Car("Ford", "C 12", "url"),
                Car("Ford", "C 12", "url")
            )
        ),
        onRefresh = {}
    )
}