package com.example.umbaandroid.features.movies.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.umbaandroid.features.movies.data.local.dao.LatestMoviesDao
import com.example.umbaandroid.features.movies.data.local.dao.PopularMoviesDao
import com.example.umbaandroid.features.movies.data.local.dao.UpcomingMoviesDao
import com.example.umbaandroid.features.movies.data.local.entity.LatestMovieEntity
import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import com.example.umbaandroid.features.movies.data.local.entity.UpcomingMovieEntity

@Database(
    entities = [PopularMovieEntity::class, UpcomingMovieEntity::class, LatestMovieEntity::class],
    version = 1,
    exportSchema = false
)

abstract class MovieDatabase : RoomDatabase() {

    abstract val latestMoviesDao: LatestMoviesDao

    abstract val popularMoviesDao: PopularMoviesDao

    abstract val upcomingMoviesDao: UpcomingMoviesDao

    companion object {
        private const val DATABASE_NAME: String = "movies_db"
        fun build(context: Context): MovieDatabase = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}