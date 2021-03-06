package com.example.umbaandroid.cache.util

import android.content.Context
import androidx.room.Room
import com.example.umbaandroid.features.movies.data.local.MovieDatabase

internal object Utils {

    fun makeMovieDatabase(context: Context):
            MovieDatabase = Room.inMemoryDatabaseBuilder(
        context, MovieDatabase::class.java
    ).build()
}