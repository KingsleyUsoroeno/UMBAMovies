package com.example.umbaandroid.features.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.umbaandroid.features.movies.data.local.dao.base.BaseDao
import com.example.umbaandroid.features.movies.data.local.entity.LatestMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LatestMoviesDao : BaseDao<LatestMovieEntity> {

    @Query("SELECT * FROM LatestMovies")
    fun observeLatestMovies(): Flow<List<LatestMovieEntity>>

    @Query("SELECT * FROM LatestMovies")
    fun getLatestMovies(): List<LatestMovieEntity>

    @Query("SELECT * FROM LatestMovies WHERE id = :id LIMIT 1")
    suspend fun findMovieById(id: Int): LatestMovieEntity?

    @Query("DELETE FROM LatestMovies")
    suspend fun clearTable()

    @Transaction
    suspend fun insertAndDeleteInTransaction(
        newEntity: List<LatestMovieEntity>
    ) {
        clearTable()
        insert(entities = newEntity)
    }
}