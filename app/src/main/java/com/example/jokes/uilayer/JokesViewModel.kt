package com.example.jokes.uilayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jokes.JokesApplication
import com.example.jokes.datalayer.DefaultJokesRepository
import com.example.jokes.datalayer.JokesRepository
import com.example.jokes.datalayer.JokesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class JokesViewModel(private val jokesRepository: JokesRepository) : ViewModel() {

    private var _jokesState = MutableStateFlow<JokesState>(JokesState.Loading)
    val jokesState: StateFlow<JokesState> = _jokesState.asStateFlow()

    init {
        getJokes()
    }


    fun getJokes() {
        viewModelScope.launch {
            _jokesState.value = JokesState.Loading
            try {
                val myJokes = jokesRepository.getJokeDetails()
                _jokesState.value = JokesState.Success(myJokes)
            } catch (e: IOException) {
                _jokesState.value = JokesState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JokesApplication)
                val jokesRepository = application.container.jokesRepository
                JokesViewModel(jokesRepository = jokesRepository)
            }
        }
    }

}