package com.example.memoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert
    suspend fun insertItems(item: Item)
    @Update
    suspend fun updateItems(item: Item)
    @Delete
    suspend fun deleteItems(item: Item)
    @Query("SELECT * FROM items WHERE id = :id")
    fun getItems(id: Int): Flow<Item>
    @Query("SELECT * FROM items ORDER BY id DESC")
    fun getAllItems(): Flow<List<Item>>
}