package com.example.domain.repository

import com.example.domain.models.NewsDomain

interface NewsRepository {

    suspend fun saveToDatabase(news: NewsDomain)

    suspend fun getAllFromDatabase(): List<NewsDomain>

    suspend fun deleteFromDatabase(news: NewsDomain)

    suspend fun getAllFromInternet(): List<NewsDomain>
}