package com.example.umbaandroid.features.movies.presentation.movie.latest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import com.example.umbaandroid.features.movies.presentation.MovieType
import com.example.umbaandroid.features.movies.presentation.Screen
import com.example.umbaandroid.features.movies.presentation.components.ErrorUi
import com.example.umbaandroid.features.movies.presentation.components.LoadingUi
import com.example.umbaandroid.features.movies.presentation.components.MovieGrid
import com.example.umbaandroid.features.movies.presentation.state.MovieInfoState


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

            val lifecycleOwner = LocalLifecycleOwner.current
            val uiStateFlow = remember(viewModel.uiState, lifecycleOwner) {
                viewModel.uiState.flowWithLifecycle(
                    lifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                )
            }

            val state by uiStateFlow.collectAsState(initial = MovieInfoState())

            if (state.isLoading) {
                LoadingUi()

            } else if (state.isLoading.not() &&
                state.errorMessage == null && state.movies != null
            ) {
                MovieGrid(
                    paddingValues = paddingValues,
                    movies = state.movies ?: emptyList()
                ) { movieId ->
                    val destination = Screen.MovieDetailScreen
                        .withArgs(MovieType.LatestMovies.name, movieId)
                    navController.navigate(destination)
                }

            } else if (state.isLoading.not() &&
                state.errorMessage != null && state.movies == null
            ) {
                ErrorUi(errorMessage = state.errorMessage!!) {
                    viewModel.fetchLatestMovies()
                }
            }
        }
    }
}