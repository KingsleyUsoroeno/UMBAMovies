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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.umbaandroid.ui.theme.Shapes
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    movieName: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .size(width = 160.dp, height = 210.dp)
            .clickable(onClick = onClick),
        shape = Shapes.small,
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column {
            GlideImage(
                imageModel = imageUrl,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 250),
                // shows a placeholder ImageBitmap when loading.
                //placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
                // shows an error ImageBitmap when the request failed.
                //error = ImageBitmap.imageResource(R.drawable.error),
                modifier = Modifier
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
                    Text(text = "image request failed.")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = movieName,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    //.padding(bottom = 5.dp)
                    .padding(horizontal = 2.dp),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {

}