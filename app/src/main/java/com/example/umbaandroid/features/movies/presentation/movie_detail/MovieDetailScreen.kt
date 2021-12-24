package com.example.umbaandroid.features.movies.presentation.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.umbaandroid.R

@Composable
fun MovieDetailScreen(
    movieType: String,
    movieDetailScreenViewModel: MovieDetailScreenViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_movie_concept),
                contentDescription = "Movie poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Story Line", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                            "when an unknown printer took a galley of type and scrambled it to make a type " +
                            "specimen book. It has survived not only five centuries, but also the leap into "
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(movieType = "Upcoming")
}