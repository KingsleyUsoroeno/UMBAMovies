package com.example.umbaandroid.features.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.umbaandroid.features.movies.data.local.dao.base.BaseDao
import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMoviesDao : BaseDao<PopularMovieEntity> {

    @Query("SELECT * FROM PopularMovies")
    fun observePopularMovies(): Flow<List<PopularMovieEntity>>

    @Query("SELECT * FROM PopularMovies")
    fun getPopularMovies(): List<PopularMovieEntity>

    @Query("SELECT * FROM PopularMovies WHERE id = :id LIMIT 1")
    suspend fun findMovieById(id: Int): PopularMovieEntity?

    @Query("DELETE FROM PopularMovies")
    suspend fun clearTable()

    @Transaction
    suspend fun insertAndDeleteInTransaction(
        newEntity: List<PopularMovieEntity>
    ) {
        clearTable()
        insert(entities = newEntity)
    }
}