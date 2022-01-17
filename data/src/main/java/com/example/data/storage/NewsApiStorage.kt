package com.example.data.storage

import com.example.data.storage.models.NewsEntity

interface NewsApiStorage {
    suspend fun getAll(): List<NewsEntity>
}