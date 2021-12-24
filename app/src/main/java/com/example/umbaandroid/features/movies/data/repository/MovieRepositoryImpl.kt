package com.example.umbaandroid.features.movies.data.repository

import com.example.umbaandroid.features.movies.data.local.MovieDatabase
import com.example.umbaandroid.features.movies.data.local.mapper.latest_movies.LatestMovieEntityMapper
import com.example.umbaandroid.features.movies.data.local.mapper.popular_movie.PopularMovieEntityMapper
import com.example.umbaandroid.features.movies.data.local.mapper.upcoming_movies.UpcomingMoviesEntityMapper
import com.example.umbaandroid.features.movies.data.remote.MovieApiService
import com.example.umbaandroid.features.movies.data.remote.mapper.LatestMovieDtoMapper
import com.example.umbaandroid.features.movies.data.remote.mapper.PopularMovieDtoMapper
import com.example.umbaandroid.features.movies.data.remote.mapper.UpcomingMovieDtoMapper
import com.example.umbaandroid.features.movies.domain.model.Movie
import com.example.umbaandroid.features.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val movieDatabase: MovieDatabase,
    private val popularMovieEntityMapper: PopularMovieEntityMapper = PopularMovieEntityMapper(),
    private val latestMovieEntityMapper: LatestMovieEntityMapper = LatestMovieEntityMapper(),
    private val upcomingMovieEntityMapper: UpcomingMoviesEntityMapper = UpcomingMoviesEntityMapper(),
    private val popularMovieDtoMapper: PopularMovieDtoMapper = PopularMovieDtoMapper(),
    private val upcomingMovieDtoMapper: UpcomingMovieDtoMapper = UpcomingMovieDtoMapper(),
    private val latestMovieDtoMapper: LatestMovieDtoMapper = LatestMovieDtoMapper()
) : MovieRepository {

    private val popularMoviesDao by lazy { movieDatabase.popularMoviesDao }
    private val upcomingMoviesDao by lazy { movieDatabase.upcomingMoviesDao }
    private val latestMoviesDao by lazy { movieDatabase.latestMoviesDao }


    override suspend fun findPopularMovieById(id: Int): Movie? {
        val popularMovieEntity = popularMoviesDao.findMovieById(id)
        return popularMovieEntity?.let { popularMovieEntityMapper.mapToModel(it) }
    }

    override suspend fun findUpcomingMovieById(id: Int): Movie? {
        val upcomingMovieEntity = upcomingMoviesDao.findMovieById(id)
        return upcomingMovieEntity?.let { upcomingMovieEntityMapper.mapToModel(it) }
    }

    override suspend fun findLatestMovieById(id: Int): Movie? {
        val latestMovie = latestMoviesDao.findMovieById(id)
        return latestMovie?.let { latestMovieEntityMapper.mapToModel(it) }
    }

    override fun fetchPopularMovies(apiKey: String): Flow<List<Movie>> {
        return flow {
            val cachedMovieEntities = popularMoviesDao.getPopularMovies()
            emit(popularMovieEntityMapper.mapToModelList(cachedMovieEntities))
            val moviesResponse = movieApiService.getPopularMovies(apiKey = apiKey)
            val popularMovies = popularMovieDtoMapper.mapModelList(moviesResponse.movies)
            emit(popularMovieEntityMapper.mapToModelList(popularMovies))
            popularMoviesDao.insertAndDeleteInTransaction(
                oldEntity = cachedMovieEntities,
                newEntity = popularMovies
            )
        }
    }

    override fun fetchUpcomingMovies(apiKey: String): Flow<List<Movie>> {
        return flow {
            val movieEntities = upcomingMoviesDao.getUpcomingMovies()
            emit(upcomingMovieEntityMapper.mapToModelList(movieEntities))
            val movieResponse = movieApiService.getUpcomingMovies(apiKey = apiKey)
            val upcomingMovies = upcomingMovieDtoMapper.mapModelList(movieResponse.movies)
            emit(upcomingMovieEntityMapper.mapToModelList(upcomingMovies))
            upcomingMoviesDao.insertAndDeleteInTransaction(
                oldEntity = movieEntities,
                newEntity = upcomingMovies
            )
        }
    }

    override fun fetchLatestMovies(apiKey: String): Flow<List<Movie>> {
        return flow {
            val cachedLatestMovie = latestMoviesDao.getLatestMovies()
            emit(latestMovieEntityMapper.mapToModelList(cachedLatestMovie))
            val movieDto = movieApiService.getLatestMovie(apiKey = apiKey)
            val latestMovies = latestMovieDtoMapper.mapFromModel(movieDto)
            emit(latestMovieEntityMapper.mapToModelList(listOf(latestMovies)))
            latestMoviesDao.insertAndDeleteInTransaction(
                oldEntity = cachedLatestMovie,
                newEntity = listOf(latestMovies)
            )
        }
    }

}