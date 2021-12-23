package com.example.umbaandroid.features.movies.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.umbaandroid.R
import com.example.umbaandroid.ui.theme.Shapes

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    movieName: String,
    onClick : () -> Unit
) {
    Surface(
        modifier = modifier
            .size(width = 160.dp, height = 200.dp)
            .clickable(onClick = onClick),
        shape = Shapes.small,
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_movie_concept),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = movieName,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {

}