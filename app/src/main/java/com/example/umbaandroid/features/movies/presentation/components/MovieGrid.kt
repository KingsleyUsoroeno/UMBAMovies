package com.example.umbaandroid.features.movies.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.umbaandroid.features.movies.domain.model.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieGrid(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    movies: List<Movie> = listOf(),
    onItemClick: (id: Int) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = paddingValues,
        modifier = modifier
    ) {
        items(movies.size) {
            MovieCard(
                modifier = Modifier.padding(8.dp),
                imageUrl = movies[it].imageUrl,
                movieName = movies[it].title,
                movieId = movies[it].id,
                onClick = onItemClick
            )
        }
    }
}

@Preview
@Composable
fun MovieListScreenPreview() {
    MovieGrid(paddingValues = PaddingValues(8.dp), movies = listOf()) {}
}