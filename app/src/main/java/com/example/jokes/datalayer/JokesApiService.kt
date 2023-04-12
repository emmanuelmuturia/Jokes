package com.example.jokes.datalayer

import retrofit2.http.GET

interface JokesApiService {

    @GET("random_ten")
    suspend fun getJoke(): List<Joke>

}