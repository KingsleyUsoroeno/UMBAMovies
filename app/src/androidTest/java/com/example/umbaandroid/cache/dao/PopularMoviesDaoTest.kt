package com.example.umbaandroid.cache.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.umbaandroid.cache.DummyData
import com.example.umbaandroid.cache.util.Utils.makeMovieDatabase
import com.example.umbaandroid.features.movies.data.local.MovieDatabase
import com.example.umbaandroid.features.movies.data.local.dao.PopularMoviesDao
import com.example.umbaandroid.features.movies.data.local.entity.PopularMovieEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class PopularMoviesDaoTest {

    private lateinit var movieDatabase: MovieDatabase
    private lateinit var popularMoviesDao: PopularMoviesDao

    @Before
    fun initDb() {
        movieDatabase = makeMovieDatabase(
            InstrumentationRegistry.getInstrumentation().targetContext,
        )
        popularMoviesDao = movieDatabase.popularMoviesDao
    }

    @Test
    fun insertSavesDataToDatabase(): Unit = runBlocking {
        val popularMovieEntities = DummyData.popularMovieEntity
        popularMoviesDao.insert(entity = popularMovieEntities)
        val cachedMovies = popularMoviesDao.getPopularMovies()
        assertThat(cachedMovies.size).isEqualTo(1)
        assertThat(cachedMovies.first().title).isEqualTo("Venom: Let There Be Carnage")
        assertThat(cachedMovies.isNotEmpty())
    }

    @Test
    fun getPopularMoviesReturnsAllCachedMovieData(): Unit = runBlocking {
        val cachedMovieEntities: List<PopularMovieEntity> =
            List(size = 4) { DummyData.popularMovieEntity }

        popularMoviesDao.insert(entities = cachedMovieEntities)

        val retrievedMovies = popularMoviesDao.getPopularMovies()
        assertThat(
            retrievedMovies == cachedMovieEntities.sortedWith(
                compareBy(
                    { it.id },
                    { it.id })
            )
        )
    }

    @Test
    fun clearTableClearsData(): Unit = runBlocking {
        val popularMovieEntities = DummyData.popularMovieEntity
        popularMoviesDao.insert(entity = popularMovieEntities)

        popularMoviesDao.clearTable()
        assertThat(popularMoviesDao.getPopularMovies().isEmpty())
    }

    @Test
    fun findMovieByIdReturnsCorrectDataWithValidId(): Unit = runBlocking {
        val movieEntity: PopularMovieEntity = DummyData.popularMovieEntity
        popularMoviesDao.insert(entity = movieEntity)

        val popularMovieEntity = popularMoviesDao.findMovieById(movieEntity.id)

        assertThat(popularMovieEntity).isNotEqualTo(null)
        assertThat(popularMovieEntity?.title).isEqualTo("Venom: Let There Be Carnage")
    }

    @After
    fun closeDb() {
        movieDatabase.close()
    }
}