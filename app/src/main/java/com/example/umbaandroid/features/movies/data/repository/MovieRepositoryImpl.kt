package com.example.umbaandroid.features.movies.data.repository

import com.example.umbaandroid.core.util.Resource
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

    override fun fetchPopularMovies(apiKey: String): Flow<Resource<List<Movie>>> {
        return flowResource(
            getCachedMovie = {
                val cacheMovieEntity = popularMoviesDao.getPopularMovies()
                popularMovieEntityMapper.mapToModelList(cacheMovieEntity)
            },
            getMovieDto = {
                val movieResponse = movieApiService.getPopularMovies(apiKey = apiKey)
                val movieDto = popularMovieDtoMapper.mapModelList(movieResponse.movies)
                popularMovieEntityMapper.mapToModelList(movieDto)
            },
            insertMovie = {
                popularMoviesDao.insertAndDeleteInTransaction(
                    popularMovieEntityMapper.mapToEntityList(
                        it
                    )
                )
            }
        )
    }

    override fun fetchUpcomingMovies(apiKey: String): Flow<Resource<List<Movie>>> {
        return flowResource(
            getCachedMovie = {
                val cacheMovieEntity = upcomingMoviesDao.getUpcomingMovies()
                upcomingMovieEntityMapper.mapToModelList(cacheMovieEntity)
            },
            getMovieDto = {
                val movieResponse = movieApiService.getUpcomingMovies(apiKey = apiKey)
                val movieDto = upcomingMovieDtoMapper.mapModelList(movieResponse.movies)
                upcomingMovieEntityMapper.mapToModelList(movieDto)
            },
            insertMovie = {
                upcomingMoviesDao.insertAndDeleteInTransaction(
                    upcomingMovieEntityMapper.mapToEntityList(
                        it
                    )
                )
            }
        )
    }

    override fun fetchLatestMovies(apiKey: String): Flow<Resource<List<Movie>>> {
        return flowResource(
            getCachedMovie = {
                val cachedLatestMovies = latestMoviesDao.getLatestMovies()
                latestMovieEntityMapper.mapToModelList(cachedLatestMovies)
            },
            getMovieDto = {
                val movieResponse = movieApiService.getLatestMovie(apiKey = apiKey)
                val movieDto = latestMovieDtoMapper.mapFromModel(movieResponse)
                latestMovieEntityMapper.mapToModelList(listOf(movieDto))
            },
            insertMovie = {
                latestMoviesDao.insertAndDeleteInTransaction(
                    latestMovieEntityMapper.mapToEntityList(
                        it
                    )
                )
            }
        )
    }

    private fun flowResource(
        getCachedMovie: () -> List<Movie>,
        getMovieDto: suspend () -> List<Movie>,
        insertMovie: suspend (movies: List<Movie>) -> Unit
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading<List<Movie>>())

            // Get cached items
            val cachedMovieEntity: List<Movie> = getCachedMovie()
            if (cachedMovieEntity.isEmpty()) {
                emit(Resource.Loading<List<Movie>>())
            } else {
                emit(Resource.Success(data = cachedMovieEntity))
            }

            try {
                val movieDto: List<Movie> = getMovieDto()
                emit(Resource.Success(data = movieDto))
                insertMovie(movieDto)
            } catch (e: Exception) {
                if (cachedMovieEntity.isEmpty()) {
                    emit(
                        Resource.Error<List<Movie>>(
                            message = "Oops, something went wrong!",
                            data = null
                        )
                    )
                } else {
                    emit(Resource.Success(cachedMovieEntity))
                }
            }
        }
    }
}