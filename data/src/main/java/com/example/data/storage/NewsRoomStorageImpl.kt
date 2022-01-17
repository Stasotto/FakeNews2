package com.example.data.storage

import com.example.data.storage.models.NewsEntity
import com.example.data.storage.room.NewsDao

class NewsRoomStorageImpl(private val newsDao: NewsDao) : NewsRoomStorage {

    override suspend fun getAll(): List<NewsEntity> {
        return newsDao.getAll()
    }

    override suspend fun save(news: NewsEntity) {
        newsDao.save(news)
    }

    override suspend fun delete(news: NewsEntity) {
        newsDao.delete(news)
    }
}