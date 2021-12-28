package com.example.umbaandroid.remote.utils

import com.example.umbaandroid.features.movies.data.remote.MovieApiService
import com.google.common.io.Resources
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.URL

private val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder().build()

@Suppress("UnstableApiUsage")
internal fun getJson(path: String): String {
    val uri: URL = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}

internal fun makeTestApiService(mockWebServer: MockWebServer): MovieApiService = Retrofit.Builder()
    .baseUrl(mockWebServer.url("/"))
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .build()
    .create(MovieApiService::class.java)
