package com.example.fakenews2

import com.example.domain.models.NewsDomain
import com.example.fakenews2.presentation.models.News

fun NewsDomain.toNews() = News(
    title = title,
    subtitle = subtitle,
    isSaved = isSaved,
    imageUrl = imageUrl
)

fun News.toNewsDomain() = NewsDomain(
    title = title,
    subtitle = subtitle,
    isSaved = isSaved,
    imageUrl = imageUrl
)