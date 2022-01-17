package com.example.domain.repository

import com.example.domain.models.NewsDomain

interface NewsRepository {

    suspend fun saveInRoomNews(news: NewsDomain)

    suspend fun getAllFromRoomNews(): List<NewsDomain>

    suspend fun deleteFromRoomNews(news: NewsDomain)

    suspend fun getAllFromNewsApi(): List<NewsDomain>
}