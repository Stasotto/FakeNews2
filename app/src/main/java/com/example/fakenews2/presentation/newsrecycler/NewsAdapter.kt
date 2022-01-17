package com.example.fakenews2.presentation.newsrecycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakenews2.presentation.models.News

class NewsAdapter(
    private val onNewsClickListener: OnNewsClickListener
) : RecyclerView.Adapter<NewsHolder>() {

    private val listOfNews = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder.from(parent, onNewsClickListener)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(listOfNews[position])
    }

    override fun getItemCount(): Int {
        return listOfNews.size
    }

    fun addNews(news: List<News>) {
        listOfNews.addAll(news)
        notifyDataSetChanged()
    }
}