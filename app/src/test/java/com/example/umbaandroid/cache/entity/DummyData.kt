package com.example.umbaandroid.cache.entity

import com.example.umbaandroid.features.movies.data.local.entity.LatestMovieEntity
import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import com.example.umbaandroid.features.movies.data.local.entity.UpcomingMovieEntity
import com.example.umbaandroid.features.movies.domain.model.Movie

internal object DummyData {
    val latestMovieEntity = LatestMovieEntity(
        adult = true,
        backdropPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        id = 5,
        originalTitle = "Spider-Man: No Way Home",
        overview = "Hello World :)",
        title = "Spider-Man: No Way Home",
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        voteAverage = 5.6,
        releaseDate = "2021-12-15"
    )

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
        originalTitle = "Sky fall",
        overview = "On his fifth mission for the MI6 james bond comes across his most deadly nemesis",
        title = "Spider-Man: No Way Home",
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        voteAverage = 5.6,
        releaseDate = "2021-12-15"
    )

    val movie = Movie(
        adult = true,
        backdropPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        id = 5,
        originalTitle = "Sky fall",
        overview = "On his fifth mission for the MI6 james bond comes across his most deadly nemesis",
        title = "Sky fall",
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        voteAverage = 5.6,
        releaseDate = "2021-12-15"
    )
}
