package com.example.jokes.datalayer

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer {
    val jokesRepository: JokesRepository
}


class DefaultAppContainer : AppContainer {

    private val baseURL = "https://official-joke-api.appspot.com/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: JokesApiService by lazy {
        retrofit.create(JokesApiService::class.java)
    }

    override val jokesRepository: JokesRepository
        get() = DefaultJokesRepository(retrofitService)

}