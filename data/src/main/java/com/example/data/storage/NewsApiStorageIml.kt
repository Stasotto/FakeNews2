package com.example.data.storage

import com.example.data.storage.models.NewsEntity
import com.example.data.storage.retrofit.NewsApi
import com.example.data.toListNewsEntity

class NewsApiStorageIml(private val newsApi: NewsApi) : NewsApiStorage {

    override suspend fun getAll(): List<NewsEntity> {
        return newsApi.getEverything(
            "политика",
            null,
            null,
            "ru",
            "",
        ).toListNewsEntity()
    }
}