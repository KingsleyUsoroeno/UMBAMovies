package com.example.umbaandroid.features.movies.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UpcomingMovies")
data class UpcomingMovieEntity(
    @NonNull @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)