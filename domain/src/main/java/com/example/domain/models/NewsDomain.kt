package com.example.domain.models

data class NewsDomain(
    val title: String,
    val subtitle: String,
    val isSaved: Boolean = false,
    val imageUrl: String
) {
}