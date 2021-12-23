package com.example.umbaandroid.features.movies.presentation.movie_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.umbaandroid.features.movies.presentation.components.MovieCard


@Composable
fun MovieListScreen(
    navController: NavController,
    movieViewModel: MovieViewModel = hiltViewModel(),
    movieType: String
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(topBar = {
            TopAppBar {
                Text(
                    text = "$movieType Movies",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }) { paddingValues ->
            MovieGrid(paddingValues = paddingValues)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieGrid(modifier: Modifier = Modifier, paddingValues: PaddingValues) {
    val numbers = (0..10).toList()
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = paddingValues,
        modifier = modifier
    ) {
        items(numbers.size) {
            MovieCard(
                modifier = Modifier.padding(8.dp),
                imageUrl = "", movieName = "Tarzan"
            ) {

            }
        }
    }
}

@Preview
@Composable
fun MovieListScreenPreview() {
    MovieGrid(paddingValues = PaddingValues(8.dp))
}