package com.example.fakenews2.presentation.newsrecycler

import com.example.fakenews2.presentation.models.News

interface OnNewsClickListener {

    fun onIconDeleteClickListener(news: News)

    fun onIconSaveClickListener(news: News)

}