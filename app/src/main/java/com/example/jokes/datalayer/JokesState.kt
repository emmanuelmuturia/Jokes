package com.example.jokes.datalayer

sealed interface JokesState {

    data class Success(val myJokes: List<Joke>): JokesState
    object Loading: JokesState
    object Error: JokesState

}