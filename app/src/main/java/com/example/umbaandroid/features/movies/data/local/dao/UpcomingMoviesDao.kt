package com.example.umbaandroid.features.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.umbaandroid.features.movies.data.local.dao.base.BaseDao
import com.example.umbaandroid.features.movies.data.local.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingMoviesDao : BaseDao<UpcomingMovieEntity> {

    @Query("SELECT * FROM UpcomingMovies")
    fun observeUpcomingMovies(): Flow<List<UpcomingMovieEntity>>

    @Query("SELECT * FROM UpcomingMovies")
    fun getUpcomingMovies(): List<UpcomingMovieEntity>

    @Query("SELECT * FROM UpcomingMovies WHERE id = :id LIMIT 1")
    suspend fun findMovieById(id: Int): UpcomingMovieEntity?

    @Query("DELETE FROM UpcomingMovies")
    suspend fun clearTable()

    @Transaction
    suspend fun insertAndDeleteInTransaction(
        newEntity: List<UpcomingMovieEntity>
    ) {
        clearTable()
        insert(entities = newEntity)
    }
}