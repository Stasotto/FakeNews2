package com.example.data.repository

import com.example.data.storage.NewsApiStorage
import com.example.data.storage.NewsRoomStorage
import com.example.data.toNewsDomain
import com.example.data.toNewsEntity
import com.example.domain.models.NewsDomain
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
    private val newsApiStorage: NewsApiStorage,
    private val newsRoomStorage: NewsRoomStorage
) : NewsRepository {

    override suspend fun saveInRoomNews(news: NewsDomain) {
        withContext(Dispatchers.IO) {
            newsRoomStorage.save(news.toNewsEntity())
        }
    }

    override suspend fun getAllFromRoomNews(): List<NewsDomain> {
        return withContext(Dispatchers.IO) {
            newsRoomStorage.getAll().map { newsEntity ->
                newsEntity.toNewsDomain()
            }
        }
    }

    override suspend fun deleteFromRoomNews(news: NewsDomain) {
        withContext(Dispatchers.IO) {
            newsRoomStorage.delete(news.toNewsEntity())
        }
    }

    override suspend fun getAllFromNewsApi(): List<NewsDomain> {
        return withContext(Dispatchers.IO) {
            newsApiStorage.getAll().map { newsEntity ->
                newsEntity.toNewsDomain()
            }
        }
    }

}