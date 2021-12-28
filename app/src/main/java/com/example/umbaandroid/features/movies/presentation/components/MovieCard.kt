package com.example.umbaandroid.features.movies.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.umbaandroid.R
import com.example.umbaandroid.ui.theme.Shapes
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    movieName: String,
    movieId: Int,
    onClick: (id: Int) -> Unit
) {
    Surface(
        modifier = modifier
            .size(width = 160.dp, height = 210.dp)
            .clickable { onClick(movieId) },
        shape = Shapes.small,
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column {

            MovieImage(imageUrl = imageUrl)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = movieName,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(horizontal = 4.dp),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun MovieImage(modifier: Modifier = Modifier, imageUrl: String) {
    GlideImage(
        imageModel = imageUrl,
        contentScale = ContentScale.Crop,
        // shows an image with a circular revealed animation.
        circularReveal = CircularReveal(duration = 250),
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        // will show a progress indicator while the image is
        // being loaded from the network
        loading = {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val indicator = createRef()
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(indicator) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        },
        failure = {
            GlideImage(
                imageModel = painterResource(id = R.drawable.img_broken_image),
                circularReveal = CircularReveal(duration = 250),
                contentDescription = "broken image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp),
            )
        },
    )
}