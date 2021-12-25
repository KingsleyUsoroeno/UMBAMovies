package com.example.umbaandroid.features.movies.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LoadingUi(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
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
}

@Preview(showBackground = true)
@Composable
fun LoadingUiPreview() {
    LoadingUi()
}