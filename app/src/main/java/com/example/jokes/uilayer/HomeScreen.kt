package com.example.jokes.uilayer

import androidx.compose.runtime.Composable
import com.example.jokes.datalayer.JokesState

@Composable
fun HomeScreen(jokesState: JokesState) {
    when(jokesState) {
        is JokesState.Error -> ErrorScreen()
        is JokesState.Loading -> LoadingScreen()
        is JokesState.Success -> JokesScreen(myJokes = jokesState.myJokes)
    }
}