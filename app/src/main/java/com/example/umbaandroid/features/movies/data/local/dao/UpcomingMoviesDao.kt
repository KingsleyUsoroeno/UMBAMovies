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

    @Transaction
    suspend fun insertAndDeleteInTransaction(
        oldEntity: UpcomingMovieEntity,
        newEntity: UpcomingMovieEntity
    ) {
        delete(entity = oldEntity)
        insert(entity = newEntity)
    }

    @Transaction
    suspend fun insertAndDeleteInTransaction(
        oldEntity: List<UpcomingMovieEntity>,
        newEntity: List<UpcomingMovieEntity>
    ) {
        delete(entities = oldEntity)
        insert(entities = newEntity)
    }
}