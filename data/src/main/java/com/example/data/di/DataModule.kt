package com.example.data.di

import androidx.room.Room
import com.example.data.storage.NewsApiStorage
import com.example.data.storage.NewsApiStorageIml
import com.example.data.storage.NewsRoomStorage
import com.example.data.storage.NewsRoomStorageImpl
import com.example.data.storage.retrofit.RetrofitClient
import com.example.data.storage.room.AppDatabase
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "news"
        ).build()
    }

    single {
        get<AppDatabase>().getNewsDao()
    }

    single {
        RetrofitClient.getNewsApi()
    }

    single<NewsRoomStorage> {
        NewsRoomStorageImpl(newsDao = get())
    }

    single<NewsApiStorage> {
        NewsApiStorageIml(newsApi = get())
    }
}
