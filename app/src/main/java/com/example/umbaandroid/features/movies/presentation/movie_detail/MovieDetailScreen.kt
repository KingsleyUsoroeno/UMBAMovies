package com.example.umbaandroid.features.movies.presentation.movie_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.umbaandroid.features.movies.presentation.components.MovieImage

@Composable
fun MovieDetailScreen(
    movieType: String,
    id: Int,
    movieViewModel: MovieDetailScreenViewModel = hiltViewModel()
) {

    val movieDetailFlow = movieViewModel.getMovieById(movieType, id)

    val lifecycleOwner = LocalLifecycleOwner.current
    val movieDetailFlowLifecycleAware = remember(movieDetailFlow, lifecycleOwner) {
        movieDetailFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val movie by movieDetailFlowLifecycleAware.collectAsState(initial = null)

    movie?.let { nonNullMovie ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                MovieImage(
                    imageUrl = nonNullMovie.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .padding(top = 20.dp)
                ) {
                    Text(
                        text = nonNullMovie.title.ifEmpty { "N/A" },
                        style = MaterialTheme.typography.h5,
                    )

                    Text(
                        text = nonNullMovie.overview,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(movieType = "Upcoming", id = 90)
}