package com.example.memoapp

import android.app.Application
import com.example.memoapp.data.AppContainer
import com.example.memoapp.data.AppDataContainer

class TodoApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}