package com.example.umbaandroid.features.movies.presentation.movie.popular

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.umbaandroid.features.movies.presentation.Screen
import com.example.umbaandroid.features.movies.presentation.components.ErrorUi
import com.example.umbaandroid.features.movies.presentation.components.LoadingUi
import com.example.umbaandroid.features.movies.presentation.components.MovieGrid

@Composable
fun PopularMoviesScreen(
    navController: NavController,
    viewModel: PopularMoviesScreenViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TopAppBar {
            Text(
                text = "Popular Movies",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }) { paddingValues ->

        val state by viewModel.uiState.collectAsState()

        if (state.isLoading) {
            LoadingUi()

        } else if (state.isLoading.not() &&
            state.errorMessage == null && state.movies != null
        ) {
            MovieGrid(paddingValues = paddingValues, movies = state.movies!!) { movieId ->
                val destination = Screen.MovieDetailScreen
                    .withArgs("Popular Movies", movieId)
                navController.navigate(destination)
            }

        } else if (state.isLoading.not() &&
            state.errorMessage != null && state.movies == null
        ) {
            ErrorUi(errorMessage = state.errorMessage!!) {
                viewModel.fetchPopularMovies()
            }
        }
    }
}