package com.example.jokes.datalayer

interface JokesRepository {
    suspend fun getJokeDetails(): List<Joke>
}


class DefaultJokesRepository(private val jokesApiService: JokesApiService) : JokesRepository {

    override suspend fun getJokeDetails(): List<Joke> {
        return jokesApiService.getJoke()
    }

}