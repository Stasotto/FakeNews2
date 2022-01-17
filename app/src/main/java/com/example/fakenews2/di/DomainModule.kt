package com.example.fakenews2.di

import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import com.example.domain.usecase.DeleteNewsFromRoomUseCase
import com.example.domain.usecase.GetAllNewsFromNewsApiUseCase
import com.example.domain.usecase.GetAllNewsFromRoomUseCase
import com.example.domain.usecase.SaveNewsInRoomUseCase
import org.koin.dsl.module

val domainModule = module {

    single {
        DeleteNewsFromRoomUseCase(newsRepository = get())
    }

    single {
        GetAllNewsFromNewsApiUseCase(newsRepository = get())
    }

    single {
        GetAllNewsFromRoomUseCase(newsRepository = get())
    }

    single {
        SaveNewsInRoomUseCase(newsRepository = get())
    }

    single<NewsRepository> {
        NewsRepositoryImpl(newsApiStorage = get(), newsRoomStorage = get())
    }
}