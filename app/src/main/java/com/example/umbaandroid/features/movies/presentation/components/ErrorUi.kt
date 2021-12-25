package com.example.umbaandroid.features.movies.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.umbaandroid.R


@Composable
fun ErrorUi(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_error),
            contentDescription = "Error image"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = errorMessage)
        Button(
            onClick = onRetry, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 30.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Retry",
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorUiPreview() {
    ErrorUi(errorMessage = "Something Went Wrong :)") {}
}