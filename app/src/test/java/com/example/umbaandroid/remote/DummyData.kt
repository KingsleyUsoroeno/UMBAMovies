package com.example.umbaandroid.remote

import com.example.umbaandroid.features.movies.data.remote.model.MovieDto

internal object DummyData {

    val movieDto = MovieDto(
        id = 4,
        adult = false,
        backdropPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        originalTitle = "english",
        overview = "A boy in the mysterious bath top",
        posterPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        releaseDate = "23 January 2016",
        title = "Nathaniel clean",
        voteAverage = 4.5
    )
}