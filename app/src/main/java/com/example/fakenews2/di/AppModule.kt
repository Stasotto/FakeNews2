package com.example.fakenews2.di

import com.example.fakenews2.presentation.viewmodels.NewsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        NewsFragmentViewModel(
            deleteNewsFromRoomUseCase = get(),
            getAllNewsFromRoomUseCase = get(),
            getAllNewsFromNewsApiUseCase = get(),
            saveNewsInRoomUseCase = get()
        )
    }
}