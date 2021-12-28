package com.example.umbaandroid.features.movies.presentation.movie.upcoming

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
fun UpcomingMoviesScreen(
    navController: NavController,
    viewModel: UpcomingMoviesViewModel = hiltViewModel(),
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

        val lifecycleOwner = LocalLifecycleOwner.current
        val uiStateFlow = remember(viewModel.uiState, lifecycleOwner) {
            viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        }

        val uiState by uiStateFlow.collectAsState(initial = MovieInfoState())

        if (uiState.isLoading) {
            LoadingUi()

        } else if (uiState.isLoading.not() &&
            uiState.errorMessage == null && uiState.movies != null
        ) {
            MovieGrid(
                paddingValues = paddingValues,
                movies = uiState.movies ?: emptyList()
            ) { movieId ->
                val destination = Screen.MovieDetailScreen
                    .withArgs(MovieType.UpcomingMovies.name, movieId)
                navController.navigate(destination)
            }

        } else if (uiState.isLoading.not() &&
            uiState.errorMessage != null && uiState.movies == null
        ) {
            ErrorUi(errorMessage = uiState.errorMessage!!) {
                viewModel.fetchUpcomingMovies()
            }
        }
    }
}