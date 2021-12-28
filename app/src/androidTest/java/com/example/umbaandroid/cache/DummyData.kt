package com.example.umbaandroid.cache

import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import com.example.umbaandroid.features.movies.data.local.entity.UpcomingMovieEntity

object DummyData {
    val popularMovieEntity = PopularMovieEntity(
        adult = false,
        backdropPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        id = 5,
        originalTitle = "Venom: Let There Be Carnage",
        overview = "Hello World :)",
        title = "Venom: Let There Be Carnage",
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        voteAverage = 5.6,
        releaseDate = "2021-12-15"
    )

    val upcomingMovieEntity = UpcomingMovieEntity(
        adult = true,
        backdropPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        id = 5,
        originalTitle = "Venom: Let There Be Carnage",
        overview = "Hello World :)",
        title = "Venom: Let There Be Carnage",
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        voteAverage = 5.6,
        releaseDate = "2021-12-15"
    )
}