package com.example.memoapp.data

import kotlinx.coroutines.flow.Flow

class DatabaseItemsRepository(private val itemDao: ItemDao) :
    ItemsRepository {
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()
    override fun getItemsStream(id: Int): Flow<Item?> = itemDao.getItems(id)
    override suspend fun insertItem(item: Item) = itemDao.insertItems(item)
    override suspend fun deleteItem(item: Item) = itemDao.deleteItems(item)
    override suspend fun updateItem(item: Item) = itemDao.updateItems(item)
}