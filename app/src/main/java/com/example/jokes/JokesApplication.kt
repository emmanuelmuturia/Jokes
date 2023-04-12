package com.example.jokes

import android.app.Application
import com.example.jokes.datalayer.AppContainer
import com.example.jokes.datalayer.DefaultAppContainer

class JokesApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}