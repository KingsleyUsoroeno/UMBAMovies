package com.example.umbaandroid.features.movies.data.remote.model.response

import com.example.umbaandroid.features.movies.data.remote.model.MovieDto
import com.google.gson.annotations.SerializedName

data class MovieDtoResponse(
    @SerializedName("results")
    val movies: List<MovieDto>
)
