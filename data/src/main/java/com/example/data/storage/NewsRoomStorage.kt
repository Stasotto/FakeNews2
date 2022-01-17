package com.example.data.storage

import com.example.data.storage.models.NewsEntity

interface NewsRoomStorage {

    suspend fun getAll(): List<NewsEntity>

    suspend fun save(news: NewsEntity)

    suspend fun delete(news: NewsEntity)

}