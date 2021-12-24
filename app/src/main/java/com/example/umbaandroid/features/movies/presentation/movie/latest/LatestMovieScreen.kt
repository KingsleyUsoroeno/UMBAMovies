package com.example.umbaandroid.features.movies.presentation.movie.latest

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
fun LatestMovieScreen(
    navController: NavController,
    viewModel: LatestMovieScreenViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(topBar = {
            TopAppBar {
                Text(
                    text = "Latest Movies",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }) { paddingValues ->

            val uiState by viewModel.uiState.collectAsState()

            when (uiState) {
                is UiState.Idle -> {
                }

                is UiState.Loading -> {
                    LoadingUi()
                }

                is UiState.Loaded -> {
                    val movies = (uiState as UiState.Loaded).movies
                    MovieGrid(paddingValues = paddingValues, movies = movies) { id ->
                        val destination = Screen.MovieDetailScreen
                            .withArgs("Latest Movies", id)
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