package com.example.umbaandroid.features.movies.presentation.movie.upcoming

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.umbaandroid.features.movies.presentation.Screen
import com.example.umbaandroid.features.movies.presentation.components.LoadingUi
import com.example.umbaandroid.features.movies.presentation.components.MovieGrid
import com.example.umbaandroid.features.movies.presentation.state.UiState

@Composable
fun UpcomingMoviesScreen(
    navController: NavController,
    viewModel: UpcomingMoviesViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(topBar = {
            TopAppBar {
                Text(
                    text = "Upcoming Movies",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }) { paddingValues ->

            val uiState by viewModel.uiState.collectAsState(initial = UiState.Idle)

            when (uiState) {
                is UiState.Idle -> {
                }

                is UiState.Loading -> {
                    LoadingUi()
                }

                is UiState.Loaded -> {
                    val movies = (uiState as UiState.Loaded).movies
                    MovieGrid(paddingValues = paddingValues, movies = movies) { movieId ->
                        val destination = Screen.MovieDetailScreen
                            .withArgs("Upcoming Movies", movieId)
                        navController.navigate(destination)
                    }
                }

                is UiState.Error -> {
                    println("encountered error is ${(uiState as UiState.Error).errorMessage}")
                }
            }
        }
    }
}