package com.example.memoapp.data

import android.content.Context

interface AppContainer {
    val itemsRepository: ItemsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: ItemsRepository by lazy {
        DatabaseItemsRepository(TodoDatabase.getDatabase(context).itemDao())
    }
}