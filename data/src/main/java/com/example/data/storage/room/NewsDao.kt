package com.example.data.storage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.storage.models.NewsEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>

    @Insert
    fun save(news: NewsEntity)

    @Delete
    fun delete(news: NewsEntity)
}