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

    @Transaction
    suspend fun insertAndDeleteInTransaction(
        oldEntity: PopularMovieEntity,
        newEntity: PopularMovieEntity
    ) {
        delete(entity = oldEntity)
        insert(entity = newEntity)
    }

    @Transaction
    suspend fun insertAndDeleteInTransaction(
        oldEntity: List<PopularMovieEntity>,
        newEntity: List<PopularMovieEntity>
    ) {
        delete(entities = oldEntity)
        insert(entities = newEntity)
    }
}