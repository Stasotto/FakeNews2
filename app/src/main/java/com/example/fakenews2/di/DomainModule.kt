package com.example.fakenews2.di

import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import com.example.domain.usecase.DeleteNewsFromDatabaseUseCase
import com.example.domain.usecase.GetAllNewsFromInternetUseCase
import com.example.domain.usecase.GetAllNewsFromDatabaseUseCase
import com.example.domain.usecase.SaveNewsToDatabaseUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        DeleteNewsFromDatabaseUseCase(newsRepository = get())
    }

    single {
        GetAllNewsFromInternetUseCase(newsRepository = get())
    }

    single {
        GetAllNewsFromDatabaseUseCase(newsRepository = get())
    }

    single {
        SaveNewsToDatabaseUseCase(newsRepository = get())
    }

    single<NewsRepository> {
        NewsRepositoryImpl(newsApiStorage = get(), newsRoomStorage = get())
    }
}