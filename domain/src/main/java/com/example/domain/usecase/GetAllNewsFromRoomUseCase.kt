package com.example.domain.usecase

import com.example.domain.models.NewsDomain
import com.example.domain.repository.NewsRepository

class GetAllNewsFromRoomUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(): List<NewsDomain> {
        return newsRepository.getAllFromRoomNews()
    }
}