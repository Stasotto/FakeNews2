package com.example.domain.usecase

import com.example.domain.models.NewsDomain
import com.example.domain.repository.NewsRepository

class SaveNewsToDatabaseUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(news: NewsDomain) {
        newsRepository.saveToDatabase(news)
    }
}