package com.example.data

import com.example.data.storage.models.NewsEntity
import com.example.data.storage.retrofit.dto.Article
import com.example.data.storage.retrofit.dto.NewsResponse
import com.example.domain.models.NewsDomain



fun NewsEntity.toNewsDomain() = NewsDomain(
    title = title,
    subtitle = subtitle,
    isSaved = isSaved,
    imageUrl = imageUrl
)

fun NewsDomain.toNewsEntity() = NewsEntity(
    title = title,
    subtitle = subtitle,
    isSaved = true,
    imageUrl = imageUrl
)

fun NewsResponse.toListNewsEntity() = articles.map { article ->
    article.toNewsEntity()
}

private fun Article.toNewsEntity() = NewsEntity(
    title = title.orEmpty(),
    subtitle = content.orEmpty(),
    imageUrl = previewUrl.orEmpty()
)
