package com.example.fakenews2.presentation.models

data class News(
    val title: String,
    val subtitle: String,
    val isSaved: Boolean = false,
    val imageUrl: String

)
