package com.example.fakenews2.presentation.newsrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakenews2.R
import com.example.fakenews2.databinding.NewsLayoutBinding
import com.example.fakenews2.presentation.models.News

class NewsHolder(
    item: View,
    private val onNewsClickListener: OnNewsClickListener
) : RecyclerView.ViewHolder(item) {

    companion object {
        fun from(parent: ViewGroup, onNewsClickListener: OnNewsClickListener) = NewsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_layout, parent, false), onNewsClickListener
        )
    }

    private val binding: NewsLayoutBinding by lazy { NewsLayoutBinding.bind(item) }

    fun bind(news: News) = with(binding) {
        title.text = news.title
        saveIcon.isChecked = news.isSaved
        saveIcon.setOnClickListener {
            if (saveIcon.isChecked) {
                onNewsClickListener.onIconSaveClickListener(news)
            } else {
                onNewsClickListener.onIconDeleteClickListener(news)
            }
        }
    }

}
