package com.example.umbaandroid.features.movies.domain.model


data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
) {
    val imageUrl: String get() = "https://image.tmdb.org/t/p/w500/$posterPath"
}