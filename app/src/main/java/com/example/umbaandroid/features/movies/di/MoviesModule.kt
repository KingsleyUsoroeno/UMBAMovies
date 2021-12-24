package com.example.umbaandroid.features.movies.di

import android.content.Context
import com.example.umbaandroid.BuildConfig
import com.example.umbaandroid.features.movies.data.local.MovieDatabase
import com.example.umbaandroid.features.movies.data.remote.MovieApiService
import com.example.umbaandroid.features.movies.data.remote.interceptor.NoInternetInterceptor
import com.example.umbaandroid.features.movies.data.repository.MovieRepositoryImpl
import com.example.umbaandroid.features.movies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @[Provides Singleton]
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        movieDatabase: MovieDatabase,
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieApiService = movieApiService,
            movieDatabase = movieDatabase
        )
    }

    @[Provides Singleton]
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return MovieDatabase.build(context)
    }

    @[Provides Singleton]
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @[Provides Singleton]
    fun provideRetrofitBuilder(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(NoInternetInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().apply {
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            baseUrl("https://api.themoviedb.org/")
        }.build()
    }
}