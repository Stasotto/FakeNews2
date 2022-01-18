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
    internal val newsApiStorage: NewsApiStorage,
    private val newsRoomStorage: NewsRoomStorage
) : NewsRepository {

    override suspend fun saveToDatabase(news: NewsDomain) {
        withContext(Dispatchers.IO) {
            newsRoomStorage.save(news.toNewsEntity())
        }
    }

    override suspend fun getAllFromDatabase(): List<NewsDomain> {
        return withContext(Dispatchers.IO) {
            newsRoomStorage.getAll().map { newsEntity ->
                newsEntity.toNewsDomain()
            }
        }
    }

    override suspend fun deleteFromDatabase(news: NewsDomain) {
        withContext(Dispatchers.IO) {
            newsRoomStorage.delete(news.toNewsEntity())
        }
    }

    override suspend fun getAllFromInternet(): List<NewsDomain> {
        return withContext(Dispatchers.IO) {
            newsApiStorage.getAll().map { newsEntity ->
                newsEntity.toNewsDomain()
            }
        }
    }

}