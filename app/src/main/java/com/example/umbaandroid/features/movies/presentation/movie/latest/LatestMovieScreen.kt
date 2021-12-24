package com.example.umbaandroid.features.movies.presentation.movie.latest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.umbaandroid.features.movies.presentation.Screen
import com.example.umbaandroid.features.movies.presentation.components.MovieGrid


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
            MovieGrid(paddingValues = paddingValues) {
                navController.navigate(Screen.MovieDetailScreen.withArgs("Latest Movies"))
            }
        }
    }
}